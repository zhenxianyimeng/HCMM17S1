package HCMM17S1;

public class Member {
	private String name;
	
	private String birthday;
	
	private String mobile;
	
	private String pass;
	
	private String free;
	
	private String address;
	
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@Override
	public String toString() {
		return "Member [name=" + name + ", birthday=" + birthday + ", mobile=" + mobile + ", pass=" + pass + ", free="
				+ free + ", address=" + address + ", email=" + email + "]";
	}
	
	
}
