package tradehero.json.dto;

public  class ProfileDTO {

	public ProfileDTO()
	{
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String email;
	public String displayName;
	public String firstName;
}
