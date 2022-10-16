package com.mymovie.service.purchase;

import java.util.List;

import com.mymovie.domain.Purchase;

public interface PurchaseServiceIF {
	// Get Purchase by ID
	Purchase getPurchaseById(long id);

	// Get All Purchases
	List<Purchase> getAllPurchases();

	// Get All Purchases by User ID
	List<Purchase> getPurchaseByUserId(String strUserId);

	// Save
	Purchase savePurchase(Purchase purchase);

	// Delete
	void deletePurchase(long id);
}
