package com.example.demo;

public class Notes {
int id1;
String datas;
boolean isDone;
public Notes(int id1, String datas, boolean isDone) {
	super();
	this.id1 = id1;
	this.datas = datas;
	this.isDone = isDone;
}
public int getId1() {
	return id1;
}
public void setId1(int id1) {
	this.id1 = id1;
}
public String getDatas() {
	return datas;
}
public void setDatas(String datas) {
	this.datas = datas;
}
public boolean isDone() {
	return isDone;
}
public void setDone(boolean isDone) {
	this.isDone = isDone;
}
}
