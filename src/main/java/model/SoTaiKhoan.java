package model;

public class SoTaiKhoan {
private String stk;
private double soDu;
public SoTaiKhoan(String stk, double soDu) {
	this.stk = stk;
	this.soDu = soDu;
}
public String getStk() {
	return stk;
}
public void setStk(String stk) {
	this.stk = stk;
}
public double getSoDu() {
	return soDu;
}
public void setSoDu(double soDu) {
	this.soDu = soDu;
}


}
