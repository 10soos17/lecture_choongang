package a.a.a;

public class StudentInfo {

	private String name;
	private int engScore;
	private int mathScore;
	

	public StudentInfo() {

	}


	public StudentInfo(String name, int engScore, int mathScore) {

		this.name = name;
		this.engScore = engScore;
		this.mathScore = mathScore;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getEngScore() {
		return engScore;
	}


	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}


	public int getMathScore() {
		return mathScore;
	}


	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}
	
	
}
