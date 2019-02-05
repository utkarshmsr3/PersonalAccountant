package com.myproject.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myproject.jdbc.daos.IncomeCategoryDao;
import com.myproject.jdbc.pojos.IncomeCategory;


/**
 * Servlet implementation class IncomeCategoryController
 */
@WebServlet("/IncomeCategoryController")
public class IncomeCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeCategoryController() {
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
	
	protected String getIncomeCategoryTable(int userId) {
		ArrayList<IncomeCategory> list = new IncomeCategoryDao().findAll(userId);
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
				IncomeCategory e = list.get(i);
				data += "<tr>";
				data += "<td>" + (i + 1) + "</td>";
				data += "<td id=\"catName" + e.getIncCatId() + "\">" + e.getIncCatName() + "</td>";
				data += "<td id=\"catDetails" + e.getIncCatId() + "\">" + e.getIncCatDetails() + "</td>";
				data += "<td id=\"editCancel" + e.getIncCatId() + "\">" + "<a onclick=\"editAction(" + e.getIncCatId()
						+ ");\" class=\"templatemo-edit-btn\">Edit</a>" + "</td>";
				data += "<td id=\"deleteSave" + e.getIncCatId() + "\">"
						+ "<a onclick=\"deleteAction(" + e.getIncCatId()
						+ ");\" class=\"templatemo-edit-btn\">Delete</a>" + "</td>";
				data += "</tr>";
			}
			data += "</tbody>";
			data += "</table>";
		}
		return data;
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = (int) request.getSession().getAttribute("currentUser");
		//System.out.println(request.getParameter("incomeCategoryOperation"));
		//System.out.println(request.getParameter("incomeCategoryOperationCreate"));
		if (request.getParameter("incomeCategoryOperationCreate") != null) {
			if (request.getParameter("incomeCategoryOperationCreate").equals("create")) {
				String catName = request.getParameter("inputCategoryName");
				String catDetails = request.getParameter("inputCategoryDetails");
				new IncomeCategoryDao().create(new IncomeCategory(0, catName, catDetails, userId));

				request.setAttribute("incomeCategoryNotification","<div class=\"col-md-12 alert-success-message\">"
						+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
						+ "  <strong>Created Successfully !</strong>" + "</div>");
			}
		}
		if (request.getParameter("incomeCategoryOperation") != null) {
			if (request.getParameter("incomeCategoryOperation").equals("save")) {
				String catName = request.getParameter("changedCatName");
				String catDetails = request.getParameter("changedCatDetails");
				int catId = Integer.parseInt(request.getParameter("changedId"));
				new IncomeCategoryDao().edit(new IncomeCategory(catId, catName, catDetails, userId));
				request.setAttribute("incomeCategoryNotification","<div class=\"col-md-12 alert-success-message\">"
						+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
						+ "  <strong>Saved Successfully !</strong>" + "</div>");
			
			} else if (request.getParameter("incomeCategoryOperation").equals("delete")) {
				//System.out.println("deleting");
				int catId = Integer.parseInt(request.getParameter("changedId"));
				new IncomeCategoryDao().remove(catId);
				request.setAttribute("incomeCategoryNotification","<div class=\"col-md-12 alert-success-message\">"
						+ "  <span class=\"alert-closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> "
						+ "  <strong>Removed Successfully !</strong>" + "</div>");
			
			}
		}

		String tableData = getIncomeCategoryTable(userId);
		request.removeAttribute("incomeCategoryData");
		request.setAttribute("incomeCategoryData", tableData);
		RequestDispatcher rd = request.getRequestDispatcher("IncomeCategory.jsp");
		rd.forward(request, response);		
	}

}
