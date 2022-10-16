package com.mymovie.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PURCHASE")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PURCHASE_ID")
	private long longPurchaseId;

	@Column(name = "USER_ID")
	private String strUserId;

	@Column(name = "TOTAL_PRICE")
	private float flTotalPrice;

	@OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PurchaseItem> listPurchasedItems = new ArrayList<>();

	public Purchase() {}

	public Purchase(long longPurchaseId, String strUserId, float flTotalPrice) {
		super();
		this.longPurchaseId = longPurchaseId;
		this.strUserId = strUserId;
		this.flTotalPrice = flTotalPrice;
	}

	public long getLongPurchaseId() {
		return longPurchaseId;
	}

	public void setLongPurchaseId(long longPurchaseId) {
		this.longPurchaseId = longPurchaseId;
	}

	public String getStrUserId() {
		return strUserId;
	}

	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}

	public float getFlTotalPrice() {
		return flTotalPrice;
	}

	public void setFlTotalPrice(float flTotalPrice) {
		this.flTotalPrice = flTotalPrice;
	}

	public List<PurchaseItem> getListPurchasedItems() {
		return listPurchasedItems;
	}

	public void setListPurchasedItems(List<PurchaseItem> listPurchasedItems) {
		this.listPurchasedItems = listPurchasedItems;

		this.listPurchasedItems.stream().forEach(e -> e.setPurchase(this));
	}
}
