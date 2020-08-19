package com.iiht.stockExchange.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.stockExchange.dao.CompanyDetailsDao;
import com.iiht.stockExchange.entity.CompanyDetails;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDetailsDao companyDetailsDao;
	
	public Boolean saveCompany(CompanyDetails companyDetails) {
		companyDetailsDao.saveCompany(companyDetails);
		return true;
	};

	public List<CompanyDetails> getAllCompanies() {
		return companyDetailsDao.getAllCompanies();
	};
	
	public List<CompanyDetails> getAllCompaniesByExchange(String stockExchange) {
		return companyDetailsDao.getAllCompaniesByExchange(stockExchange);
	};
	
	public List<CompanyDetails> getCompanyCode(String companyName){
		return companyDetailsDao.getCompanyCode(companyName);
	};

	public List<CompanyDetails> getCompanyInfoByCompanyCode(Long companyCode){
		return companyDetailsDao.getCompanyInfoByCompanyCode(companyCode);
	};	
}