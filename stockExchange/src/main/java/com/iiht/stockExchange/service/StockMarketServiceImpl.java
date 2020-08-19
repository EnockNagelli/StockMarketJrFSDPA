package com.iiht.stockExchange.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.stockExchange.dao.StockMarketDao;
import com.iiht.stockExchange.entity.StockPriceDetails;

@Service
@Transactional
public class StockMarketServiceImpl implements StockMarketService{

	@Autowired
	private StockMarketDao stockMarketDao;
	
	@Override
	public Boolean saveStockDetails(StockPriceDetails stockPriceDetails) {
		stockMarketDao.saveStock(stockPriceDetails);
		return true;
	};

	@Override
	public List<StockPriceDetails> getAllStock() {
		return stockMarketDao.getAllStock();
	};

	@Override
	public List<StockPriceDetails> getStockByCompanyCode(Long companyCode){
		return stockMarketDao.getStockByCompanyCode(companyCode);
	};
	
	@Override
	public List<StockPriceDetails> getStockRange(LocalDate from, LocalDate to){
		return stockMarketDao.getStockRange(from, to);
	};
	//---------------------------------------------------------------------------------------------
	@Override
	public Double getMaxStockPrice(LocalDate from, LocalDate to) {
		return stockMarketDao.getMaxStockPrice(from, to);
	};

	@Override
	public Double getAvgStockPrice(LocalDate from, LocalDate to) {
		return stockMarketDao.getAvgStockPrice(from, to);
	};
	
	@Override
	public Double getMinStockPrice(LocalDate from, LocalDate to) {
		return stockMarketDao.getMinStockPrice(from, to);
	};
}