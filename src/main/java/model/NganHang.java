package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class NganHang {
private Map<String, SoTaiKhoan> map = new LinkedHashMap<String, SoTaiKhoan>();

public NganHang(Map<String, SoTaiKhoan> map) {
	this.map = map;
}

public NganHang() {
	this.map.put("BIDV", new SoTaiKhoan("17284927462738", 12000));
	this.map.put("Sacombank", new SoTaiKhoan("98736471872453", 120000000));
	this.map.put("MoMo", new SoTaiKhoan("12343258294762", 120000000));
	this.map.put("MBBank", new SoTaiKhoan("374826482122", 120000000));
	this.map.put("TPBank", new SoTaiKhoan("452378459182", 120000000));
}

public Map<String, SoTaiKhoan> getMap() {
	return map;
}

public void setMap(Map<String, SoTaiKhoan> map) {
	this.map = map;
}



}
