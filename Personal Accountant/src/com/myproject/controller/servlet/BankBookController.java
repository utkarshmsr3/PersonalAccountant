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

import com.myproject.jdbc.daos.BankBookDao;
import com.myproject.jdbc.pojos.BankBook;
import com.myproject.utilities.DateUtils;

/**
 * Servlet implementation class BankBookController
 */
@WebServlet("/BankBookController")
public class BankBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankBookController() {
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
		String bankSDate = request.getParameter("dateFrom");
		int userId = (int)request.getSession().getAttribute("currentUser");
		String bankEDate = "";
		if(bankSDate != null) {
		bankEDate = request.getParameter("dateTo");}
		else {
			String bankBookTableData = "";
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
			bankSDate = year +"-"+monthStr+"-"+dayStr;			
			bankEDate = sdf.format(new java.util.Date());
		}
		ArrayList<BankBook> cB = new BankBookDao().findAllDateWise(bankSDate, bankEDate, userId);
		int closingBalance=0;
		String bankBookTableData = "";
		if(cB.size()>0) {
			bankBookTableData +="<thead>" + 
					"								<tr>" + 
					"									<td>Sno.</td>" + 
					"									<td>Date</td>" + 
					"									<td>Amount</td>" + 
					"									<td>Pay/Receive</td>" + 
					"								</tr>" + 
					"			</thead><tbody>";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(int i=0;i<cB.size();i++) {
				bankBookTableData += "								<tr>" + 
						"									<td>" + (i+1) +"</td>" + 
						"									<td>"+sdf.format(cB.get(i).getTranDate())+"</td>" + 
						"									<td>"+cB.get(i).getAmount()+"</td>" + 
						"									<td>"+cB.get(i).getOperation() +"</td>" + 
						"								</tr>";
				if(cB.get(i).getOperation().equals("Receive"))
					closingBalance += cB.get(i).getAmount();
				else
					closingBalance -= cB.get(i).getAmount();
			}
			bankBookTableData += "" + 
					"							<thead>" + 
					"								<td colspan=\"2\">Closing Balance</td>" + 
					"								<td colspan=\"2\">"+closingBalance+"</td> "+ 
					"							</thead>" + 
					"							</tbody>";
			
		}
		
		request.setAttribute("bankBookTableData", bankBookTableData);
		RequestDispatcher rd = request.getRequestDispatcher("BankBook.jsp");
		rd.forward(request, response);
	}

}
