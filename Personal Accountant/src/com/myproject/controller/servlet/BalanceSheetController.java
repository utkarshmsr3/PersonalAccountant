package com.myproject.controller.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.jdbc.daos.ExpensesDao;
import com.myproject.jdbc.daos.IncomesDao;
import com.myproject.jdbc.pojos.Expenses;
import com.myproject.jdbc.pojos.Incomes;

/**
 * Servlet implementation class BalanceSheetController
 */
@WebServlet("/BalanceSheetController")
public class BalanceSheetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BalanceSheetController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bsSDate = request.getParameter("dateFrom");
		int userId = (int)request.getSession().getAttribute("currentUser");
		String bsEDate = "";
		if(bsSDate != null) {
		bsEDate = request.getParameter("dateTo");}
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new java.util.Date());
			int day = Integer.parseInt(date.split("-")[2]);
			int month = Integer.parseInt(date.split("-")[1]);
			int year = Integer.parseInt(date.split("-")[0]);
			if(month == 1) {
				month = 12;
				year = year-1;
			}else {
				month--;
			}
			String monthStr = "" + month;
			if(month<10) {
				monthStr = "0" + month;
			}
			String dayStr = "" + day;
			if(day<10) {
				dayStr = "0" + day;
			}
			bsSDate = year +"-"+monthStr+"-"+dayStr;			
			bsEDate = sdf.format(new java.util.Date());
		}
		ArrayList<Expenses> expList = new ExpensesDao().findAllDateWise(bsSDate, bsEDate, userId);
		ArrayList<Incomes> incList = new IncomesDao().findAllDateWise(bsSDate, bsEDate, userId);
		int incTotal = 0, expTotal=0;
		String bsTableData ="";
		if(expList.size()>0 || incList.size()>0) {
			bsTableData += "<table class=\"table table-striped table-bordered templatemo-user-table\">" + 
					"                <thead>" + 
					"                  <tr>" + 
					"		    <td colspan=\"2\">Incomes</td>" + 
					"		    <td colspan=\"2\">Expenses</td>" + 
					"                  </tr>" + 
					"		  <tr>" + 
					"		    <td>Incomes</td>" + 
					"		    <td>Amount</td>" + 
					"		    <td>Expenses</td>" + 
					"		    <td>Amount</td>" + 
					"		  </tr>" + 
					"                </thead><tbody>" ;
			for(int i=0,j=0;i<incList.size() || j<expList.size();i++,j++) {
				bsTableData += "<tr>";
				if(i<incList.size()) {
					bsTableData += "<td>" + incList.get(i).getIncAc() +"</td>";
					bsTableData += "<td>" + incList.get(i).getAmount() +"</td>";
					incTotal += incList.get(i).getAmount();
				}else {
					bsTableData += "<td>" +"N/A"+ "</td>";
					bsTableData += "<td>" + "N/A"+"</td>";
				}
				if(j<expList.size()) {
					bsTableData += "<td>" + expList.get(j).getExpAc() +"</td>";
					bsTableData += "<td>" + expList.get(j).getAmount() +"</td>";
					expTotal += expList.get(j).getAmount();
				}else {
					bsTableData += "<td>" +"N/A" +"</td>";
					bsTableData += "<td>" +"N/A" +"</td>";
				}
				bsTableData += "</tr>";
				
			}
			bsTableData+=" </tbody>" + 
					"		<thead>" + 
					"		  <tr>" + 
					"		    <td>Total</td>" + 
					"		    <td>"+incTotal+"</td>" + 
					"		    <td>Total</td>" + 
					"		    <td>"+expTotal+"</td>" + 
					"		  </tr></thead></tbody></table><br><br><h3 class=\"text-center\">";
			if(incTotal>=expTotal)
				bsTableData += "<b>Gross Profit: </b>" + (incTotal - expTotal) + "&nbsp&nbsp&nbsp";
			else
				bsTableData += "<b>Gross Loss: </b>" + (expTotal - incTotal) + "&nbsp&nbsp&nbsp";
			bsTableData +="</h3>";
			
		}
		request.setAttribute("bsTableData", bsTableData);
		RequestDispatcher rd = request.getRequestDispatcher("BalanceSheet.jsp");
		rd.forward(request, response);
	}

}
