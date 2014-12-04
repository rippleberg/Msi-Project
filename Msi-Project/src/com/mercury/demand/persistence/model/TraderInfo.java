package com.mercury.demand.persistence.model;

import java.io.Serializable;
import java.util.List;

public class TraderInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 405310139983594654L;
	private List<Trader> traders;
	
	public TraderInfo() {}

	public List<Trader> getTraders() {
		return traders;
	}

	public void setTraders(List<Trader> traders) {
		this.traders = traders;
	}
}
