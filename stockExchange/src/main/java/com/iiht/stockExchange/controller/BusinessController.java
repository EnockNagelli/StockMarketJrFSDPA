package com.iiht.stockExchange.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iiht.stockExchange.entity.CompanyDetails;
import com.iiht.stockExchange.entity.StockExchange;
import com.iiht.stockExchange.entity.StockPriceDetails;
import com.iiht.stockExchange.service.CompanyService;
import com.iiht.stockExchange.service.StockMarketService;

@Controller
public class BusinessController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private StockMarketService stockMarketService;

	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String visitorPage(HttpServletResponse response) throws IOException {
		System.out.println("Server Started. Inside the controller. Serving upon landing page...");
		return "home";
		// return new ModelAndView("home", "CompanyDetails", new CompanyDetails());
	}
	
	// -----------------------------------------------------------------------------------------------
	// 			1. ADD NEW COMPANY DETAILS FOR THE STOCK EXCHANGE
	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/addNewCompany", method = RequestMethod.GET)
	public ModelAndView addNewCompany() {
		List<StockExchange> exchanges = new ArrayList<StockExchange>(Arrays.asList(StockExchange.values()));
		return new ModelAndView("addCompanyInfo", "exchanges", exchanges);
	}
	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
	public String saveCompanyInfo(HttpServletRequest request) {
		
		Long companyCode = Long.parseLong(request.getParameter("companyCode"));
		String companySE = request.getParameter("stockExchange");
		String companyName = request.getParameter("companyName");
		String companyCEO = request.getParameter("ceoName");
		Double companyTurnover = Double.parseDouble(request.getParameter("turnover"));
		String companyDirectors = request.getParameter("directors");
		String companyProfile = request.getParameter("profile");

		CompanyDetails companyDetails = new CompanyDetails();

		companyDetails.setCompanyCode(companyCode);
		companyDetails.setStockExchange(companySE);
		companyDetails.setCompanyName(companyName);
		companyDetails.setCompanyCEO(companyCEO);
		companyDetails.setTurnover(companyTurnover);
		companyDetails.setBoardOfDirectors(companyDirectors);
		companyDetails.setCompanyProfile(companyProfile);

		/*
		 * System.out.println("Company Details : ");
		 * System.out.println("----------------------------------");
		 * System.out.println("1. Company Code : " + companyDetails.getCompanyCode());
		 * System.out.println("2. Registered Stock Exchange : " +
		 * companyDetails.getStockExchange()); System.out.println("3. Company Name : " +
		 * companyDetails.getCompanyName()); System.out.println("4. Company CEO : " +
		 * companyDetails.getCompanyCEO()); System.out.println("5. Company Turnover : "
		 * + companyDetails.getTurnover());
		 * System.out.println("6. Board Of Directors : " +
		 * companyDetails.getBoardOfDirectors());
		 * System.out.println("7. Company Profile : " +
		 * companyDetails.getCompanyProfile());
		 * System.out.println("----------------------------------");
		 */
		 
		companyService.saveCompany(companyDetails);

		return "home";
	}
	
	// -----------------------------------------------------------------------------------------------
	// 			2. DISPLAY COMPANY DETAILS FOR A STOCK EXCHANGE
	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectExchange1", method = RequestMethod.GET)
	public ModelAndView selectExchange() {
		return new ModelAndView("selectExchange");							// Here the page will display
	}
	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectExchange", method = RequestMethod.GET)
	public ModelAndView displayAllCompaniesofAnExchange(HttpServletRequest request) {
		ModelAndView md = null;
		String stockExchange = request.getParameter("stockExchange");
		List<CompanyDetails> companyList = companyService.getAllCompaniesByExchange(stockExchange);
		md = new ModelAndView("selectExchange", "clist", companyList);
		return md;
	}
	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/viewAllCompanies", method = RequestMethod.GET)
	public ModelAndView getAllCompanies() {
		ModelAndView md = null;
		try {
			List<CompanyDetails> companyList = companyService.getAllCompanies();

			if (!CollectionUtils.isEmpty(companyList)) {
				md = new ModelAndView("viewListedCompanies", "clist", companyList);
			} else {
				md = new ModelAndView("home");
				throw new RuntimeException("No Records Found");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return md;
	}
	
	// -----------------------------------------------------------------------------------------------
	// 			3. ADD MULTIPLE STOCK PRICES FOR THE COMPANY
	// --------------------------------------------------------------------------------------------
	@RequestMapping(value = "/addStockPrices", method = RequestMethod.GET)
	public ModelAndView addStockPrices() {
		return new ModelAndView("addStockPrices");						// Here the page will be displayed
	}
	//--------------------------------------------------------------------------------------------	
	@RequestMapping(value = "/selectStockExchange", method = RequestMethod.GET)
	public ModelAndView populateCompanyNames(HttpServletRequest request) 
	{
		String stockExchange = request.getParameter("stockExchange");
		List<CompanyDetails> companyList = companyService.getAllCompaniesByExchange(stockExchange);
		
		HttpSession session = request.getSession();
		session.setAttribute("sExchange", stockExchange);
	
		return new ModelAndView("addStockPrices", "companyList", companyList);
	}
	//--------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectCompanyName", method = RequestMethod.GET)
	public ModelAndView selectCompanyName(HttpServletRequest request) 
	{
		String companyName = request.getParameter("companyName");

		HttpSession session = request.getSession();
		session.setAttribute("cName", companyName);
		
		List<CompanyDetails> companyList = companyService.getCompanyCode(companyName);
		Long companyCode = (Long) companyList.get(0).getCompanyCode();
		session.setAttribute("cCode", companyCode);

		return new ModelAndView("addStockPrices", "companyCode", companyCode);
	}
	//--------------------------------------------------------------------------------------
	@RequestMapping(value = "/commitStockPrices", method = RequestMethod.GET)
	public ModelAndView addStockDetails(HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		
		String stockExchange = (String) session.getAttribute("sExchange");
		String companyName = (String) session.getAttribute("cName");	
		Long companyCode = (Long) session.getAttribute("cCode");
		//Long stockIndex = (Long) Long.parseLong(request.getParameter("stockId"));
		Double stockPrice = Double.parseDouble(request.getParameter("stockPrice"));

		String date = request.getParameter("stockDate");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate stockDate = LocalDate.parse(date, dateFormat);
		
		String actualTime = request.getParameter("stockTime");						// 10:30PM
		String timeValue = actualTime.substring(0, 5)+":00";						// 10:30:01:001

		//DateTimeFormatter timeFormat = DateTimeFormatter.ISO_LOCAL_TIME;
		//DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss.nnn");
		//LocalTime stockLocalTime = LocalTime.parse(timeValue, timeFormat);
		//Time stockTime = new LocalTimeAttributeConverter().convertToDatabaseColumn(stockLocalTime);
		//Timestamp stockTime = new LocalDateTimeAttributeConverter().convertToDatabaseColumn(stockLocalTime);

		//Time stockTime = Time.valueOf(timeValue);
//--------------------------------------------------------------------------------------
		//java.util.Date dt = new java.util.Date();
		//System.out.println("Current Time A : "+dt.getTime());					// 1597212365308
		//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String currentTime = sdf.format(dt);
		//System.out.println("Current Time B : "+currentTime);					// 2020-08-12 11:36:05
//--------------------------------------------------------------------------------------
		/*
		 * System.out.println("Stock Price Details : ");
		 * System.out.println("----------------------------------");
		 * System.out.println("1. Stock Id : "+stockIndex);
		 * System.out.println("2. Selected Stock Exchange : "+stockExchange);
		 * System.out.println("3. Selected Company Code : "+companyCode);
		 * System.out.println("4. Selected Company Name : "+companyName);
		 * System.out.println("5. Stock Price : "+stockPrice);
		 * System.out.println("6. Stock Date : "+stockDate);
		 * System.out.println("7. Stock Time : "+timeValue);
		 */		
		StockPriceDetails spd = new StockPriceDetails();
		
		//spd.setStockIndex(stockIndex);
		spd.setCompanyCode(companyCode);
		spd.setStockExchange(stockExchange);
		spd.setCompanyName(companyName);
		spd.setCurrentStockPrice(stockPrice);
		spd.setStockPriceDate(stockDate);
		spd.setStockPriceTime(timeValue);
		
		stockMarketService.saveStockDetails(spd);

		return new ModelAndView("home");
	}
	
	// -----------------------------------------------------------------------------------------------
	// 			4. STOCK DETAILS OF THE COMPANY
	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectExchange2", method = RequestMethod.GET)
	public ModelAndView selectExchange2() {
		return new ModelAndView("displayStockDetails");					//Here the page will be displayed
	}
	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectStockExchange2", method = RequestMethod.GET)
	public ModelAndView populateCompanyInfo(HttpServletRequest request) 
	{
		String stockExchange = request.getParameter("stockExchange");
		List<CompanyDetails> companyList = companyService.getAllCompaniesByExchange(stockExchange);
			
		return new ModelAndView("displayStockDetails", "companyList", companyList);
	}
	//--------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectCompanyName2", method = RequestMethod.GET)
	public ModelAndView selectACompany(HttpServletRequest request) throws Exception 
	{
		ModelAndView md = null;
		
		List<CompanyDetails> companyList = companyService.getCompanyCode(request.getParameter("companyName"));
		Long companyCode = (Long) companyList.get(0).getCompanyCode();

		String companyCEO = companyList.get(0).getCompanyCEO();
		Double companyTurnover = companyList.get(0).getTurnover();

		List<StockPriceDetails> stockPriceDetails = stockMarketService.getStockByCompanyCode(companyCode);

		try
		{
			if (!CollectionUtils.isEmpty(stockPriceDetails)) { 
				md = new ModelAndView("displayStockDetails", "spList", stockPriceDetails);
				md.addObject("comCode", companyCode);
				md.addObject("comCEO", companyCEO);
				md.addObject("comTurnover", companyTurnover);
			} else { 
				md = new ModelAndView("home"); 
				throw new RuntimeException("No Records Found"); 
			} 
		} catch(Exception e) { 
			System.out.println(e.getMessage()); 
		}
		return md;
	}
	
	// -----------------------------------------------------------------------------------------------
	// 			5. STOCK PRICE INDEX OF THE COMPANY
	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/viewStockDetails", method = RequestMethod.GET)
	public ModelAndView selectExchange3() {
		return new ModelAndView("stockPriceIndex");					//Here the page will be displayed
	}
	// -----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectStockExchange3", method = RequestMethod.GET)
	public ModelAndView populateCompanyData(HttpServletRequest request) 
	{
		String stockExchange = request.getParameter("stockExchange");
		List<CompanyDetails> companyList = companyService.getAllCompaniesByExchange(stockExchange);
	
		return new ModelAndView("stockPriceIndex", "companyList", companyList);
	}
	//--------------------------------------------------------------------------------------
	@RequestMapping(value = "/selectCompanyName3", method = RequestMethod.GET)
	public ModelAndView chooseCompany(HttpServletRequest request) throws Exception 
	{
		String companyName = request.getParameter("companyName");
		List<CompanyDetails> companyList = companyService.getCompanyCode(companyName);
		Long companyCode = (Long) companyList.get(0).getCompanyCode();

		HttpSession session = request.getSession();
		session.setAttribute("companyCode", companyCode);

		return new ModelAndView("stockPriceIndex", "cCode", companyCode);
	}
	
	@RequestMapping(value = "/displayStockPriceIndex", method = RequestMethod.GET)
	public ModelAndView displayStockPriceIndex(HttpServletRequest request) throws Exception 
	{
		ModelAndView md = null;
	
		HttpSession session = request.getSession();
		Long companyCode = (Long) session.getAttribute("companyCode");

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String sDate = request.getParameter("startDate");
		LocalDate startDate = LocalDate.parse(sDate, dateFormat);
		String eDate = request.getParameter("endDate");
		LocalDate endDate = LocalDate.parse(eDate, dateFormat);
		
		Double maxStockPrice = stockMarketService.getMaxStockPrice(startDate, endDate);
		Double avgStockPrice = stockMarketService.getAvgStockPrice(startDate, endDate);
		Double minStockPrice = stockMarketService.getMinStockPrice(startDate, endDate);
		
		List<StockPriceDetails> stockList = stockMarketService.getStockRange(startDate, endDate);

		Long stockIndex = stockList.get(stockList.size()-1).getStockIndex();
		Double currentStockPrice = stockList.get(stockList.size()-1).getCurrentStockPrice();
		
		try
		{
			if (!CollectionUtils.isEmpty(stockList)) {
				md = new ModelAndView("stockPriceIndex");
				md.addObject("cCode", companyCode);
				md.addObject("index", stockIndex);
				md.addObject("curSP", currentStockPrice);
				md.addObject("maxSP", maxStockPrice);
				md.addObject("avgSP", avgStockPrice);
				md.addObject("minSP", minStockPrice);
			} else {
				md = new ModelAndView("home");
				throw new RuntimeException("No Records Found");
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return md;
	}
}