package com.mercury.demand.persistence.model;

import java.util.List;

public class TraderInfo {

	private List<Trader> traders;
	
	public TraderInfo() {}

	public List<Trader> getTraders() {
		return traders;
	}

	public void setTraders(List<Trader> traders) {
		this.traders = traders;
	}
}
