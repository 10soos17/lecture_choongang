package com.dto;

public class EmpDTO {
	int empno;
	String ename;
	int sal;
	int deptno;

	public EmpDTO(int empno, String ename, int sal, int deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
		this.deptno = deptno;
	}

	public String toString() {
		
		return "empno: "+this.getEmpno()+", "+
				"ename: "+this.getEname()+", "+
				"sal: "+this.getSal()+", "+
				"deptno: "+this.getDeptno();
	}
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

}
