package modele.util;

import java.util.List;

public class Location {
	private String _message;
	private List _lstCopies;
	
	public String getMessage() { return _message; }
	public List getCopies() { return _lstCopies; }
	
	public Location(String msg, List lstCopies){
		_message = msg;
		_lstCopies = lstCopies;
	}
}
