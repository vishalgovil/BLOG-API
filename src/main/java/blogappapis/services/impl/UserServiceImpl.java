package blogappapis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import blogappapis.config.AppConstants;
import blogappapis.entities.Role;
import blogappapis.entities.User;
import blogappapis.exception.ResourceNotFoundException;
import blogappapis.payloads.UserDto;
import blogappapis.repositories.RoleRepo;
import blogappapis.repositories.UserRepo;
import blogappapis.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user= this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto update(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user= this.userRepo.findById(userId).orElseThrow((()-> new ResourceNotFoundException("User"," id ",userId)));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser=this.userRepo.save(user);		
		UserDto userDto1 = this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow((()-> new ResourceNotFoundException("User"," id ",userId)));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow((()-> new ResourceNotFoundException("User"," id ",userId)));
		
		this.userRepo.delete(user);
		
	}
	
	private User dtoToUser(UserDto userDto)
	{
		
		User user = this.modelMapper.map(userDto, User.class);
//		User user= new User();
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		return user;
		
		return user;
		
	}
	
	
	public UserDto userToDto(User user)
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		
//		UserDto userDto= new UserDto();
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
//
//		return userDto;
		
		return userDto;
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		
		user.getRoles().add(role);
		
		User newUser =  this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}
	

	
	
}
