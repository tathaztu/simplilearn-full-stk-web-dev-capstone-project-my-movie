package com.mymovie.service.purchase;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovie.domain.Purchase;
import com.mymovie.repository.PurchaseRepositoryIF;

@Service(value = "Purchase Service")
public class PurchaseServiceImpl implements PurchaseServiceIF {

	@Autowired
	private PurchaseRepositoryIF purchaseRepositoryIF;

	@Override
	public Purchase getPurchaseById(long id) {
		Optional<Purchase> optPurchase = purchaseRepositoryIF.findById(id);
		return optPurchase.isPresent() ? optPurchase.get() : null;
	}

	@Override
	public List<Purchase> getAllPurchases() {
		return purchaseRepositoryIF.findAll();
	}

	@Override
	public List<Purchase> getPurchaseByUserId(String strUserId) {
		return purchaseRepositoryIF.findByStrUserId(strUserId);
	}

	@Override
	public Purchase savePurchase(Purchase purchase) {
		return purchaseRepositoryIF.save(purchase);
	}

	@Override
	public void deletePurchase(long id) {
		purchaseRepositoryIF.deleteById(id);
	}
}
