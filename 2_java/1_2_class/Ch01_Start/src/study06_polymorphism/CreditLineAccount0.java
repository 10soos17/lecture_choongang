package study06_polymorphism;

public class CreditLineAccount0 extends Account0 {
	public int creditLine;

	public CreditLineAccount0(String an, String on, int b, int cn) {
		super(an, on, b);
		this.creditLine = cn;

	}

	public void inPut() {
		// super.inPut();
		Account0.ac.add(new CreditLineAccount0(super.accountNo, super.ownerName, super.balance, this.creditLine));

	}

	public int withdraw(int amount) throws Exception {

		return super.withdraw(amount);
	}
/*
	public String toString() {
		return super.toString() + "크레딧카드번호: " + this.creditLine + "\n";
	}*/
}
