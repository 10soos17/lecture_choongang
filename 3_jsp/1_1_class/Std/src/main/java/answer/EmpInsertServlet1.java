package answer;

import study02_emp.*;

import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/study02/answer/EmpInsert2")
public class EmpInsertServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// response.setContentType("text/html; charset=utf-8");

		String empno = request.getParameter("emp_id");
		String ename = request.getParameter("ename");
		String sal = request.getParameter("salary");
		String deptno = request.getParameter("depart");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");

		Connection conn = null;
		Statement stmt = null;

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "scott", "scott");
			stmt = conn.createStatement();
			String sql = "insert into emp(empno, ename, sal, deptno) values('" + empno + "','" + ename + "'," + sal
					+ "," + deptno + ")";
			int n = stmt.executeUpdate(sql);

			if (n == 1) {
				out.println("저장성공");
			} else {
				out.println("저장실패");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("driver가 없습니다.");
		} catch (SQLException e) {
			System.out.println("SQL에 문제가 있습니다");
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
			}

		}
		out.println("</body></html>");
	}

}
