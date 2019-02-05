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
import com.myproject.jdbc.daos.ExpenseCategoryDao;
import com.myproject.jdbc.daos.ExpensesDao;
import com.myproject.jdbc.pojos.BankBook;
import com.myproject.jdbc.pojos.CashBook;
import com.myproject.jdbc.pojos.ExpenseCategory;
import com.myproject.jdbc.pojos.Expenses;
import com.myproject.utilities.DateUtils;

/**
 * Servlet implementation class ExpenseController
 */
@WebServlet("/ExpenseController")
public class ExpenseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseController() {
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
	
	protected String getExpenseCategories(int userId) {
		String expCats = "";
		ArrayList<ExpenseCategory> expCatList = new ExpenseCategoryDao().findAll(userId);
		for(int i=0;i<expCatList.size();i++) {
			expCats += "<option value = \""+expCatList.get(i).getExpCatId()+"\">";
			expCats += expCatList.get(i).getExpCatName();
			expCats += "</option>";
		}
		return expCats;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = (int)request.getSession().getAttribute("currentUser");
		String message = "";
		if(request.getParameter("inputExpenseCategory")!=null) {
			int expCatId = Integer.parseInt(request.getParameter("inputExpenseCategory"));
			String expAc = request.getParameter("inputExpenseName");
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
			String payBy = request.getParameter("inputPayBy");
			String remark = request.getParameter("inputRemark");
			new ExpensesDao().create(new Expenses(0,expAc,userId,expCatId,amount,date,payBy,remark));
			message = "<div class=\"col-md-12 alert-success-message\">"
					+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
					+ "  <strong>Expense Added Successfully !</strong>" + "</div>";
			if(payBy.equals("Cash")) {
				new CashBookDao().create(new CashBook(0,expAc,date,amount,userId,"Pay"));
			}
			else {
				new BankBookDao().create(new BankBook(0,expAc,date,amount,userId,"Pay"));
			}
		}
		
		String expenseCategories = getExpenseCategories(userId);
		request.setAttribute("ExpenseMessage", message);
		request.setAttribute("expenseCategories", expenseCategories);
		RequestDispatcher rd = request.getRequestDispatcher("Expense.jsp");
		rd.forward(request, response);
	}

}
