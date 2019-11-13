package MainShowInterface;

public class ErrorString {
public String string;//错误字符串
public int line;//在第几行
public int index;//从哪个位置开始(清楚空格后)
public String getString() {
	return string;
}
public void setString(String string) {
	this.string = string;
}
public int getLine() {
	return line;
}
public void setLine(int line) {
	this.line = line;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
}
