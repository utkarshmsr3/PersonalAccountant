package com.myproject.controller.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.jdbc.daos.BankBookDao;
import com.myproject.jdbc.daos.CashBookDao;
import com.myproject.jdbc.daos.IncomeCategoryDao;
import com.myproject.jdbc.daos.IncomesDao;
import com.myproject.jdbc.pojos.BankBook;
import com.myproject.jdbc.pojos.CashBook;
import com.myproject.jdbc.pojos.IncomeCategory;
import com.myproject.jdbc.pojos.Incomes;
import com.myproject.utilities.DateUtils;

/**
 * Servlet implementation class IncomeController
 */
@WebServlet("/IncomeController")
public class IncomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeController() {
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
	
	protected String getIncomeCategories(int userId) {
		String incCats = "";
		ArrayList<IncomeCategory> incCatList = new IncomeCategoryDao().findAll(userId);
		for(int i=0;i<incCatList.size();i++) {
			incCats += "<option value = \""+incCatList.get(i).getIncCatId()+"\">";
			incCats += incCatList.get(i).getIncCatName();
			incCats += "</option>";
		}
		return incCats;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = (int)request.getSession().getAttribute("currentUser");
		String message = "";
		if(request.getParameter("inputIncomeCategory")!=null) {
			int incCatId = Integer.parseInt(request.getParameter("inputIncomeCategory"));
			String incAc = request.getParameter("inputIncomeName");
			double amount = Double.parseDouble(request.getParameter("inputAmount"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date=null;
			try {
				date = sdf.parse(request.getParameter("inputDate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "<div class=\"col-md-12 alert-error-message\">"
						+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
						+ "  <strong>Invalid Date !</strong>" + "</div>";
			}
			String receiveBy = request.getParameter("inputReceiveBy");
			String remark = request.getParameter("inputRemark");
			new IncomesDao().create(new Incomes(0,incAc,userId,incCatId,amount,date,receiveBy,remark));
			message = "<div class=\"col-md-12 alert-success-message\">"
					+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
					+ "  <strong>Income Added Successfully !</strong>" + "</div>";
			if(receiveBy.equals("Cash")) {
				new CashBookDao().create(new CashBook(0,incAc,date,amount,userId,"Receive"));
			}
			else {
				new BankBookDao().create(new BankBook(0,incAc,date,amount,userId,"Receive"));
			}
		
		}
		
		String incomeCategories = getIncomeCategories(userId);
		request.setAttribute("IncomeMessage", message);
		request.setAttribute("incomeCategories", incomeCategories);
		RequestDispatcher rd = request.getRequestDispatcher("Income.jsp");
		rd.forward(request, response);
	}

}
