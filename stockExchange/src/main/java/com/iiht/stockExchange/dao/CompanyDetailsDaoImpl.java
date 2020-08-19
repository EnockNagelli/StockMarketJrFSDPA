package com.iiht.stockExchange.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.stockExchange.entity.CompanyDetails;

@Repository
@Transactional
public class CompanyDetailsDaoImpl implements CompanyDetailsDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public Boolean saveCompany(CompanyDetails companyDetails) {
		sessionFactory.getCurrentSession().save(companyDetails);
		return true;
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDetails> getAllCompanies(){
		String hql = "FROM CompanyDetails";		  		
		return (List<CompanyDetails>) sessionFactory.getCurrentSession().createQuery(hql).list();		
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDetails> getAllCompaniesByExchange(String stockExchange){
		String hql = "from CompanyDetails c where c.stockExchange = ?";
		return (List<CompanyDetails>) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, stockExchange).list();
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDetails> getCompanyCode(String companyName) {
		String hql = "from CompanyDetails c where c.companyName = ?";
		return (List<CompanyDetails>) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyName).list();
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDetails> getCompanyInfoByCompanyCode(Long companyCode) {
		String hql = "from CompanyDetails c where c.companyCode = ?";
		return (List<CompanyDetails>) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyCode).list();
		
	};
}