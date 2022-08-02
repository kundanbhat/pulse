package com.ie.Pulse.controller;






import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import com.ie.Pulse.Services.ActualService;
import com.ie.Pulse.Services.BUService;
import com.ie.Pulse.Services.BrandEmployeeService;
import com.ie.Pulse.Services.BrandService;
import com.ie.Pulse.Services.BuRevenueService;
import com.ie.Pulse.Services.BusinessUnitEmployeeService;
import com.ie.Pulse.Services.EmployeeService;
import com.ie.Pulse.Services.ExpenseDataService;
import com.ie.Pulse.Services.L1Service;
import com.ie.Pulse.Services.L2Service;
import com.ie.Pulse.Services.MainDepartmentService;
import com.ie.Pulse.Services.PageViewService;
import com.ie.Pulse.Services.PartyService;
import com.ie.Pulse.Services.RevenueDataService;
import com.ie.Pulse.Services.RevenueHeadService;
import com.ie.Pulse.Services.RevenueSubHeadService;
import com.ie.Pulse.Services.SubDepartmentService;
import com.ie.Pulse.Services.UserService;
import com.ie.Pulse.entity.Actual;
import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.BrandEmployee;
import com.ie.Pulse.entity.BuRevenue;
import com.ie.Pulse.entity.BusinessUnit;
import com.ie.Pulse.entity.BusinessUnitEmployee;
import com.ie.Pulse.entity.Employee;
import com.ie.Pulse.entity.ExpenseData;
import com.ie.Pulse.entity.HeadCountdto;
import com.ie.Pulse.entity.L2;
import com.ie.Pulse.entity.MainDepartment;
import com.ie.Pulse.entity.PageView;
import com.ie.Pulse.entity.Party;
import com.ie.Pulse.entity.ProfitLoss;
import com.ie.Pulse.entity.RevenueData;
import com.ie.Pulse.entity.RevenueHead;
import com.ie.Pulse.entity.RevenueSubHeads;
import com.ie.Pulse.entity.SubDepartment;
import com.ie.Pulse.entity.Users;
import com.ie.Pulse.util.ExcelHelper;
import com.ie.Pulse.util.Util;




@Controller
public class PulseController {


	@Autowired
	L1Service l1Service;


	@Autowired
	L2Service l2Service;


	@Autowired
	ExpenseDataService expenseDataService;

	@Autowired
	PartyService partyServive;

	@Autowired
	BrandService brandService;


	@Autowired
	BUService businessService;


	@Autowired
	EmployeeService empService;


	@Autowired
	MainDepartmentService mainDepartmentService;


	@Autowired
	SubDepartmentService subDepartmentService;


	@Autowired
	BrandEmployeeService brandEmployeeService;


	@Autowired
	BusinessUnitEmployeeService buempService;

	@Autowired
	PageViewService pageViewService;


	@Autowired
	ActualService actualService;

	@Autowired
	RevenueHeadService headService;

	@Autowired
	RevenueSubHeadService subheadService;

	@Autowired
	RevenueDataService revenueDataService;

	@Autowired
	BuRevenueService revenueService;

	@Autowired
	UserService userService;




	@GetMapping("/home")
	public String getHomePage(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{

		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "home";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}

	}
	@GetMapping("/")
	public String getHomepage(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "home";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "login";
		}

	}


	@GetMapping("/user")
	public String getUserpage(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()==3)
			{

				model.addAttribute("brand", brandService.getAllBrands());
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "Users";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}





	}

	@GetMapping("/getUserData")
	public String getUserData(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		
		
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()==3)
			{

				model.addAttribute("brand", brandService.getAllBrands());
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				model.addAttribute("users", userService.getAllUsers());
			
				
				return "userunits";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}
		
	}


	@GetMapping("/deleteUserData")
	public String deleteUserData(@AuthenticationPrincipal OAuth2User user,ModelMap model,@RequestParam long id)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()==3)
			{

				model.addAttribute("brand", brandService.getAllBrands());
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));

				Users userDb=userService.getUser(id);
				userDb.getBrands().clear();
				userService.delete(userDb);
				model.addAttribute("users", userService.getAllUsers());
				model.addAttribute("userName", user.getAttribute("email"));
				return "userunits";
			
				
				
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}
		
		
		
	}


	@GetMapping("/saveUserData")
	public String saveUserData(@AuthenticationPrincipal OAuth2User user,ModelMap model,@RequestParam String email,@RequestParam List<String> brand,@RequestParam String level,@RequestParam long id)
	{


		Users userDb=null;
		if(id>0)
		{
			userDb=userService.getUser(id);
			userDb.getBrands().clear();
		}

		userDb.setUserName(email);

		if(brand.size()>0)
		{
			for(String bd:brand)
			{
				Brand bds=brandService.getBrandById(Long.parseLong(bd));
				if(!userDb.getBrands().contains(bds))
					userDb.getBrands().add(bds);
			}
		}
		userDb.setLevel(Integer.parseInt(level));
		userService.save(userDb);
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("userName", user.getAttribute("email"));
		return "userunits";

	}





	@GetMapping("/uploadExpense")
	public String showUploadExpensePage(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()>1)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "uploadexpense";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}



	}


	@GetMapping("/uploadHeadCount")
	public String showUploadHeadCount(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()>1)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "uploadheadcount";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}

	}

	@GetMapping("/uploadactual")
	public String showActual(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()>1)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "uploadactual";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}

	}

	@GetMapping("/uploadrevenue")
	public String showrevenue(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()>1)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "revenueUpload";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}

	}





	@GetMapping("/revenueView")
	public String revenueView(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "RevenueReports";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}



	}


	@GetMapping("/pnl")
	public String pnlView(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{

		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "pnlReports";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}




	}



	@PostMapping("/uploadActual")
	public String ProcessActualDate(@RequestParam("file") MultipartFile file,@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{

		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()>1)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				List<String> sheetNames=ExcelHelper.getSheetNames(file);
				for(String s:sheetNames)
				{
					if(s.equalsIgnoreCase("Actual sample"))
					{
						try {
							XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
							XSSFSheet sheet = wb.getSheet(s);
							int rows=sheet.getLastRowNum();
							XSSFRow firstRow=sheet.getRow(0);
							int totalCol=firstRow.getLastCellNum();
							for(int i=1;i<rows;i++)
							{
								XSSFRow row=sheet.getRow(i);
								String party=row.getCell(0).toString();
								List<Party> partyList=partyServive.getParty(party);
								if(partyList!=null && partyList.size()>0)
								{
									Party pty=partyList.get(0);
									String brandstr=row.getCell(1).toString();
									List<Brand> brandList=brandService.getBrandForName(brandstr);
									if(brandList!=null && brandList.size()>0)
									{
										Brand brand=brandList.get(0);
										for(int k=2;k<totalCol;k++)
										{
											try {
												Date date=Util.convertStringToDate(firstRow.getCell(k).toString());
												double value=Double.parseDouble(row.getCell(k).toString());
												if(value>=0  && date!=null)
												{
													List<Actual> actualList=actualService.getActuals(brand, pty, Util.setLastDayOfMonth(date));
													Actual actual=null;
													if(actualList!=null && actualList.size()>0)
													{
														actual=actualList.get(0);
													}
													else
														actual=new Actual();
													actual.setBrand(brand);
													actual.setDate(Util.setLastDayOfMonth(date));
													actual.setParty(pty);
													actual.setValue(value);
													actualService.save(actual);

												}
											}
											catch(Exception ex)
											{

											}
										}

									}
								}

							}

						}
						catch(Exception ex) {ex.printStackTrace();}
					}	

				}
				return "uploadactual";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}

	}	

	@PostMapping("/uploadHeadCountData")
	public String fileUploadHeadCount(@RequestParam("file") MultipartFile file,@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{

		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));





				model.addAttribute("userName", user.getAttribute("email"));
				List<String> sheetNames=ExcelHelper.getSheetNames(file);
				for(String s:sheetNames)
				{
					if(s.equalsIgnoreCase("Input sheet_2"))
					{
						try {
							XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
							XSSFSheet sheet = wb.getSheet(s);
							for(int i=1;i<=sheet.getLastRowNum();i++)
							{

								if(sheet.getRow(i).getCell(0)!=null)
								{
									String empcode=sheet.getRow(i).getCell(0).toString().trim();

									List<Employee> empList=empService.getEmployeeList(empcode,Util.getDateMinusMonth(1));
									Employee emp=null;
									if(empList!=null && empList.size()>0)
									{
										emp=empList.get(0);
									}
									else
									{
										emp=new Employee();
										emp.setEmpcode(empcode);
									}
									String maDepartment=sheet.getRow(i).getCell(1).toString().trim();
									List<MainDepartment> mainDepartmentList=mainDepartmentService.getMainDepartmentList(maDepartment);
									MainDepartment mainDepartment=null;
									if(mainDepartmentList!=null && mainDepartmentList.size()>0)
									{
										mainDepartment=mainDepartmentList.get(0);
									}
									else
									{
										mainDepartment=new MainDepartment();
										mainDepartment.setName(maDepartment);
										mainDepartmentService.save(mainDepartment);
									}


									String submaDepartment=sheet.getRow(i).getCell(2).toString().trim();
									List<SubDepartment> subDepartmentList=subDepartmentService.getSubDepartmentList(submaDepartment);
									SubDepartment subDepartment=null;
									if(subDepartmentList!=null && subDepartmentList.size()>0)
									{
										subDepartment=subDepartmentList.get(0);
									}
									else
									{
										subDepartment=new SubDepartment();
										subDepartment.setName(submaDepartment);
										subDepartment.setMainDepartment(mainDepartment);
										subDepartmentService.save(subDepartment);

									}


									emp.setSubdepartment(subDepartment);
									try {

										String reportManager=sheet.getRow(i).getCell(3).toString().trim();
										List<Employee> employeeList=empService.getEmployeeList(reportManager);
										if(employeeList!=null && employeeList.size()>0)
										{
											emp.setManager(employeeList.get(0));
										}
									}
									catch(Exception ex) {}


									try{String doj=sheet.getRow(i).getCell(4).toString().trim();
									System.out.println(doj);
									emp.setDoj(Util.convertStringToDate(doj));}catch(Exception ex) {ex.printStackTrace();}


									try{String lwd=sheet.getRow(i).getCell(5).toString().trim();
									emp.setLwd(Util.convertStringToDate(lwd));}catch(Exception ex) {}


									try{String contractExpiryDate=sheet.getRow(i).getCell(6).toString().trim();
									emp.setContractExpiryDate(Util.convertStringToDate(contractExpiryDate));}catch(Exception ex) {}


									try{emp.setCtc(Double.parseDouble(sheet.getRow(i).getCell(7).toString().trim())*100000);}catch(Exception ex) {}
									try{emp.setEmpType(sheet.getRow(i).getCell(8).toString().trim());}catch(Exception ex) {}


									emp.setDate(Util.getDateMinusMonth(1));

									Employee empSaved= empService.save(emp);

									try {
										String[] brandList=sheet.getRow(i).getCell(9).toString().trim().split(",");
										for(String brandWithPercent:brandList)
										{
											String[] brandpercentsplit=brandWithPercent.split("\\(");
											String percent=brandpercentsplit[1].trim().replace("%)", "");
											List<Brand> brandlst=brandService.getBrandForName(brandpercentsplit[0].trim().toUpperCase());
											if(brandlst!=null && brandlst.size()>0)
											{
												List<BrandEmployee> brandEmployeeList=brandEmployeeService.getBrandEmployeeList(brandlst.get(0), emp);
												if(brandEmployeeList!=null && brandEmployeeList.size()>0)
												{
													BrandEmployee brandEmployee=brandEmployeeList.get(0);
													brandEmployee.setPercent(Double.parseDouble(percent));
													brandEmployee.setEmployee(empSaved);
													brandEmployeeService.save(brandEmployee);
												}
												else
												{
													BrandEmployee brandEmployee=new BrandEmployee();
													brandEmployee.setBrand(brandlst.get(0));
													brandEmployee.setEmployee(empSaved);
													brandEmployee.setPercent(Double.parseDouble(percent));
													brandEmployeeService.save(brandEmployee);
												}

											}
											else {
												Brand brand=new Brand();
												brand.setName(brandpercentsplit[0].trim().toUpperCase());

												brandService.save(brand);

												BrandEmployee brandEmployee=new BrandEmployee();
												brandEmployee.setBrand(brand);
												brandEmployee.setEmployee(empSaved);
												brandEmployee.setPercent(Double.parseDouble(percent.trim()));
												brandEmployeeService.save(brandEmployee);
											}


										}
									}
									catch(Exception ex) {}


									try {
										String[] buWithPercentList=sheet.getRow(i).getCell(10).toString().trim().split(",");


										for(String buWithPercent:buWithPercentList)
										{
											String[] buPercentSpllit=buWithPercent.trim().split("\\(");
											String percent=buPercentSpllit[1].trim().replace("%)", "");
											List<BusinessUnit> bulist=businessService.getBusinessUnitByName(buPercentSpllit[0].trim());
											if(bulist!=null && bulist.size()>0)
											{
												BusinessUnit bunit=bulist.get(0);
												List<BusinessUnitEmployee> buempList=buempService.getBusinessUnitEmployee(bunit, emp);
												if(buempList!=null && buempList.size()>0)
												{
													BusinessUnitEmployee buemp=buempList.get(0);
													buemp.setEmployee(empSaved);
													buemp.setPercent(Double.parseDouble(percent));
													buempService.save(buemp);
												}
												else
												{
													BusinessUnitEmployee buemp=new BusinessUnitEmployee();
													buemp.setEmployee(empSaved);
													buemp.setPercent(Double.parseDouble(percent.trim()));
													buemp.setBuunit(bunit);
													buempService.save(buemp);
												}

											}
										}
									}
									catch(Exception ex) {}

									empService.save(empSaved);
								}

							}




						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}

					}
				}

				return "uploadheadcount";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}



	}

	@PostMapping("/processrevenuedata")
	public String ProcessRevenueData(@RequestParam("file") MultipartFile file,@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{

		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()>1)
			{


				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));



				List<Brand> brandList=brandService.getAllBrands();
				ArrayList<String> brands=new ArrayList<>();
				List<BuRevenue> buRevList=revenueService.getBuByName("AD-SALES");
				for(Brand b:brandList)
					brands.add(b.getName());
				model.addAttribute("userName", user.getAttribute("email"));
				System.out.println(file);
				List<String> sheetNames=ExcelHelper.getSheetNames(file);
				for(String s:sheetNames)
				{
					if(brands.contains(s.toUpperCase()))
					{
						try {
							XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
							XSSFSheet sheet = wb.getSheet(s);
							int rows=sheet.getLastRowNum();
							XSSFRow firstRow=sheet.getRow(1);
							XSSFRow secondRow=sheet.getRow(2);
							int cols=firstRow.getLastCellNum();
							RevenueHead head=null;
							for(int k=3;k<=rows;k++)
							{
								try {	
									XSSFRow currentRow=sheet.getRow(k);

									RevenueSubHeads subhead=null;
									String headOrSubhead=currentRow.getCell(0).toString();
									if(!headOrSubhead.equalsIgnoreCase("Revenue (INR)"))
									{
										if(headOrSubhead.contains("##"))
										{
											headOrSubhead=headOrSubhead.replace("##", "");
											List<RevenueHead> headList=headService.getRevenueHeadByName(headOrSubhead);
											if(headList!=null && headList.size()>0)
											{
												head=headList.get(0);
												head.setBurevenue(buRevList.get(0));;
												headService.save(head);
											}
											else {
												head=new RevenueHead();
												head.setName(headOrSubhead);
												head.setBurevenue(buRevList.get(0));
												headService.save(head);
											}


										}
										else
										{
											List<RevenueSubHeads> headList=subheadService.getRevenueSubHeadByName(headOrSubhead,head);
											if(headList!=null && headList.size()>0)
												subhead=headList.get(0);
											else {
												subhead=new RevenueSubHeads();
												subhead.setName(headOrSubhead);
												subhead.setRevenuehead(head);
												subheadService.save(subhead);
											}
										}
										for(int i=1;i<cols;i++)
										{
											try {
												List<Brand> bList=brandService.getBrandForName(s);
												List<RevenueData> dataList=revenueDataService.getRevenueData(subhead, head, bList.get(0), Util.setFirstDayOfMonth(Util.convertStringToDate(firstRow.getCell(i).toString())));
												RevenueData data=null;
												if(dataList!=null && dataList.size()>0)
													data=dataList.get(0);
												else
													data=new RevenueData();
												data.setBrand(bList.get(0));
												data.setDate(Util.setFirstDayOfMonth(Util.convertStringToDate(firstRow.getCell(i).toString())));
												data.setRevenuehead(head);
												data.setRevenueSubHead(subhead);
												data.setValue(Double.parseDouble( currentRow.getCell(i).toString()));
												data.setUsdToInr(Float.parseFloat(secondRow.getCell(i).toString()));
												data.setBurevenue(buRevList.get(0));

												revenueDataService.save(data);
											}
											catch(Exception ex) {

											}

										}
									}
								}
								catch(Exception ex)
								{
									//ex.printStackTrace();
								}


							}
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					}
				}
				return "revenueUpload";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}




	}


	@PostMapping("/processPageViewData")
	public String fileUploadPageView(@RequestParam("file") MultipartFile file,@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{


		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));


				model.addAttribute("userName", user.getAttribute("email"));
				List<String> sheetNames=ExcelHelper.getSheetNames(file);
				for(String s:sheetNames)
				{
					if(s.equalsIgnoreCase("PV"))
					{
						try {
							XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
							XSSFSheet sheet = wb.getSheet(s);
							int rows=sheet.getLastRowNum();
							XSSFRow firstRow=sheet.getRow(0);
							int totalCol=firstRow.getLastCellNum();
							System.out.println("rows=="+rows);
							for(int k=1;k<=rows;k++)
							{
								XSSFRow row=sheet.getRow(k);
								try {


									String brand=row.getCell(0).toString();
									List<Brand> brandlist=brandService.getBrandForName(brand);


									for(int z=1;z<totalCol;z++)
									{
										Date date=Util.convertStringToDateFromMonthYear(firstRow.getCell(z).toString());
										double value=0;
										try{value= Double.parseDouble(row.getCell(z).toString());}catch(Exception ex) {ex.printStackTrace();};
										if(value>0)
										{
											List<PageView> pageviewlist=pageViewService.getPageView(brandlist.get(0), Util.setLastDayOfMonth(date));
											PageView pageView=null;
											if(pageviewlist!=null && pageviewlist.size()>0)
											{
												pageView=pageviewlist.get(0);

											}
											else
											{
												pageView=new PageView();
											}
											pageView.setBrand(brandlist.get(0));
											pageView.setDate(Util.setLastDayOfMonth(date));
											pageView.setPageview(value);
											pageViewService.save(pageView);

										}

									}



								}
								catch(Exception ex)
								{
									ex.printStackTrace();
								}

							}
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}

					}
				}

				return "pageviewreport";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}




	}


	@PostMapping("/uploadExpenseData")
	public String fileUploadExpense(@RequestParam("file") MultipartFile file,@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{



		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));





				model.addAttribute("userName", user.getAttribute("email"));
				List<String> sheetNames=ExcelHelper.getSheetNames(file);
				for(String s:sheetNames)
				{
					if(s.equalsIgnoreCase("Input sheet_1"))
					{


						try {
							XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
							XSSFSheet sheet = wb.getSheet(s);
							int rows=sheet.getLastRowNum();
							XSSFRow firstRow=sheet.getRow(0);
							int totalCol=firstRow.getLastCellNum();
							for(int k=1;k<rows;k++)
							{
								XSSFRow row=sheet.getRow(k);
								if( row!=null &&  row.getCell(0)!=null &&  row.getCell(0).toString().length()>0) {

									try {
										String partyName=row.getCell(0).toString();
										String poc=row.getCell(1).toString();
										String team=row.getCell(2).toString();
										String purpose=row.getCell(3).toString();
										String L1=row.getCell(4).toString();
										String L2=row.getCell(5).toString();
										String brand=row.getCell(6).toString();
										String bu=row.getCell(7).toString();
										String allocation=row.getCell(8).toString();
										String billingCriteria=row.getCell(9).toString();
										String conversion=row.getCell(10).toString();
										String units=row.getCell(11).toString();



										List<Party> partyList=partyServive.getParty(partyName);
										Party party=null;
										if(partyList!=null && partyList.size()>0)
										{
											party=partyList.get(0);
										}
										else
										{
											party=new Party();
											party.setName(partyName);
											partyServive.Save(party);
										}


										List<com.ie.Pulse.entity.L1> liList=l1Service.checkL1ExistInDatabase(L1);
										com.ie.Pulse.entity.L1 li=null;
										if(liList!=null && liList.size()>0)
										{
											li=liList.get(0);
										}
										else
										{
											li=new com.ie.Pulse.entity.L1();
											li.setName(L1);
											l1Service.save(li);
										}


										List<com.ie.Pulse.entity.L2> l2List=l2Service.checkL2ExistInDatabase(L2);
										com.ie.Pulse.entity.L2 l2=null;
										if(l2List!=null && l2List.size()>0)
										{
											l2=l2List.get(0);
											l2.setL1(li);
										}
										else
										{
											l2=new com.ie.Pulse.entity.L2();
											l2.setName(L2);
											l2.setL1(li);
											l2Service.save(l2);
										}

										for(int col=12;col<=totalCol;col++)
										{
											//System.out.println(firstRow.getCell(col));
											if(firstRow.getCell(col)!=null && Util.convertStringToDate(firstRow.getCell(col).toString())!=null)
											{
												try {
													List<ExpenseData> list=expenseDataService.checkExpenseExistInDatabaseBefore(party, firstRow.getCell(col).toString());
													ExpenseData  expense=null;
													if(list!=null && list.size()>0)
													{
														expense=list.get(0);
													}
													else
													{
														expense=new ExpenseData();
													}
													expense.setParty(party);
													expense.setPoc(poc);
													expense.setTeamName(team);
													expense.setPurpose(purpose);
													expense.setL2(l2);
													String[] brandStr=brand.split(",");



													for(int z=0;z<brandStr.length;z++)
													{
														List<Brand> brandList=brandService.getBrandForName(brandStr[z].trim().toUpperCase());
														Brand brad=null;
														if(brandList!=null && brandList.size()>0)
														{
															brad=brandList.get(0);
														}
														else
														{
															brad=new Brand();
															brad.setName(brandStr[z].trim().toUpperCase());
															brandService.save(brad);
														}


														brad.getExpenseData().add(expense);
														expense.getBrands().add(brad);
													}



													List<BusinessUnit> buList=businessService.getBusinessUnitByName(bu);
													BusinessUnit buunit=null;
													if(buList!=null && buList.size()>0)
													{
														buunit=buList.get(0);
													}
													else
													{
														buunit=new BusinessUnit();
														buunit.setName(bu);
														businessService.save(buunit);
													}
													expense.getBulist().add(buunit);
													buunit.getExpenseList().add(expense);

													expense.setAllocation(allocation);
													expense.setBillingCriteria(billingCriteria);
													if(conversion.equalsIgnoreCase("na"))
													{
														expense.setConversion(1);
													}
													else
													{
														expense.setConversion(Double.parseDouble(conversion));
													}
													expense.setUnits(units);
													expense.setDate(Util.convertStringToDate( firstRow.getCell(col).toString()));

													expense.setValue(Double.parseDouble(row.getCell(col).toString()));
													expenseDataService.save(expense);
												}
												catch(Exception a)
												{
													//System.out.println("row=="+k+" col=="+row.getCell(col).toString()+"month=="+firstRow.getCell(col));
												}
											}
										}
									}
									catch(Exception an)
									{

									}
								}
							}



						}catch(Exception ex)
						{

							ex.printStackTrace();
						}



					}

				}
				return "uploadexpense";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}



	}


	@GetMapping("/uploadPageViewData")
	public String displaypageviewReport(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0 && userlist.get(0).getLevel()>1)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));
				return "pageviewreport";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}

	}


	@GetMapping("/displayheadCountReport")
	public String displayHeadCountReport(@AuthenticationPrincipal OAuth2User user,ModelMap model)
	{
		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));

				return "headcountreport";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}


	}



	@GetMapping("/dashboardreport")
	public String getDashboardReport(@AuthenticationPrincipal OAuth2User user,ModelMap model,@RequestParam String startDate,@RequestParam String endDate,@RequestParam String brandId,@RequestParam String combrandId)
	{

		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));




				Map<String, Map<String, ProfitLoss>> profitLoss=new HashMap<>();

				Brand brandObj=brandService.getBrandById(Long.parseLong(brandId));
				Brand combrand=null;
				if(!combrandId.equalsIgnoreCase("-1"))
				{
					combrand=brandService.getBrandById(Long.parseLong(combrandId));
				}


				NumberFormat nf = DecimalFormat.getInstance();
				nf.setMaximumFractionDigits(0);
				nf.setGroupingUsed(false);

				Map<String,Map<String, Double>> expense=getExpenseBrandWise(startDate, endDate);
				List<Object[]> revenueDataList =revenueDataService.getAccumulatedRevenueDataBasedonBrand(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)));




				for(Object[] obj:revenueDataList)
				{
					String monthYear=obj[1].toString();
					double revenue=Double.parseDouble(obj[2].toString());
					Brand brand=brandService.getBrandById(Long.parseLong(obj[0].toString()));
					Map<String, Double> expMonthMap=expense.get(brand.getName());
					if(profitLoss.containsKey(brand.getName()))
					{
						Map<String, ProfitLoss> monthmap=profitLoss.get(brand.getName());
						ProfitLoss pnl=new ProfitLoss();
						pnl.setBrand(brand.getName());
						pnl.setRevenue(revenue);
						if(expMonthMap!=null && expMonthMap.containsKey(monthYear))
						{
							pnl.setExpense(expMonthMap.get(monthYear));
							pnl.setMonthYear(monthYear);
							pnl.setProfit(pnl.getRevenue()-pnl.getExpense());
							double percentProfit=(pnl.getProfit()/pnl.getRevenue())*100;
							BigDecimal bdUp=new BigDecimal(percentProfit).setScale(2,RoundingMode.UP);
							pnl.setProfitPercent(bdUp.doubleValue());
						}

						monthmap.put(monthYear, pnl);
						profitLoss.put(brand.getName(), monthmap);



					}
					else
					{
						ProfitLoss pnl=new ProfitLoss();
						pnl.setBrand(brand.getName());
						pnl.setRevenue(revenue);
						if(expMonthMap!=null && expMonthMap.containsKey(monthYear))
						{
							pnl.setExpense(expMonthMap.get(monthYear));
							pnl.setMonthYear(monthYear);
							pnl.setProfit(pnl.getRevenue()-pnl.getExpense());
							double percentProfit=(pnl.getProfit()/pnl.getRevenue())*100;
							BigDecimal bdUp=new BigDecimal(percentProfit).setScale(2,RoundingMode.UP);
							pnl.setProfitPercent(bdUp.doubleValue());
						}
						Map<String, ProfitLoss> monthmap=new HashMap<>();
						monthmap.put(monthYear, pnl);
						profitLoss.put(brand.getName(), monthmap);
					}
				}

				List<String> allDates= Util.getAllMonthsBetweenTwoDates(startDate, endDate);

				String dateStr="";
				SimpleDateFormat sdf=new SimpleDateFormat("MMM-yy");
				for(String s:allDates)
				{
					try {
						//datelist.add(Util.sdfDateOnly.parse(s+"-01"));
						dateStr+=sdf.format(Util.sdfDateOnly.parse(s+"-01"))+",";
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				Map<String, ProfitLoss> plmap=profitLoss.get(brandObj.getName());
				String revenueStr="";
				String expenseStr="";
				String pnl="";

				String comrevenueStr="";
				String comexpenseStr="";
				String compnl="";



				if(plmap!=null)
				{
					for(String s:allDates)
					{
						if(plmap.get(s)!=null)
						{
							revenueStr=revenueStr+nf.format(plmap.get(s).getRevenue())+",";
							expenseStr=expenseStr+nf.format(plmap.get(s).getExpense())+",";
							pnl=pnl+ nf.format(plmap.get(s).getProfitPercent())+",";
						}
						else
						{
							revenueStr=revenueStr+"0,";
							expenseStr=expenseStr+"0,";
							pnl=pnl+ "0,";
						}


					}
				}

				if(!(combrandId.equalsIgnoreCase("-1")) && combrand!=null)
				{
					Map<String, ProfitLoss> plmapcom=profitLoss.get(combrand.getName());
					for(String s:allDates)
					{
						if(plmapcom!=null && plmapcom.get(s)!=null)
						{
							comrevenueStr=comrevenueStr+nf.format(plmapcom.get(s).getRevenue())+",";
							comexpenseStr=comexpenseStr+nf.format(plmapcom.get(s).getExpense())+",";
							compnl=compnl+ nf.format(plmapcom.get(s).getProfitPercent())+",";
						}
						else
						{
							comrevenueStr=comrevenueStr+"0,";
							comexpenseStr=comexpenseStr+"0,";
							compnl=compnl+ "0,";
						}


					}
				}


				Map<String, Double> revenueWf=new HashMap<>();
				List<Object[]> accRevenueTwoDates=revenueDataService.getRevenueDataAccumulated(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)), Long.parseLong(brandId));
				if(accRevenueTwoDates!=null && accRevenueTwoDates.size()>0)
				{
					for(Object[] obj :accRevenueTwoDates)
					{
						BuRevenue buRevenue=revenueService.getBuRevenueById(Long.parseLong(obj[0].toString()));
						if(revenueWf.containsKey(buRevenue.getName()))
						{
							double sum=revenueWf.get(buRevenue.getName());
							sum=sum+Double.parseDouble(obj[1].toString());
							BigDecimal bdUp=new BigDecimal(sum).setScale(2,RoundingMode.UP);
							revenueWf.put(buRevenue.getName(), bdUp.doubleValue());
						}
						else
						{
							BigDecimal bdUp=new BigDecimal(Double.parseDouble(obj[1].toString())).setScale(2,RoundingMode.UP);
							revenueWf.put(buRevenue.getName(),  bdUp.doubleValue());
						}
					}
				}

				Map<String, Double>  expenseWf=  getExpenseBrandWiseForL1(startDate,  endDate,brandObj.getName());

				String revenuewfStr="";
				double totRevenue=0;
				for (Map.Entry<String,Double> revenuewfMap : revenueWf.entrySet())
				{
					BigDecimal bdUp=new BigDecimal(revenuewfMap.getValue()).setScale(2,RoundingMode.UP);
					revenuewfStr=revenuewfStr+revenuewfMap.getKey()+":"+bdUp.doubleValue()+",";
					totRevenue=totRevenue+revenuewfMap.getValue();
				}
				String expensewfStr="";
				double totExpense=0;
				for (Map.Entry<String,Double> expenseWfMap : expenseWf.entrySet())
				{
					BigDecimal bdUp=new BigDecimal(expenseWfMap.getValue()).setScale(2,RoundingMode.UP);
					expensewfStr=expensewfStr+expenseWfMap.getKey()+":"+bdUp.doubleValue()+",";
					totExpense=totExpense+expenseWfMap.getValue();
				}

				double pl=totRevenue-totExpense;


				model.addAttribute("revenuewfStr", revenuewfStr);
				model.addAttribute("expensewfStr", expensewfStr);
				BigDecimal bdUp=new BigDecimal(pl).setScale(2,RoundingMode.UP);
				model.addAttribute("pl", String.valueOf(bdUp.doubleValue()));

				model.addAttribute("dateStr", dateStr);
				model.addAttribute("revenuestr", revenueStr);
				model.addAttribute("expenseStr", expenseStr);
				model.addAttribute("comrevenueStr", comrevenueStr);
				model.addAttribute("comexpenseStr", comexpenseStr);
				model.addAttribute("compnl", compnl);
				model.addAttribute("pnl", pnl);
				model.addAttribute("firstbrand", brandObj.getName());
				if(combrand!=null)
					model.addAttribute("secondbrand", combrand.getName());
				else
					model.addAttribute("secondbrand", "");

				return "dashboardunits";

			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}
	}


	@GetMapping("/profillossreport")
	public String getProfitLossReport(@AuthenticationPrincipal OAuth2User user,ModelMap model,@RequestParam String startDate,@RequestParam String endDate)
	{

		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));





				Map<String, Map<String, ProfitLoss>> profitLoss=new HashMap<>();

				Map<String,Map<String, Double>> expense=getExpenseBrandWise(startDate, endDate);
				List<Object[]> revenueDataList =revenueDataService.getAccumulatedRevenueDataBasedonBrand(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)));

				for(Object[] obj:revenueDataList)
				{
					String monthYear=obj[1].toString();
					double revenue=Double.parseDouble(obj[2].toString());
					Brand brand=brandService.getBrandById(Long.parseLong(obj[0].toString()));
					Map<String, Double> expMonthMap=expense.get(brand.getName());
					if(profitLoss.containsKey(brand.getName()))
					{
						Map<String, ProfitLoss> monthmap=profitLoss.get(brand.getName());
						ProfitLoss pnl=new ProfitLoss();
						pnl.setBrand(brand.getName());
						pnl.setRevenue(revenue);
						if(expMonthMap!=null && expMonthMap.containsKey(monthYear))
						{
							pnl.setExpense(expMonthMap.get(monthYear));
							pnl.setMonthYear(monthYear);
							pnl.setProfit(pnl.getRevenue()-pnl.getExpense());
							double percentProfit=(pnl.getProfit()/pnl.getRevenue())*100;
							BigDecimal bdUp=new BigDecimal(percentProfit).setScale(2,RoundingMode.UP);
							pnl.setProfitPercent(bdUp.doubleValue());
						}

						monthmap.put(monthYear, pnl);
						profitLoss.put(brand.getName(), monthmap);



					}
					else
					{
						ProfitLoss pnl=new ProfitLoss();
						pnl.setBrand(brand.getName());
						pnl.setRevenue(revenue);
						if(expMonthMap!=null && expMonthMap.containsKey(monthYear))
						{
							pnl.setExpense(expMonthMap.get(monthYear));
							pnl.setMonthYear(monthYear);
							pnl.setProfit(pnl.getRevenue()-pnl.getExpense());
							double percentProfit=(pnl.getProfit()/pnl.getRevenue())*100;
							BigDecimal bdUp=new BigDecimal(percentProfit).setScale(2,RoundingMode.UP);
							pnl.setProfitPercent(bdUp.doubleValue());
						}
						Map<String, ProfitLoss> monthmap=new HashMap<>();
						monthmap.put(monthYear, pnl);
						profitLoss.put(brand.getName(), monthmap);




					}



				}

				List<String> allDates= Util.getAllMonthsBetweenTwoDates(startDate, endDate);
				
				List<Date> datelist=new ArrayList<>();
				for(String s:allDates)
				{
					try {
						datelist.add(Util.sdfDateOnly.parse(s+"-01"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				model.addAttribute("datelist", datelist);
				model.addAttribute("profitloss", profitLoss);
				model.addAttribute("datestrlist", allDates);
				model.addAttribute("brandlist", userlist.get(0).getBrands());
				return "profitlossunits";
			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}



	}



	@GetMapping("/revenuereport")
	public String getRevenueReport(@AuthenticationPrincipal OAuth2User user,ModelMap model,@RequestParam String startDate,@RequestParam String endDate,@RequestParam String reportType )
	{

		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{
				Set<Brand> brands=userlist.get(0).getBrands();
				model.addAttribute("brand", brands);
				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));

				if(reportType.equalsIgnoreCase("bucketwise") && userlist.get(0).getLevel()==3)
				{

					List<Object[]> revList=revenueDataService.getAccumulatedRevenueData(Util.convertStringToDateWithMonth(startDate), Util.convertStringToDateWithMonth(endDate));
					Map<String,Map<String,Map<String, Map<String,Double>>>> headbrandMap=new HashMap<>();
					Map<String,Map<String,Map<String,Double>>> sumheadMap=new HashMap<>();
					Map<String,Map<String,Double>> buTotalMap=new HashMap<>();
					for(Object[] obj :revList)
					{
						Brand brand=brandService.getBrandById(Long.parseLong(obj[2].toString()));
						RevenueHead head=headService.getRevenueDateById(Long.parseLong(obj[1].toString()));
						BuRevenue buhead=revenueService.getBuRevenueById(Long.parseLong(obj[0].toString()));
						String key=obj[3].toString();
						double value=Double.parseDouble(obj[4].toString());

						if(buTotalMap.containsKey(buhead.getName()))
						{
							Map<String, Double> monthMap=buTotalMap.get(buhead.getName());
							if(monthMap.containsKey(key))
							{
								double sum=monthMap.get(key);
								sum=sum+value;
								monthMap.put(key, sum);
								buTotalMap.put(buhead.getName(), monthMap);
							}
							else
							{
								monthMap.put(key,value);
								buTotalMap.put(buhead.getName(), monthMap);
							}



						}
						else
						{
							Map<String, Double> monthMap=new HashMap<>();
							monthMap.put(key,value);
							buTotalMap.put(buhead.getName(), monthMap);
						}



						if(sumheadMap.containsKey(buhead.getName()))
						{
							Map<String,Map<String, Double>> headmap=sumheadMap.get(buhead.getName());
							if(headmap.containsKey(head.getName()))
							{
								Map<String, Double> monthMap=headmap.get(head.getName());
								if(monthMap.containsKey(key))
								{
									double sum=monthMap.get(key);
									sum=sum+value;
									monthMap.put(key, sum);
									headmap.put(head.getName(), monthMap);
									sumheadMap.put(buhead.getName(), headmap);
								}
								else
								{
									monthMap.put(key,value);
									headmap.put(head.getName(), monthMap);
									sumheadMap.put(buhead.getName(), headmap);
								}
							}

							else
							{
								Map<String, Double> monthMap=new HashMap<>();
								monthMap.put(key,value);
								headmap.put(head.getName(), monthMap);
								sumheadMap.put(buhead.getName(), headmap);
							}


						}
						else
						{
							Map<String, Double> monthMap=new HashMap<>();
							monthMap.put(key,value);
							Map<String,Map<String,Double>> headmap=new HashMap<>();
							headmap.put(head.getName(), monthMap);
							sumheadMap.put(buhead.getName(), headmap);


						}


						if(headbrandMap.containsKey(buhead.getName()))
						{
							Map<String,Map<String,Map<String, Double>>> headMap=headbrandMap.get(buhead.getName());
							if(headMap.containsKey(head.getName()))
							{
								Map<String,Map<String, Double>> brandmap=headMap.get(head.getName());
								if(brandmap.containsKey(brand.getName()))		
								{
									Map<String, Double> monthMap =brandmap.get(brand.getName());
									monthMap.put(key, value);
									brandmap.put(brand.getName(), monthMap);
									headMap.put(head.getName(), brandmap);
									headbrandMap.put(buhead.getName(), headMap);
								}
								else
								{


									Map<String, Double> monthMap =new HashMap<>();
									monthMap.put(key, value);
									brandmap.put(brand.getName(), monthMap);
									headMap.put(head.getName(), brandmap);
									headbrandMap.put(buhead.getName(), headMap);

								}



							}
							else
							{
								Map<String, Double> monthMap=new HashMap<>();
								monthMap.put(key, value);
								Map<String,Map<String, Double>> bmap=new HashMap<>();
								bmap.put(brand.getName(), monthMap);
								headMap.put(head.getName(), bmap);
								headbrandMap.put(buhead.getName(), headMap);
							}


						}
						else
						{
							Map<String, Double> monthMap=new HashMap<>();
							monthMap.put(key, value);
							Map<String,Map<String, Double>> bmap=new HashMap<>();
							bmap.put(brand.getName(), monthMap);
							Map<String,Map<String,Map<String, Double>>> hmap=new HashMap<>();
							hmap.put(head.getName(), bmap);
							headbrandMap.put(buhead.getName(), hmap);
						}

					}
					List<String> allDates= Util.getAllMonthsBetweenTwoDates(startDate, endDate);
					List<Date> datelist=new ArrayList<>();
					for(String s:allDates)
					{
						try {
							datelist.add(Util.sdfDateOnly.parse(s+"-01"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					model.addAttribute("butotalMap", buTotalMap);
					model.addAttribute("datelist", datelist);
					model.addAttribute("headbrandMap", headbrandMap);
					model.addAttribute("datestrlist", allDates);
					model.addAttribute("sumheadMap", sumheadMap);
					return "revenuebucketwiseunits";



				}

				else if(reportType.equalsIgnoreCase("sitewise"))
				{
					Map<String, Map<String,Double>> brandTotal=new HashMap<>();
					Map<String,Map<String,Map<String, Map<String,Double>>>> headTotal=new HashMap<>();
					Map<String,Map<String, Map<String,Double>>> buTotal=new HashMap<>();

					List<RevenueData> revList=revenueDataService.getRevenueData(Util.convertStringToDateWithMonth(startDate), Util.convertStringToDateWithMonth(endDate));
					Map<String,Map<String, Map<String, Map<String, Map<String, Double>>>>> revenuemap=new HashMap<>();
					if(revList!=null && revList.size()>0)
					{
						for(int i=0;i<revList.size();i++)
						{
							RevenueData data=revList.get(i);
							String lastKey=Util.getYearMonth(data.getDate());

							if(brandTotal.containsKey(data.getBrand().getName()))
							{

								Map<String, Double> monthMap=brandTotal.get(data.getBrand().getName());
								if(monthMap.containsKey(lastKey))
								{
									double value=monthMap.get(lastKey);
									double sum=value+data.getValue();
									monthMap.put(lastKey, sum);
									brandTotal.put(data.getBrand().getName(), monthMap);
								}
								else
								{
									monthMap.put(lastKey, data.getValue());
									brandTotal.put(data.getBrand().getName(), monthMap);
								}
							}
							else
							{
								Map<String, Double> monthMap=new HashMap<>();
								monthMap.put(lastKey, data.getValue());
								brandTotal.put(data.getBrand().getName(), monthMap);
							}

							if(headTotal.containsKey(data.getBrand().getName()))
							{
								Map<String,Map<String, Map<String, Double>>> bumap=headTotal.get(data.getBrand().getName());


								if(bumap.containsKey(data.getBurevenue().getName()))
								{
									Map<String,Map<String, Double>> headmap=bumap.get(data.getBurevenue().getName());
									if(headmap.containsKey(data.getRevenuehead().getName()))
									{
										Map<String, Double> monthmap=headmap.get(data.getRevenuehead().getName());
										if(monthmap.containsKey(lastKey))
										{
											double value =monthmap.get(lastKey);
											double sum=value+data.getValue();
											monthmap.put(lastKey, sum);
											headmap.put(data.getRevenuehead().getName(), monthmap);
											bumap.put(data.getBurevenue().getName(), headmap);
											headTotal.put(data.getBrand().getName(), bumap);
										}
										else
										{
											monthmap.put(lastKey, data.getValue());
											headmap.put(data.getRevenuehead().getName(), monthmap);
											bumap.put(data.getBurevenue().getName(), headmap);
											headTotal.put(data.getBrand().getName(), bumap);
										}
									}
									else
									{
										Map<String, Double> monthMap=new HashMap<>();
										monthMap.put(lastKey, data.getValue());
										headmap.put(data.getRevenuehead().getName(), monthMap);
										bumap.put(data.getBurevenue().getName(), headmap);
										headTotal.put(data.getBrand().getName(), bumap);
									}



								}
								else
								{
									Map<String, Double> monthMap=new HashMap<>();
									monthMap.put(lastKey, data.getValue());
									Map<String, Map<String, Double>> headmap=new HashMap<>();
									headmap.put(data.getRevenuehead().getName(), monthMap);

									bumap.put(data.getBurevenue().getName(), headmap);
									headTotal.put(data.getBrand().getName(), bumap);
								}


							}
							else
							{

								Map<String, Double> monthMap=new HashMap<>();
								monthMap.put(lastKey, data.getValue());
								Map<String, Map<String, Double>> headmap=new HashMap<>();
								headmap.put(data.getRevenuehead().getName(), monthMap);
								Map<String,Map<String, Map<String, Double>>> bumap=new HashMap<>();
								bumap.put(data.getBurevenue().getName(), headmap);
								headTotal.put(data.getBrand().getName(), bumap);
							}

							if(buTotal.containsKey(data.getBrand().getName()))
							{
								Map<String, Map<String, Double>> bumap=buTotal.get(data.getBrand().getName());


								if(bumap.containsKey(data.getBurevenue().getName()))
								{
									Map<String, Double> monthmap=bumap.get(data.getBurevenue().getName());
									if(monthmap.containsKey(lastKey))
									{
										double value =monthmap.get(lastKey);
										double sum=value+data.getValue();
										monthmap.put(lastKey, sum);
										bumap.put(data.getBurevenue().getName(), monthmap);
										buTotal.put(data.getBrand().getName(), bumap);
									}
									else
									{

										monthmap.put(lastKey, data.getValue());
										bumap.put(data.getBurevenue().getName(), monthmap);
										buTotal.put(data.getBrand().getName(), bumap);

									}
								}
								else
								{
									Map<String, Double> monthMap=new HashMap<>();
									monthMap.put(lastKey, data.getValue());
									bumap.put(data.getBurevenue().getName(), monthMap);
									buTotal.put(data.getBrand().getName(), bumap);
								}


							}
							else
							{

								Map<String, Double> monthMap=new HashMap<>();
								monthMap.put(lastKey, data.getValue());
								Map<String, Map<String, Double>> bumap=new HashMap<>();
								bumap.put(data.getBurevenue().getName(), monthMap);
								buTotal.put(data.getBrand().getName(), bumap);
							}


							if(data.getBrand().getName().equalsIgnoreCase("IE"))
							{
								System.out.println("hi how r u");
							}

							if(revenuemap.containsKey(data.getBrand().getName()))
							{
								Map<String,Map<String,Map<String,Map<String, Double>>>> buheadmap=revenuemap.get(data.getBrand().getName());
								if(buheadmap.containsKey(data.getBurevenue().getName()))
								{
									Map<String,Map<String,Map<String, Double>>> headmap=buheadmap.get(data.getBurevenue().getName());
									if(headmap.containsKey(data.getRevenuehead().getName()))
									{
										Map<String,Map<String, Double>> subheadmap=headmap.get(data.getRevenuehead().getName());
										String key="";
										if(data.getRevenueSubHead()==null)
											key=data.getRevenuehead().getName();
										else
											key=data.getRevenueSubHead().getName();
										if(subheadmap.containsKey(key))
										{
											Map<String, Double> monthMap=subheadmap.get(key);
											monthMap.put(lastKey, data.getValue());
											if(data.getRevenueSubHead()==null)
												subheadmap.put(data.getRevenuehead().getName(), monthMap);
											else
												subheadmap.put(data.getRevenueSubHead().getName(), monthMap);
											headmap.put(data.getRevenuehead().getName(), subheadmap);
											buheadmap.put(data.getBurevenue().getName(), headmap);
											revenuemap.put(data.getBrand().getName(), buheadmap);

										}
										else
										{

											Map<String, Double> monthMap=new HashMap<>();
											monthMap.put(lastKey, data.getValue());
											if(data.getRevenueSubHead()==null)
												subheadmap.put(data.getRevenuehead().getName(), monthMap);
											else
												subheadmap.put(data.getRevenueSubHead().getName(), monthMap);
											headmap.put(data.getRevenuehead().getName(), subheadmap);
											buheadmap.put(data.getBurevenue().getName(), headmap);
											revenuemap.put(data.getBrand().getName(), buheadmap);

										}


									}
									else
									{
										Map<String, Double> monthMap=new HashMap<>();
										monthMap.put(lastKey, data.getValue());
										Map<String,Map<String, Double>> subheadmap=new HashMap<>();
										if(data.getRevenueSubHead()==null)
											subheadmap.put(data.getRevenuehead().getName(), monthMap);
										else
											subheadmap.put(data.getRevenueSubHead().getName(), monthMap);
										headmap.put(data.getRevenuehead().getName(), subheadmap);
										buheadmap.put(data.getBurevenue().getName(), headmap);
										revenuemap.put(data.getBrand().getName(), buheadmap);

									}
								}
								else
								{

									Map<String, Double> monthMap=new HashMap<>();
									monthMap.put(lastKey, data.getValue());
									Map<String,Map<String, Double>> subheadmap=new HashMap<>();
									if(data.getRevenueSubHead()==null)
										subheadmap.put(data.getRevenuehead().getName(), monthMap);
									else
										subheadmap.put(data.getRevenueSubHead().getName(), monthMap);
									Map<String,Map<String,Map<String, Double>>> headmap=new HashMap<>();
									headmap.put(data.getRevenuehead().getName(), subheadmap);
									buheadmap.put(data.getBurevenue().getName(), headmap);
									revenuemap.put(data.getBrand().getName(), buheadmap);


								}



							}
							else
							{
								Map<String, Double> monthMap=new HashMap<>();
								monthMap.put(lastKey, data.getValue());
								Map<String,Map<String, Double>> subheadmap=new HashMap<>();
								if(data.getRevenueSubHead()==null)
									subheadmap.put(data.getRevenuehead().getName(), monthMap);
								else
									subheadmap.put(data.getRevenueSubHead().getName(), monthMap);
								Map<String,Map<String,Map<String, Double>>> headmap=new HashMap<>();
								headmap.put(data.getRevenuehead().getName(), subheadmap);
								Map<String,Map<String,Map<String,Map<String, Double>>>> burevenueMap=new HashMap<>();
								burevenueMap.put(data.getBurevenue().getName(), headmap);
								revenuemap.put(data.getBrand().getName(), burevenueMap);
							}


						}


					}



					Set<Brand> allBrands=userlist.get(0).getBrands();
					ArrayList<String> brandStrList=new ArrayList<>();
					for(Brand brand:allBrands)
						brandStrList.add(brand.getName());
					List<String> allDates= Util.getAllMonthsBetweenTwoDates(startDate, endDate);
					List<Date> datelist=new ArrayList<>();
					for(String s:allDates)
					{
						try {
							datelist.add(Util.sdfDateOnly.parse(s+"-01"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					model.addAttribute("butotal", buTotal);
					model.addAttribute("brandlist", brandStrList);
					model.addAttribute("datelist", datelist);
					model.addAttribute("revenuemap", revenuemap);
					model.addAttribute("datestrlist", allDates);
					model.addAttribute("brandTotal", brandTotal);
					model.addAttribute("headTotal", headTotal);

					return "revenuereportunitssitewise";
				}

				else
				{
					return "notauthorized";
				}



			}
			else
			{
				return "notauthorized";
			}
		}
		else
		{
			return "notauthorized";
		}		




	}





	public Map<String,Map<String, Double>> getExpenseBrandWise(String  startDate,String  endDate)
	{
		Map<String,Map<String, Double>> brandTotalExpense=new HashMap<>();
		List<ExpenseData> expenseDataList=expenseDataService.getDateBetweenStartDateAndEndDate(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)));
		Map<String,Double> accPageView=expenseDataService.getMonthWiseAccumulatedPageView(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)));
		for(ExpenseData data:expenseDataList)
		{

			Map<String, Double> empPercent=empService.getEmployeeHeadCount(Util.setFirstDayOfMonth(data.getDate()));

			for(Brand b:data.getBrands())
			{
				double brandShare=-1;
				String monthDate=null;
				if(data.getAllocation().equalsIgnoreCase("As per PV"))
				{
					List<PageView> pageView=pageViewService.getPageView(b, Util.setLastDayOfMonth(data.getDate()));
					if(pageView!=null && pageView.size()>0)
					{
						double pageViewValue=pageView.get(0).getPageview();
						double brandPercent=pageViewValue/accPageView.get(Util.getYearMonth(data.getDate()));
						brandShare=brandPercent*data.getValue();
						monthDate=Util.getYearMonth(data.getDate());
					}
				}
				else if(data.getAllocation().equalsIgnoreCase("As per Actuals"))
				{
					List<Actual> actualList=actualService.getActuals(b, data.getParty(), Util.setLastDayOfMonth(data.getDate()));
					if(actualList!=null && actualList.size()>0)
					{
						monthDate=Util.getYearMonth(data.getDate());
						brandShare=actualList.get(0).getValue();

					}
				}
				else if(data.getAllocation().equalsIgnoreCase("As per HeadCount"))
				{
					try {
						monthDate=Util.getYearMonth(data.getDate());
						double brandPercent=empPercent.get(String.valueOf(b.getId()))/empPercent.get("total");
						brandShare=brandPercent*data.getValue();
					}
					catch(Exception ex) {}

				}

				if(brandShare>=0)
				{
					if(brandTotalExpense.containsKey(b.getName()))
					{
						Map<String, Double> monthMap=brandTotalExpense.get(b.getName());
						if(monthMap.containsKey(monthDate))
						{
							double sum=monthMap.get(monthDate);
							sum=sum+brandShare;
							BigDecimal bdUp=new BigDecimal(sum).setScale(2,RoundingMode.UP);
							monthMap.put(monthDate, bdUp.doubleValue());
							brandTotalExpense.put(b.getName(), monthMap);
						}
						else
						{
							BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
							monthMap.put(monthDate, bdUp.doubleValue());
							brandTotalExpense.put(b.getName(), monthMap);

						}


					}
					else
					{
						Map<String,Double> monthMap=new HashMap<>();
						BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
						monthMap.put(monthDate, bdUp.doubleValue());
						brandTotalExpense.put(b.getName(), monthMap);
					}



				}	
			}
		} 

		return brandTotalExpense;
	}



	public Map<String, Double> getExpenseBrandWiseForL1(String  startDate,String  endDate, String brandName)
	{
		Map<String, Double> brandTotalExpense=new HashMap<>();
		List<ExpenseData> expenseDataList=expenseDataService.getDateBetweenStartDateAndEndDate(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)));
		Map<String,Double> accPageView=expenseDataService.getMonthWiseAccumulatedPageView(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)));
		for(ExpenseData data:expenseDataList)
		{

			Map<String, Double> empPercent=empService.getEmployeeHeadCount(Util.setFirstDayOfMonth(data.getDate()));

			for(Brand b:data.getBrands())
			{
				if(b.getName().equalsIgnoreCase(brandName))
				{
					double brandShare=-1;

					if(data.getAllocation().equalsIgnoreCase("As per PV"))
					{
						List<PageView> pageView=pageViewService.getPageView(b, Util.setLastDayOfMonth(data.getDate()));
						if(pageView!=null && pageView.size()>0)
						{
							double pageViewValue=pageView.get(0).getPageview();
							double brandPercent=pageViewValue/accPageView.get(Util.getYearMonth(data.getDate()));
							brandShare=brandPercent*data.getValue();

						}
					}
					else if(data.getAllocation().equalsIgnoreCase("As per Actuals"))
					{
						List<Actual> actualList=actualService.getActuals(b, data.getParty(), Util.setLastDayOfMonth(data.getDate()));
						if(actualList!=null && actualList.size()>0)
						{
							brandShare=actualList.get(0).getValue();
						}
					}
					else if(data.getAllocation().equalsIgnoreCase("As per HeadCount"))
					{
						try {
							double brandPercent=empPercent.get(String.valueOf(b.getId()))/empPercent.get("total");
							brandShare=brandPercent*data.getValue();
						}
						catch(Exception ex) {}

					}

					if(brandShare>=0)
					{
						if(brandTotalExpense.containsKey(data.getL2().getL1().getName()))
						{
							double sum=brandTotalExpense.get(data.getL2().getL1().getName());
							sum=sum+brandShare;
							BigDecimal bdUp=new BigDecimal(sum).setScale(2,RoundingMode.UP);
							brandTotalExpense.put(data.getL2().getL1().getName(), bdUp.doubleValue());
						}
						else
						{
							BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
							brandTotalExpense.put(data.getL2().getL1().getName(), bdUp.doubleValue());
						}
					}	
				}
			}
		} 

		return brandTotalExpense;
	}







	@GetMapping("/headcountreportbasedbrand")
	public String getHeadCountReportbasedonbrand(@AuthenticationPrincipal OAuth2User user,ModelMap model,@RequestParam String startDate,@RequestParam String endDate,@RequestParam String reportType )
	{

		if(user!=null)
		{
			List<Users> userlist=userService.getUsersByEmail(user.getAttribute("email"));
			if(userlist!=null && userlist.size()>0)
			{


				model.addAttribute("level", userlist.get(0).getLevel());
				model.addAttribute("userName", user.getAttribute("email"));




				if(reportType.equalsIgnoreCase("headcount"))
				{
					List<Object[]> employee=empService.getEmployeesBasedOnBrandWith100Percent(Util.convertStringToDateWithMonth(startDate), Util.convertStringToDateWithMonth(endDate));
					Map<String, Map<String, HeadCountdto>> headCounts=new  HashMap<>();
					for(Object[] emp:employee)
					{
						if(headCounts.containsKey(emp[0].toString()))
						{
							Map<String, HeadCountdto> headCountMap=headCounts.get(emp[0].toString());
							if(headCountMap.containsKey(emp[2].toString()))
							{
								HeadCountdto dto=headCountMap.get(emp[2].toString());
								dto.setDate(Util.convertStringToDateStartWithMonth(emp[0].toString()));
								dto.setCountWith100Percent(Integer.parseInt(emp[1].toString()));
								dto.setBrandName(emp[2].toString());
								headCountMap.put(emp[2].toString(), dto);
								headCounts.put(emp[0].toString(), headCountMap);



							}
							else
							{
								HeadCountdto dto=new HeadCountdto();
								dto.setDate(Util.convertStringToDateStartWithMonth(emp[0].toString()));
								dto.setCountWith100Percent(Integer.parseInt(emp[1].toString()));
								dto.setBrandName(emp[2].toString());
								headCountMap.put(emp[2].toString(), dto);
								headCounts.put(emp[0].toString(), headCountMap);		
							}
						}
						else
						{
							Map<String, HeadCountdto> hc=new HashMap<String, HeadCountdto>();
							HeadCountdto dto=new HeadCountdto();
							dto.setDate(Util.convertStringToDateStartWithMonth(emp[0].toString()));
							dto.setCountWith100Percent(Integer.parseInt(emp[1].toString()));
							dto.setBrandName(emp[2].toString());
							hc.put(emp[2].toString(), dto);
							headCounts.put(emp[0].toString(), hc);
						}



					}

					List<Object[]> employeeList=empService.getEmployeesBasedOnBrandWithLessThan100Percent(Util.convertStringToDateWithMonth(startDate), Util.convertStringToDateWithMonth(endDate));
					for(Object[] emp:employeeList)
					{
						if(headCounts.containsKey(emp[0].toString()))
						{
							Map<String, HeadCountdto> headCountMap=headCounts.get(emp[0].toString());
							if(headCountMap.containsKey(emp[2].toString()))
							{
								HeadCountdto dto=headCountMap.get(emp[2].toString());
								dto.setDate(Util.convertStringToDateStartWithMonth(emp[0].toString()));
								dto.setCountWithLessThan100Percent((Integer.parseInt(emp[1].toString())));
								dto.setBrandName(emp[2].toString());
								headCountMap.put(emp[2].toString(), dto);
								headCounts.put(emp[0].toString(), headCountMap);



							}
							else
							{
								HeadCountdto dto=new HeadCountdto();
								dto.setDate(Util.convertStringToDateStartWithMonth(emp[0].toString()));
								dto.setCountWithLessThan100Percent(Integer.parseInt(emp[1].toString()));
								dto.setBrandName(emp[2].toString());
								headCountMap.put(emp[2].toString(), dto);
								headCounts.put(emp[0].toString(), headCountMap);		
							}
						}
						else
						{
							Map<String, HeadCountdto> hc=new HashMap<String, HeadCountdto>();
							HeadCountdto dto=new HeadCountdto();
							dto.setDate(Util.convertStringToDateStartWithMonth(emp[0].toString()));
							dto.setCountWithLessThan100Percent(Integer.parseInt(emp[1].toString()));
							dto.setBrandName(emp[2].toString());
							hc.put(emp[2].toString(), dto);
							headCounts.put(emp[0].toString(), hc);


						}
					}





					//List<Brand> allBrands=brandService.getAllBrands();
					List<Object[]> allDates=empService.getallDatesBetweenTwoDatesMonthWise(Util.convertStringToDateWithMonth(startDate), Util.convertStringToDateWithMonth(endDate));
					List<Map<String, Date>> dateMap=new ArrayList<Map<String,Date>>();
					for(Object[] date:allDates)
					{
						Map<String, Date> dm=new HashMap<>();
						dm.put(date[1].toString(),Util.convertStringToDateInYYYYMMDD(date[0].toString()));
						dateMap.add(dm);
						List<Object[]> total100PercentEmployees=empService.getuniqueemployee(Util.convertStringToDateInYYYYMMDD(date[0].toString()));
						List<Object[]> totallessThan100PercentEmployees=empService.getuniqueemployeeFromIntersection(Util.convertStringToDateInYYYYMMDD(date[0].toString()));
						Map<String, HeadCountdto> headCountMap=headCounts.get(date[1].toString());
						HeadCountdto dto=new HeadCountdto();
						dto.setCountWith100Percent(Integer.parseInt(total100PercentEmployees.get(0)[0].toString()));
						dto.setCountWithLessThan100Percent(Integer.parseInt(totallessThan100PercentEmployees.get(0)[0].toString()));
						headCountMap.put("total",dto);
						headCounts.put(date[1].toString(), headCountMap);
					}


					model.addAttribute("headcount", headCounts);
					model.addAttribute("brands", userlist.get(0).getBrands());
					model.addAttribute("dates", dateMap);



					return "headcountreportunits";
				}
				else if(reportType.equalsIgnoreCase("salarywise") ||reportType.equalsIgnoreCase("monthsalarywise"))
				{
					DecimalFormat df=new DecimalFormat("#");
					List<Employee> empList=empService.getEmployeeBetweenTwoDate(Util.convertStringToDateWithMonth(startDate), Util.convertStringToDateWithMonth(endDate));
					Map<String, Map<String, Map<String, Double>>> salaryDivision=new HashMap<>();
					if(empList!=null && empList.size()>0)
					{
						for(Employee emp:empList)
						{

							List<BrandEmployee> brandlist=emp.getBrandEmployeeList();
							Map<String, Double> sb=new HashMap<String, Double>();
							for(BrandEmployee be:brandlist)
							{
								String brand=be.getBrand().getName();
								double percent=be.getPercent();

								long diffDays=Util.getDifferenceDays(emp.getDoj(),Util.setLastDayOfMonth(emp.getDate()));
								double value=0;
								if(diffDays>0 &&  diffDays<30)
								{
									value=Double.parseDouble(df.format(((percent/100)*(emp.getCtc()/Util.getNoOfDaysInMonth(emp.getDoj())))*diffDays));

								}
								else if(diffDays<0)
								{
									value=0;
								}
								else
									value=Double.parseDouble(df.format(((percent/100)*emp.getCtc())));
								sb.put(brand, value);
							}
							if(salaryDivision.containsKey(emp.getEmpcode()))
							{
								Map<String, Map<String, Double>> dateBrandMap=salaryDivision.get(emp.getEmpcode());

								dateBrandMap.put(Util.getMonthYear(emp.getDate()), sb);
								salaryDivision.put(emp.getEmpcode(), dateBrandMap);
							}
							else
							{
								Map<String, Map<String, Double>> datesb=new HashMap<>();
								datesb.put(Util.getMonthYear(emp.getDate()), sb);
								salaryDivision.put(emp.getEmpcode(), datesb);


							}



						}



					}
					List<Brand> allBrands=brandService.getAllBrands();
					List<Object[]> allDates=empService.getallDatesBetweenTwoDatesMonthWise(Util.convertStringToDateWithMonth(startDate), Util.convertStringToDateWithMonth(endDate));
					List<String> dateList=new ArrayList<>();

					for(Object[] obj:allDates)
					{
						dateList.add(Util.getMonthYear(Util.convertStringToDateInYYYYMMDD(obj[0].toString())));
					}

					Map<String, Map<String, Double>> totalMap=new HashMap<>();
					if(salaryDivision!=null && salaryDivision.size()>0)
					{

						for(Map.Entry<String, Map<String, Map<String, Double>>> emp : salaryDivision.entrySet())
						{
							Map<String, Map<String, Double>> empCodeMap=salaryDivision.get(emp.getKey());
							for(String date:dateList)
							{
								Map<String, Double> partialTotalMap=null;
								if(totalMap.containsKey(date))
								{
									partialTotalMap=totalMap.get(date);
								}
								else
								{
									partialTotalMap=new HashMap<>();
								}
								Map<String, Double> monthMap=empCodeMap.get(date);
								for(Brand b:allBrands)
								{

									if(monthMap!=null &&  monthMap.containsKey(b.getName()))
									{
										double value=monthMap.get(b.getName());
										if(partialTotalMap.containsKey(b.getName()))
										{
											double totVal=partialTotalMap.get(b.getName());
											totVal=totVal+value;
											partialTotalMap.put(b.getName(), totVal);
										}
										else
										{
											partialTotalMap.put(b.getName(), value);

										}
									}


								}

								totalMap.put(date, partialTotalMap);
							}


						}



					}



					model.addAttribute("salaryDivision", salaryDivision);
					model.addAttribute("brands", userlist.get(0).getBrands());
					model.addAttribute("dates", dateList);
					model.addAttribute("total", totalMap);
					if(reportType.equalsIgnoreCase("monthsalarywise"))
					{
						return "monthwisesalaryreportunits";
					}
					else
					{
						return "salaryreportunits";
					}
				}
				else if(reportType.equalsIgnoreCase("l1l2vendorExpense") ||reportType.equalsIgnoreCase("l1l2vendorExpense"))
				{
					Map<String, Map<String,Map<String,Map<String,Double>>>> expenseMap=new HashMap<>();

					List<Object[]> list=expenseDataService.getExpenseDateAggregatedByL1L2Vendor(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)));
					if(list!=null && list.size()>0)
					{



						for(Object[] object:list)
						{

							L2 l2=l2Service.getL2(Long.parseLong(object[0].toString()));
							Party party=partyServive.getParty(Long.parseLong(object[1].toString()));
							String monthDate=object[2].toString();
							double value=Double.parseDouble(object[3].toString());


							// monthValue.put(monthDate, value);
							if(expenseMap.containsKey(l2.getL1().getName()))
							{
								Map<String,Map<String,Map<String,Double>>> l2map=expenseMap.get(l2.getL1().getName());
								if(l2map.containsKey(l2.getName()))
								{
									Map<String,Map<String,Double>> partyMap=l2map.get(l2.getName());

									if(partyMap.containsKey(party.getName()))
									{
										Map<String,Double> monthMap=partyMap.get(party.getName());
										monthMap.put(monthDate, value);
										partyMap.put(party.getName(), monthMap);
										l2map.put(l2.getName(), partyMap);

									}
									else
									{
										Map<String,Map<String,Double>> partymap=null;
										if(l2map.get(l2.getName())!=null)
											partymap=l2map.get(l2.getName());
										else
											partymap= new HashMap<>();


										Map<String, Double> monthValue=new HashMap<>();
										monthValue.put(monthDate, value);
										partymap.put(party.getName(), monthValue);
										l2map.put(l2.getName(), partymap);
										expenseMap.put(l2.getL1().getName(), l2map);

									}



								}
								else
								{
									Map<String,Map<String,Double>> partymap= new HashMap<>();
									Map<String, Double> monthValue=new HashMap<>();
									monthValue.put(monthDate, value);
									partymap.put(party.getName(), monthValue);
									l2map.put(l2.getName(), partymap);
									expenseMap.put(l2.getL1().getName(), l2map);
								}

							}
							else
							{
								Map<String,Map<String,Double>> vendormap=new HashMap<>();
								Map<String, Double> monthValue=new HashMap<>();
								monthValue.put(monthDate, value);
								vendormap.put(party.getName(), monthValue);
								Map<String,Map<String,Map<String,Double>>> l2map=new HashMap<>();
								l2map.put(l2.getName(), vendormap);
								expenseMap.put(l2.getL1().getName(), l2map);
							}




						}
					}
					List<String> allDates= Util.getAllMonthsBetweenTwoDates(startDate, endDate);
					List<Date> datelist=new ArrayList<>();
					for(String s:allDates)
					{
						try {
							datelist.add(Util.sdfDateOnly.parse(s+"-01"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					model.addAttribute("datelist", datelist);
					model.addAttribute("expensemap", expenseMap);
					model.addAttribute("datestrlist", allDates);

					return "l1l2vendorreportunits";



				}
				else if(reportType.equalsIgnoreCase("brandwiseexpense"))
				{
					Map<String, Map<String, Map<String,Map<String,Map<String,Double>>>>> brandExpenseMap=new HashMap<>();
					List<ExpenseData> expenseDataList=expenseDataService.getDateBetweenStartDateAndEndDate(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)));
					Map<String,Double> accPageView=expenseDataService.getMonthWiseAccumulatedPageView(Util.convertStringToDateWithMonth(startDate),  Util.setLastDayOfMonth(Util.convertStringToDateWithMonth(endDate)));
					Map<String,Map<String, Double>> brandTotalExpense=new HashMap<>();

					for(ExpenseData data:expenseDataList)
					{

						Map<String, Double> empPercent=empService.getEmployeeHeadCount(Util.setFirstDayOfMonth(data.getDate()));

						for(Brand b:data.getBrands())
						{
							double brandShare=-1;
							String monthDate=null;
							if(data.getAllocation().equalsIgnoreCase("As per PV"))
							{
								List<PageView> pageView=pageViewService.getPageView(b, Util.setLastDayOfMonth(data.getDate()));
								if(pageView!=null && pageView.size()>0)
								{
									double pageViewValue=pageView.get(0).getPageview();
									double brandPercent=pageViewValue/accPageView.get(Util.getYearMonth(data.getDate()));
									brandShare=brandPercent*data.getValue();
									monthDate=Util.getYearMonth(data.getDate());
								}
							}
							else if(data.getAllocation().equalsIgnoreCase("As per Actuals"))
							{
								List<Actual> actualList=actualService.getActuals(b, data.getParty(), Util.setLastDayOfMonth(data.getDate()));
								if(actualList!=null && actualList.size()>0)
								{
									monthDate=Util.getYearMonth(data.getDate());
									brandShare=actualList.get(0).getValue();

								}
							}
							else if(data.getAllocation().equalsIgnoreCase("As per HeadCount"))
							{
								try {
									monthDate=Util.getYearMonth(data.getDate());
									double brandPercent=empPercent.get(String.valueOf(b.getId()))/empPercent.get("total");
									brandShare=brandPercent*data.getValue();
								}
								catch(Exception ex) {}

							}

							if(brandShare>=0)
							{
								if(brandExpenseMap.containsKey(b.getName()))
								{
									Map<String, Map<String,Map<String,Map<String,Double>>>> l1map=brandExpenseMap.get(b.getName());
									if(l1map.containsKey(data.getL2().getL1().getName()))
									{

										Map<String,Map<String,Map<String,Double>>> l2map=l1map.get(data.getL2().getL1().getName());
										if(l2map.containsKey(data.getL2().getName()))
										{
											Map<String,Map<String,Double>> partyMap=l2map.get(data.getL2().getName());
											if(partyMap.containsKey(data.getParty().getName()))
											{
												Map<String,Double> monthMap=partyMap.get(data.getParty().getName());
												BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
												monthMap.put(monthDate, bdUp.doubleValue());
												partyMap.put(data.getParty().getName(), monthMap);
												l2map.put(data.getL2().getName(), partyMap);
												l1map.put(data.getL2().getL1().getName(), l2map);
												brandExpenseMap.put(b.getName(), l1map);
											}
											else
											{
												Map<String,Double> monthMap=new HashMap<>();
												BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
												monthMap.put(monthDate, bdUp.doubleValue());
												partyMap.put(data.getParty().getName(), monthMap);
												l2map.put(data.getL2().getName(), partyMap);
												l1map.put(data.getL2().getL1().getName(), l2map);
												brandExpenseMap.put(b.getName(), l1map);


											}
										}
										else
										{

											Map<String,Double> monthMap=new HashMap<>();
											BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
											monthMap.put(monthDate, bdUp.doubleValue());
											Map<String,Map<String,Double>> partyMap=new HashMap<>();
											partyMap.put(data.getParty().getName(), monthMap);
											l2map.put(data.getL2().getName(), partyMap);
											l1map.put(data.getL2().getL1().getName(), l2map);
											brandExpenseMap.put(b.getName(), l1map);

										}



									}
									else
									{
										Map<String,Double> monthMap=new HashMap<>();
										BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
										monthMap.put(monthDate, bdUp.doubleValue());
										Map<String,Map<String,Double>> partyMap=new HashMap<>();
										partyMap.put(data.getParty().getName(), monthMap);
										Map<String,Map<String,Map<String,Double>>> l2map=new HashMap<>();
										l2map.put(data.getL2().getName(), partyMap);
										l1map.put(data.getL2().getL1().getName(), l2map);
										brandExpenseMap.put(b.getName(), l1map);
									}
								}
								else
								{
									Map<String,Double> monthMap=new HashMap<>();
									BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
									monthMap.put(monthDate, bdUp.doubleValue());
									Map<String,Map<String,Double>> partyMap=new HashMap<>();
									partyMap.put(data.getParty().getName(), monthMap);
									Map<String,Map<String,Map<String,Double>>> l2map=new HashMap<>();
									l2map.put(data.getL2().getName(), partyMap);
									Map<String ,Map<String,Map<String,Map<String,Double>>>> l1map=new HashMap<>();
									l1map.put(data.getL2().getL1().getName(), l2map);
									brandExpenseMap.put(b.getName(), l1map);
								}


								if(brandTotalExpense.containsKey(b.getName()))
								{
									Map<String, Double> monthMap=brandTotalExpense.get(b.getName());
									if(monthMap.containsKey(monthDate))
									{
										double sum=monthMap.get(monthDate);
										sum=sum+brandShare;
										BigDecimal bdUp=new BigDecimal(sum).setScale(2,RoundingMode.UP);
										monthMap.put(monthDate, bdUp.doubleValue());
										brandTotalExpense.put(b.getName(), monthMap);
									}
									else
									{
										BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
										monthMap.put(monthDate, bdUp.doubleValue());
										brandTotalExpense.put(b.getName(), monthMap);

									}


								}
								else
								{
									Map<String,Double> monthMap=new HashMap<>();
									BigDecimal bdUp=new BigDecimal(brandShare).setScale(2,RoundingMode.UP);
									monthMap.put(monthDate, bdUp.doubleValue());
									brandTotalExpense.put(b.getName(), monthMap);
								}



							}	




						}






					} 
					List<String> allDates= Util.getAllMonthsBetweenTwoDates(startDate, endDate);
					List<Date> datelist=new ArrayList<>();
					for(String s:allDates)
					{
						try {
							datelist.add(Util.sdfDateOnly.parse(s+"-01"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					model.addAttribute("datelist", datelist);
					model.addAttribute("expensemap", brandExpenseMap);
					model.addAttribute("datestrlist", allDates);

					List<String> brandList=new ArrayList<>();
					for(Brand b:userlist.get(0).getBrands())
						brandList.add(b.getName());

					model.addAttribute("brands", brandList);

					return "brandwiseexpenseunits";


				}
				return "notauthorized";

			}
			else
			{
				return "notauthorized";
			}

		}
		else
		{
			return "notauthorized";
		}






	}






}
