package com.iiht.stockExchange.dao;

import java.time.LocalDate;
import java.util.List;

import com.iiht.stockExchange.entity.StockPriceDetails;

public interface StockMarketDao {
	public Boolean saveStock(StockPriceDetails stockPriceDetails);
	public List<StockPriceDetails> getAllStock();	
	public List<StockPriceDetails> getStockByCompanyCode(Long companyCode);
	public List<StockPriceDetails> getStockRange(LocalDate from, LocalDate to);
	
	public Double getMaxStockPrice(LocalDate from, LocalDate to);
	public Double getAvgStockPrice(LocalDate from, LocalDate to);
	public Double getMinStockPrice(LocalDate from, LocalDate to);
}