
public class User {
	
	private String u;
	private String p;
	private String n;
	private String c;
	private boolean value;
	
	public User(String us,String pa,String no,String co){
	
		this.u = us;
		this.p = pa;
		this.n = no;
		this.c = co;
	}
	
	public String getUsername() {
		return this.u;
	}
	
	public String getPwd() {
		return this.p;
	}
	
	public String getName() {
		return this.n;
	}

	public String getCogn() {
		return this.c;
	}
	
	public void setLog(boolean value) {
		this.value = value;
	}
	
	public boolean getLog() {
		return this.value;
	}
}
