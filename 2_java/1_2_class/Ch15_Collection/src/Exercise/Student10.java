package Exercise;

public class Student10 implements Comparable<Student10> {

	public String id;

	public int score;

	public Student10(String id, int score) {
		this.id = id;
		this.score = score;
	}

	@Override
	public int compareTo(Student10 o) {
		if (this.score < o.score)
			return -1;
		else if (this.score == o.score)
			return 0;
		else
			return 1;

	}

}
