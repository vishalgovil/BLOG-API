package blogappapis.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@Email(message="email format is not correct")
	private String email;
	@NotEmpty
	@Size(min=4, message="Increase Size")
	private String name;
	
	@NotEmpty
	@Size(min=3,max=10, message="Password is too short")
	private String password;
	
	@NotEmpty
	private String about;
}
