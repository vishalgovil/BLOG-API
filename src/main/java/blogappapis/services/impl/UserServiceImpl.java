package blogappapis.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import blogappapis.entities.User;
import blogappapis.payloads.UserDto;
import blogappapis.repositories.UserRepo;
import blogappapis.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user= this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto update(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}
	
	private User dtoToUser(UserDto userDto)
	{
		User user= new User();
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	
	public UserDto userToDto(User user)
	{
		UserDto userDto= new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());

		return userDto;
	}
}
