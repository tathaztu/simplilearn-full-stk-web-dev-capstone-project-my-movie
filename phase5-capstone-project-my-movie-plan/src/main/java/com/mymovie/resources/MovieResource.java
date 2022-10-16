package com.mymovie.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mymovie.domain.Movie;
import com.mymovie.domain.MovieGenre;
import com.mymovie.domain.MovieLang;
import com.mymovie.service.movie.MovieServiceIF;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
@RestController
public class MovieResource {

	@Autowired
	private MovieServiceIF movieServiceIF;

	@GetMapping("/movie/find/{id}")
	public Movie getMovieById(@PathVariable("id") long longId) {
		return movieServiceIF.getMovieById(longId);
	}
//
//	@PostMapping("/movie/save")
//	public Movie saveMovie(@Valid @RequestBody Movie movie) {
//		return movieServiceIF.saveMovie(movie);
//	}


	@PostMapping(
		value="/movie/save",
		consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
		}
	)
	// 2.
	// @PostMapping(value="/movie/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Movie saveMovie(@RequestPart String strMovie, @RequestPart(required = false) MultipartFile file) throws IOException {
	// 2.
	// public Movie saveMovie(@ModelAttribute Movie movie) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();

		Movie movie = objectMapper.readValue(strMovie, Movie.class);

		System.out.println("Debug : " + movie.getStrMovieName());

		// 2.
		// MultipartFile file = movie.getFile();
		createDirIfNotExist();

		if(null != file && !file.isEmpty()) {
			byte[] bytes = file.getBytes();
	        String strFileName = "uploaded-files/" + file.getOriginalFilename();
	        System.out.println(strFileName);
			Path path=Paths.get(strFileName);
	        Files.write(path, bytes);

	        movie.setStrMoviePicPath(file.getOriginalFilename());
		}

        return movieServiceIF.saveMovie(movie);
	}

	private void createDirIfNotExist() {
        //create directory to save the files
        File directory = new File("uploaded-files");
        if (! directory.exists()){
            directory.mkdir();
            System.out.println("Directory created");
        }
    }

	@GetMapping("/movie/delete/{id}")
	public void deleteMovie(@PathVariable("id") long id) {
		movieServiceIF.deleteMovie(id);
	}

	@GetMapping("/movie/all")
	public List<Movie> findAllMovies() {
		return movieServiceIF.getAllMovies();
	}

	@GetMapping("/movie/by-keyword/{strKeyword}")
	public List<Movie> findAllMovies(@PathVariable("strKeyword") String strKeyword) {
		System.out.println("Path Variable: " + strKeyword);
		return movieServiceIF.getMoviesByKeyword(strKeyword);
	}

	@GetMapping("/movie/genres/all")
	public List<MovieGenre> getGeneres(){
		return movieServiceIF.getMovieGenres();
	}

	@GetMapping("/movie/languages/all")
	public List<MovieLang> getLanguages(){
		return movieServiceIF.getMovieLanguages();
	}

	@GetMapping("/movie/getImage/{imageName}")
	// public @ResponseBody byte[] getImageWithMediaType(
	public ResponseEntity<?> getImageWithMediaType(
	    @PathVariable("imageName") String imageName
	) throws IOException {
		System.out.println("Fetching Image");
	    Path imagePath = Paths.get("uploaded-files/" + imageName);

	    // InputStream in = Files.newInputStream(path);
	    // return IOUtils.toByteArray(in);

		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(imagePath));
                
		return ResponseEntity.ok()
				.contentLength(imagePath.toFile().length())
				.contentType(MediaType.IMAGE_JPEG)
				.body(resource);
	}
}
