package com.mercury.demand.persistence.model;

import java.io.Serializable;
import java.util.List;

public class StocksInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6094795705101991319L;
	private List<Stocks> stocks;

	public StocksInfo() {}
	
	public List<Stocks> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stocks> stocks) {
		this.stocks = stocks;
	}
	
	
}
