package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.EmpDAO;
import com.dto.EmpDTO;

@WebServlet("/EmpSelectDAO")
public class EmpSelectDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		EmpDAO empdao = new EmpDAO();
		PrintWriter out = response.getWriter();
		
		int empno = Integer.parseInt(request.getParameter("id"));
		String ename = request.getParameter("name");
		int sal = Integer.parseInt(request.getParameter("salary"));
		int deptno = Integer.parseInt(request.getParameter("dept"));

		response.setContentType("text/html;charset=utf-8");
		out.println("<html><body>");
		
		ArrayList<EmpDTO> eList = empdao.select();

		if (eList.size() > 0) {
			
			out.println("<html><body>");
			out.println("성공");
			
			for(EmpDTO dto : eList) {
	
				out.println(dto.toString()+"<br>");
			}
			
		} else {
			out.println("실패");
		}
		out.println("</body></html>");
	}

}
