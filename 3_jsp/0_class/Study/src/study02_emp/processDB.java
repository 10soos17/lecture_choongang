package study02_emp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class processDB {

	static Connection conn;

	static {
		// 클래스가 로딩될 때 단 한번 호출되는 영역
		// Class.forName : 특정 클래스를 메모리로 로딩하는 메소드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc.jar가 해당 위치에 없습니다.");
		}

	}

	public static Connection getConnection() {
		// 디비 작업에 필요한 Connection 객체를 생성해 주는 메소드
		try {

			String url = "jdbc:oracle:thin:@localhost:1521:ee";
			conn = DriverManager.getConnection(url, "scott", "scott");
			System.out.println("Connection Success!");

			return conn;

		} catch (SQLException e) {
			System.out.println("주소및 id,pw가 다릅니다.");
		}
		return conn;
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// transaction 처리 메소드
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {

		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insertEmp(int id, String name, int salary, int dept) {
		LocalDate dt = LocalDate.now();
		int year = dt.getYear();
		Month month = dt.getMonth();
		int day = dt.getDayOfMonth();
	
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
//		String strDate = dateFormat.format(date);  
		System.out.println(day);
		int count = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into EMP values(?,?, ?, ?, ?, ?,?,?)";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setNull(3, Types.NULL);
			pstmt.setNull(4, Types.NULL);
			pstmt.setDate(5, new java.sql.Date(year,month.getValue(),day));
			pstmt.setLong(6, salary);
			pstmt.setNull(7, Types.NULL);
			pstmt.setInt(8, dept);

			count = pstmt.executeUpdate();

			if (count > 0) {

				System.out.println("insert success!");
			} else {
				System.out.println("insert fail!");
			}
		} catch (SQLException e1) {
			System.out.println("0insert fail!");
			System.out.println(e1.getMessage());
		} finally {

			close(pstmt);
		}
		return count;

	}

	public ArrayList<Object> selectEmp() {

		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from EMP";

		ArrayList<Object> empList = null;

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			empList = new ArrayList<Object>();

			while (rs.next()) {

				empList.add(Arrays.asList(rs.getInt(1), rs.getString(2), rs.getInt(6), rs.getInt(8)));

			}
			System.out.println("select success!");

		} catch (SQLException e) {
			System.out.println("select fail!");
		}
		return empList;
	}
}
