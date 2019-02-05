package com.myproject.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.jdbc.daos.ExpenseCategoryDao;
import com.myproject.jdbc.pojos.ExpenseCategory;
import com.myproject.jdbc.pojos.Users;

/**
 * Servlet implementation class ExpenseCategoryController
 */
@WebServlet("/ExpenseCategoryController")
public class ExpenseCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExpenseCategoryController() {
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
	protected String getExpenseCategoryTable(int userId) {
		ArrayList<ExpenseCategory> list = new ExpenseCategoryDao().findAll(userId);
		String data = "";
		if (list.size() > 0) {
			data += "<table	class=\"table table-striped table-bordered templatemo-user-table\">";
			data += "<thead>";
			data += "<tr>";
			data += "<td><a href=\"\" class=\"white-text templatemo-sort-by\">#";
			data += "		<span class=\"caret\"></span>";
			data += "</a></td>";
			data += "<td><a href=\"\" class=\"white-text templatemo-sort-by\">Category";
			data += "		Name <span class=\"caret\"></span>";
			data += "</a></td>";
			data += "<td><a href=\"\" class=\"white-text templatemo-sort-by\">Category";
			data += "		Details <span class=\"caret\"></span>";
			data += "</a></td>";
			data += "<td>Edit</td>";
			data += "<td>Action</td>";
			data += "</tr>";
			data += "</thead>";
			data += "<tbody>";
			for (int i = 0; i < list.size(); i++) {
				ExpenseCategory e = list.get(i);
				data += "<tr>";
				data += "<td>" + (i + 1) + "</td>";
				data += "<td id=\"catName" + e.getExpCatId() + "\">" + e.getExpCatName() + "</td>";
				data += "<td id=\"catDetails" + e.getExpCatId() + "\">" + e.getExpCatDetails() + "</td>";
				data += "<td id=\"editCancel" + e.getExpCatId() + "\">" + "<a onclick=\"editAction(" + e.getExpCatId()
						+ ");\" class=\"templatemo-edit-btn\">Edit</a>" + "</td>";
				data += "<td id=\"deleteSave" + e.getExpCatId() + "\">"
						+ "<a onclick=\"deleteAction(" + e.getExpCatId()
						+ ");\" class=\"templatemo-edit-btn\">Delete</a>" + "</td>";
				data += "</tr>";
			}
			data += "</tbody>";
			data += "</table>";
		}
		return data;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = (int) request.getSession().getAttribute("currentUser");
		//System.out.println(request.getParameter("expenseCategoryOperation"));
		//System.out.println(request.getParameter("expenseCategoryOperationCreate"));
		if (request.getParameter("expenseCategoryOperationCreate") != null) {
			if (request.getParameter("expenseCategoryOperationCreate").equals("create")) {
				String catName = request.getParameter("inputCategoryName");
				String catDetails = request.getParameter("inputCategoryDetails");
				new ExpenseCategoryDao().create(new ExpenseCategory(0, catName, catDetails, userId));
				request.setAttribute("expenseCategoryNotification","<div class=\"col-md-12 alert-success-message\">"
						+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
						+ "  <strong>Created Successfully !</strong>" + "</div>");
			}
		}
		if (request.getParameter("expenseCategoryOperation") != null) {
			if (request.getParameter("expenseCategoryOperation").equals("save")) {
				String catName = request.getParameter("changedCatName");
				String catDetails = request.getParameter("changedCatDetails");
				int catId = Integer.parseInt(request.getParameter("changedId"));
				new ExpenseCategoryDao().edit(new ExpenseCategory(catId, catName, catDetails, userId));
				request.setAttribute("expenseCategoryNotification","<div class=\"col-md-12 alert-success-message\">"
						+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
						+ "  <strong>Saved Successfully !</strong>" + "</div>");
			} else if (request.getParameter("expenseCategoryOperation").equals("delete")) {
				//System.out.println("deleting");
				int catId = Integer.parseInt(request.getParameter("changedId"));
				new ExpenseCategoryDao().remove(catId);
				request.setAttribute("expenseCategoryNotification","<div class=\"col-md-12 alert-success-message\">"
						+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
						+ "  <strong>Removed Successfully !</strong>" + "</div>");
			}
		}

		String tableData = getExpenseCategoryTable(userId);
		request.removeAttribute("expenseCategoryData");
		request.setAttribute("expenseCategoryData", tableData);
		RequestDispatcher rd = request.getRequestDispatcher("ExpenseCategory.jsp");
		rd.forward(request, response);
	}
}
