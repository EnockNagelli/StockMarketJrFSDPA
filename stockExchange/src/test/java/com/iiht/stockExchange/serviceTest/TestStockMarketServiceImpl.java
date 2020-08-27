package com.iiht.stockExchange.serviceTest;

import static com.iiht.stockExchange.UtilTestClass.TestUtils.businessTestFile;
import static com.iiht.stockExchange.UtilTestClass.TestUtils.currentTest;
import static com.iiht.stockExchange.UtilTestClass.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.stockExchange.UtilTestClass.MasterData;
import com.iiht.stockExchange.dao.StockMarketDao;
import com.iiht.stockExchange.entity.StockPriceDetails;
import com.iiht.stockExchange.service.StockMarketServiceImpl;

public class TestStockMarketServiceImpl {

	@Mock
	private StockMarketDao stockMarketDao;

	@InjectMocks
	private StockMarketServiceImpl stockMarketServiceImpl;
	
	// -------------------------------------------------------------------------------------------------------------------
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testStockMarketServiceImpl() throws Exception {
		boolean value = stockMarketServiceImpl.saveStockDetails(MasterData.getStockPriceDetails());
	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testViewAllStockDetails() throws Exception {
		List<StockPriceDetails> list = new ArrayList<StockPriceDetails>();
		list.add(MasterData.getStockPriceDetails());
		list.add(MasterData.getStockPriceDetails());
		when(stockMarketDao.getAllStock()).thenReturn((List<StockPriceDetails>) list);
		List<StockPriceDetails> commentFromdb = stockMarketServiceImpl.getAllStock();
	    yakshaAssert(currentTest(), commentFromdb == list ? true : false, businessTestFile);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testViewAllStockDetails1() throws Exception {
		List<StockPriceDetails> list = new ArrayList<StockPriceDetails>();
		when(stockMarketDao.getAllStock()).thenReturn((List<StockPriceDetails>) list);
		List<StockPriceDetails> commentFromdb = stockMarketServiceImpl.getAllStock();
	    yakshaAssert(currentTest(), commentFromdb==list ? true : false, businessTestFile);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testViewAllStockMarketImplTest2() throws Exception { 
		when(stockMarketDao.getAllStock()).thenReturn(null);
		List<StockPriceDetails> commentFromdb = stockMarketServiceImpl.getAllStock();
	    yakshaAssert(currentTest(), commentFromdb==null ? true : false, businessTestFile);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testSaveStockMarketImplTest() throws Exception {
		when(stockMarketDao.saveStockDetails(MasterData.getStockPriceDetails())).thenReturn(true); 
		List<StockPriceDetails> commentFromdb = stockMarketServiceImpl.getAllStock();
	    yakshaAssert(currentTest(), commentFromdb != null ? true : false, businessTestFile);
	}	
}