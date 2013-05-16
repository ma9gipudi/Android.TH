package tradehero.json;

import tradehero.json.dto.ProfileDTO;

public class User {
	public User()
	{
		
	}
	
	public User(ProfileDTO profile)
	{
		email = profile.email;
		displayName= profile.displayName;
		firstName=profile.firstName;
				
		
	}
	public String email;
	public String displayName;
	public String firstName;
}
