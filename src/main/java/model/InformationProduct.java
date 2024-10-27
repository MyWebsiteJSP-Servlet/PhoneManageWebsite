package model;

public class InformationProduct {
private String info_ID;
private String camera;
private String cameraSelFie;
private String cell;
private String cpu;
private String gpu;
private String memory;
private String os;
private String ram;
private String screen;
private String sim;
public InformationProduct(String info_ID, String camera, String cameraSelFie, String cell, String cpu, String gpu,
		String memory, String os, String ram, String screen, String sim) {
	this.info_ID = info_ID;
	this.camera = camera;
	this.cameraSelFie = cameraSelFie;
	this.cell = cell;
	this.cpu = cpu;
	this.gpu = gpu;
	this.memory = memory;
	this.os = os;
	this.ram = ram;
	this.screen = screen;
	this.sim = sim;
}
public InformationProduct() {
	super();
}
public String getInfo_ID() {
	return info_ID;
}
public void setInfo_ID(String info_ID) {
	this.info_ID = info_ID;
}
public String getCamera() {
	return camera;
}
public void setCamera(String camera) {
	this.camera = camera;
}
public String getCameraSelFie() {
	return cameraSelFie;
}
public void setCameraSelFie(String cameraSelFie) {
	this.cameraSelFie = cameraSelFie;
}
public String getCell() {
	return cell;
}
public void setCell(String cell) {
	this.cell = cell;
}
public String getCpu() {
	return cpu;
}
public void setCpu(String cpu) {
	this.cpu = cpu;
}
public String getGpu() {
	return gpu;
}
public void setGpu(String gpu) {
	this.gpu = gpu;
}
public String getMemory() {
	return memory;
}
public void setMemory(String memory) {
	this.memory = memory;
}
public String getOs() {
	return os;
}
public void setOs(String os) {
	this.os = os;
}
public String getRam() {
	return ram;
}
public void setRam(String ram) {
	this.ram = ram;
}
public String getScreen() {
	return screen;
}
public void setScreen(String screen) {
	this.screen = screen;
}
public String getSim() {
	return sim;
}
public void setSim(String sim) {
	this.sim = sim;
}


	
}
