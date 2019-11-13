package MainShowInterface;

import java.util.ArrayList;
import java.util.List;

import MiniDfa.MiniDfa;
//构造的NFA集合
public class DFAList {
public static List<MiniDfa> dfas=new ArrayList<>();

public static List<MiniDfa> getDfas() {
	return dfas;
}

public static void setDfas(List<MiniDfa> dfas) {
	DFAList.dfas = dfas;
}
}
