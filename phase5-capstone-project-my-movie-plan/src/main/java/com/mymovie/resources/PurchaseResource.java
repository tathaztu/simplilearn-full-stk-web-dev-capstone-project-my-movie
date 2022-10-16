package com.mymovie.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymovie.domain.Purchase;
import com.mymovie.service.purchase.PurchaseServiceIF;

// @CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
@RestController
public class PurchaseResource {

	@Autowired
	private PurchaseServiceIF purchaseServiceIF;

	// Get Purchase by ID
	@GetMapping("/purchase/find/byId/{id}")
	Purchase getPurchaseById(@PathVariable("id") long id) {
		return purchaseServiceIF.getPurchaseById(id);
	};

	// Get All Purchases
	@GetMapping("/purchase/find/all")
	List<Purchase> getAllPurchases() {
		return purchaseServiceIF.getAllPurchases();
	}

	// Get All Purchases by User ID
	@GetMapping("/purchase/find/byUserId/{strUserId}")
	List<Purchase> findByStrUserId(@PathVariable("strUserId") String strUserId){
		return purchaseServiceIF.getPurchaseByUserId(strUserId);
	}

	// Save
	@PostMapping("/purchase/save")
	Purchase savePurchase(@Valid @RequestBody Purchase purchase) {
		System.out.println("Before invoking save " + purchase);
		return purchaseServiceIF.savePurchase(purchase);
	}

	// Delete
	@GetMapping("/purchase/delete/{id}")
	void deletePurchase(@PathVariable("id") long id) {
		purchaseServiceIF.deletePurchase(id);
	}


}
