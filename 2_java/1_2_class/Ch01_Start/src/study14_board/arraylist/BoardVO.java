package study14_board.arraylist;

//VO=DTO=Bean=getter()/setter()
public class BoardVO {
	private String register;
	private String subject;
	private String email;
	private String content;
	private String passwd;

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {

		return "작성자: " + this.getRegister() + ", " + "이메일: " + this.getEmail() + ", " + "제목: " + this.getSubject()
				+ ", " + "글 내용: " + this.getContent();
	}

}
