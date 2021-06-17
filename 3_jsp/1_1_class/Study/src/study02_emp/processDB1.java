package study02_emp;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class processDB1 {

	Connection conn;
	String url= null;
	// DB접속
	public processDB1() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:orcl";
			
		} catch (ClassNotFoundException e) {
			System.out.println("Connection Success!");
		}

	}


	public int insertEmp(int id, String name, int salary, int dept) {
		LocalDate dt = LocalDate.now();
		int year = dt.getYear();
		int month = dt.getMonthValue();// .getMonth();
		int day = dt.getDayOfMonth();

//		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
//		String strDate = dateFormat.format(date);  
		System.out.println(day);
		int count = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into EMP values(?,?, ?, ?, ?, ?,?,?)";
		
		try {
			conn = DriverManager.getConnection(url, "scott", "scott");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setNull(3, Types.NULL);
			pstmt.setNull(4, Types.NULL);
			pstmt.setDate(5, new java.sql.Date(year, month, day));
			pstmt.setInt(6, salary);
			pstmt.setNull(7, Types.NULL);
			pstmt.setInt(8, dept);

			count = pstmt.executeUpdate();

			if (count > 0) {

				System.out.println("insert success!");
				return count;
			} else {
				System.out.println("insert fail!");
			}
		} catch (SQLException e1) {
			System.out.println("0insert fail!");
			System.out.println(e1.getMessage());
		}
		return count;

	}

	public ArrayList<Object> selectEmp() {

		ResultSet rs = null;

		ArrayList<Object> empList = new ArrayList<>();

		try {
			System.out.println("select");
			System.out.println("select");
			String sql = "select empno, ename, sal, deptno from emp";
			conn = DriverManager.getConnection(url, "scott", "scott");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String empno = rs.getString("empno");
				String ename = rs.getString(2);
				String sal = rs.getString("sal");
				String deptno = rs.getString("deptno");
				
				empList.add(Arrays.asList(empno, ename, sal, deptno));
				
				
			}
			
		} catch (SQLException e) {
			System.out.println("select fail!");
		}
		return empList;

	}
}
