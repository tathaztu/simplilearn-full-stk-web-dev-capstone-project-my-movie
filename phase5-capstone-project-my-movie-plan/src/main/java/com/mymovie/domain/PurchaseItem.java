package com.mymovie.domain;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PURCHASE_ITEMS")
public class PurchaseItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PURCHASE_ITEM_ID")
	private long longPurchaseItemId;

	@ManyToOne
	@JoinColumn(name = "PURCHASE_ID")
	@JsonIgnore
	private Purchase purchase;

	@Column(name = "MOVIE_ID")
	private long longMovieId;

	@Column(name = "SHOW_TIME", columnDefinition = "TIME")
	private LocalTime ltShowTime;

	@Column(name = "QUANTITY")
	private int nQuantity;

	public PurchaseItem() {}

	public PurchaseItem(long longPurchaseItemId, long longMovieId, int nQuantity,
			Purchase purchase) {
		this.longPurchaseItemId = longPurchaseItemId;
		this.longMovieId = longMovieId;
		this.nQuantity = nQuantity;
		this.purchase = purchase;
	}

	public long getLongPurchaseItemId() {
		return longPurchaseItemId;
	}

	public void setLongPurchaseItemId(long longPurchaseItemId) {
		this.longPurchaseItemId = longPurchaseItemId;
	}

	public long getLongMovieId() {
		return longMovieId;
	}

	public void setLongMovieId(long longMovieId) {
		this.longMovieId = longMovieId;
	}

	public int getnQuantity() {
		return nQuantity;
	}

	public void setnQuantity(int nQuantity) {
		this.nQuantity = nQuantity;
	}

	public LocalTime getLtShowTime() {
		return ltShowTime;
	}

	public void setLtShowTime(LocalTime ltShowTime) {
		this.ltShowTime = ltShowTime;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
}
