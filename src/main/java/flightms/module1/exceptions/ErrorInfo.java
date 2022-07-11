package flightms.module1.exceptions;

import java.time.LocalDateTime;

public class ErrorInfo {

	@Override
	public String toString() {
		return "ErrorInfo [timeStamp=" + timeStamp + ", msg=" + msg + ", url=" + url + "]";
	}
	LocalDateTime timeStamp;
	String msg;
	String url;
	//String suggestion;
	public ErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorInfo(LocalDateTime timeStamp, String msg, String url/*, String suggestion*/) {
		super();
		this.timeStamp = timeStamp;
		this.msg = msg;
		this.url = url;
	//	this.suggestion=suggestion;
	}
	public LocalDateTime getTimesatmp() {
		return timeStamp;
	}
	public void setTimesatmp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	/*public void setSuggestion (String suggestion)
	{
		this.suggestion=suggestion;
	}
	public String getSuggestion()
	{
		return suggestion;
	}*/
	
}