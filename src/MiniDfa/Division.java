package MiniDfa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import NFAtoDFA.NewStatus;
//»®·Ö
public class Division{
	
public List<List<Integer>> division=new ArrayList<>();
public int startStatus;
public  List<Integer> endStatus=new ArrayList<>();
public static List<String> enterwordDFA=new ArrayList<>();


public int getStartStatus() {
	return startStatus;
}

public void setStartStatus(int startStatus) {
	this.startStatus = startStatus;
}

public List<Integer> getEndStatus() {
	return endStatus;
}

public void setEndStatus(List<Integer> endStatus) {
	this.endStatus = endStatus;
}

public List<List<Integer>> getDivision() {
	return division;
}

public void setDivision(List<List<Integer>> division) {
	this.division = division;
}
}
