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
 * Servlet implementation class DayBookController
 */
@WebServlet("/DayBookController")
public class DayBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DayBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String daySDate = request.getParameter("dateFrom");
		int userId = (int)request.getSession().getAttribute("currentUser");
		String dayEDate = "";
		if(daySDate != null) {
			dayEDate = request.getParameter("dateTo");}
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
			daySDate = year +"-"+monthStr+"-"+dayStr;			
			dayEDate = sdf.format(new java.util.Date());
		}
		ArrayList<Expenses> expList = new ExpensesDao().findAllDateWise(daySDate, dayEDate, userId);
		ArrayList<Incomes> incList = new IncomesDao().findAllDateWise(daySDate, dayEDate, userId);
		System.out.println(expList);System.out.println(incList);
		String dayBookTableData = "";
		if(expList.size()>0 || incList.size()>0) {
			dayBookTableData += "		<thead>" + 
					"                  <tr>" + 
					"		    <td>Sno.</td>" + 
					"		    <td>Account Name</td>" + 
					"		    <td>Date</td>" + 
					"		    <td>Amount</td>" + 
					"                    <td>Pay/Receive By</td>" + 
					"		    <td>Remark</td>" + 
					"                  </tr>" + 
					"                </thead><tbody>";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(expList.size()>0) {
				dayBookTableData +="<tr><td colspan=\"6\"><b>Expenses</b></td></tr>";
				for(int i=0;i<expList.size();i++) {
					dayBookTableData += "   <tr>" + 
							"                    <td>"+(i+1)+"</td>" + 
							"                    <td>"+expList.get(i).getExpAc()+"</td>" + 
							"                    <td>"+sdf.format(expList.get(i).getTranDate())+"</td>" + 
							"                    <td>"+expList.get(i).getAmount()+"</td>" + 
							"                    <td>"+expList.get(i).getPayBy()+"</td>" + 
							"                    <td>"+expList.get(i).getRemark()+"</td>" + 
							"		  		</tr>";
				}
			}
			if(incList.size()>0) {
				dayBookTableData +="<tr><td colspan=\"6\"><b>Incomes</b></td></tr>";
				for(int i=0;i<incList.size();i++) {
					dayBookTableData += "   <tr>" + 
							"                    <td>"+(i+1)+"</td>" + 
							"                    <td>"+incList.get(i).getIncAc()+"</td>" + 
							"                    <td>"+sdf.format(incList.get(i).getTranDate())+"</td>" + 
							"                    <td>"+incList.get(i).getAmount()+"</td>" + 
							"                    <td>"+incList.get(i).getReceiveBy()+"</td>" + 
							"                    <td>"+incList.get(i).getRemark()+"</td>" + 
							"		  		</tr>";
				}
			}
			dayBookTableData += "</tbody>";
			request.setAttribute("dayBookTableData", dayBookTableData);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("DayBook.jsp");
		rd.forward(request, response);

	}

}
