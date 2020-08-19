package com.iiht.stockExchange.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.stockExchange.entity.StockPriceDetails;

@Repository
@Transactional
public class StockMarketDaoImpl implements StockMarketDao 
{
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public Boolean saveStock(StockPriceDetails stockPriceDetails) {
		sessionFactory.getCurrentSession().persist(stockPriceDetails);
		return true;
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StockPriceDetails> getAllStock() {
		String hql = "FROM StockPriceDetails";		  		
		return (List<StockPriceDetails>) sessionFactory.getCurrentSession().createQuery(hql).list();		
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StockPriceDetails> getStockByCompanyCode(Long companyCode) {
		String hql = "FROM StockPriceDetails s where s.companyCode=?";
		return (List<StockPriceDetails>) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyCode).list();
	};

	@SuppressWarnings("unchecked")
	@Override
	public List<StockPriceDetails> getStockRange(LocalDate from, LocalDate to) {
		String hql = "FROM StockPriceDetails AS s WHERE s.stockPriceDate BETWEEN :startDate AND :endDate ";
		return (List<StockPriceDetails>) sessionFactory.getCurrentSession().createQuery(hql).setParameter("startDate", from).setParameter("endDate", to).list();
	};
	
	//-----------------------------------------------------------------------------------------------------------------------------
	public Double getMaxStockPrice(LocalDate from, LocalDate to) {
		String hql = "SELECT MAX(currentStockPrice) FROM StockPriceDetails AS s WHERE s.stockPriceDate BETWEEN :startDate AND :endDate ";
		return (Double) sessionFactory.getCurrentSession().createQuery(hql).setParameter("startDate", from).setParameter("endDate", to).getSingleResult();
	};
	
	public Double getAvgStockPrice(LocalDate from, LocalDate to) {
		String hql = "SELECT AVG(currentStockPrice) FROM StockPriceDetails AS s WHERE s.stockPriceDate BETWEEN :startDate AND :endDate ";
		return (Double) sessionFactory.getCurrentSession().createQuery(hql).setParameter("startDate", from).setParameter("endDate", to).getSingleResult();
	};
	
	public Double getMinStockPrice(LocalDate from, LocalDate to) {
		String hql = "SELECT MIN(currentStockPrice) FROM StockPriceDetails AS s WHERE s.stockPriceDate BETWEEN :startDate AND :endDate ";
		return (Double) sessionFactory.getCurrentSession().createQuery(hql).setParameter("startDate", from).setParameter("endDate", to).getSingleResult();
	};
}