package Numeron;

public class Entry{
	private int number;
	private int probablity;
	private int eat;
	private int bite;
	//private:
	public Entry(){
		setProbablity(1);
		setNumber(setEat(setBite(0)));
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getProbablity() {
		return probablity;
	}
	public void setProbablity(int probablity) {
		this.probablity = probablity;
	}
	public int getEat() {
		return eat;
	}
	public int setEat(int eat) {
		this.eat = eat;
		return eat;
	}
	public int getBite() {
		return bite;
	}
	public int setBite(int bite) {
		this.bite = bite;
		return bite;
	}
}

