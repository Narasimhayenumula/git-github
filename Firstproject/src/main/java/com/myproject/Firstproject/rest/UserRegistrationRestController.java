package com.myproject.Firstproject.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.CodecConfigurer.CustomCodecs;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.Firstproject.DTOEntity.UserDTO;
import com.myproject.Firstproject.Exception.CustomerErrorType;
import com.myproject.Firstproject.repository.UserJpaRepsitory;
@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {
		public static final Logger logger=LoggerFactory.getLogger(UserRegistrationRestController.class);
		private UserJpaRepsitory userJpaRepsitory;
		@Autowired
		public void setUserJpaRepsitory(UserJpaRepsitory userJpaRepsitory) {
			this.userJpaRepsitory = userJpaRepsitory;
		}
	
		@GetMapping("/")
		public ResponseEntity<List<UserDTO>> listAllUsers(Model theModel){
			theModel.addAttribute("theDate",new java.util.Date());
			List<UserDTO> users=userJpaRepsitory.findAll();
			if(users.isEmpty()) {
				return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
		}
		@GetMapping("/{id}")
		public ResponseEntity<UserDTO> getUserById(@PathVariable("id")final Long id){
			UserDTO user=userJpaRepsitory.findByName("name");
			if(userJpaRepsitory.findById(id)==null) {
				return new ResponseEntity<UserDTO>(new CustomerErrorType("User with id"+id+"not found"),(HttpStatus.NOT_FOUND));
			}
			return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
		}
		@PostMapping(value="/",consumes=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<UserDTO> createUser(@RequestBody final UserDTO user){
			if(userJpaRepsitory.findByName(user.getName())!=null) {
				return new ResponseEntity<UserDTO>(new CustomerErrorType("User with id"+user.getName()+"not found"),(HttpStatus.CONFLICT));
			}
			userJpaRepsitory.save(user);
			return new ResponseEntity<UserDTO>(user,HttpStatus.CREATED);
		}
		@PutMapping(value="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long id,@RequestBody UserDTO user){
			if(userJpaRepsitory.findById(id)==null) {
				return new ResponseEntity<UserDTO>(new CustomerErrorType("Unable to update.user with id"+id+"not found"),HttpStatus.NOT_FOUND);
			}
			user.setName(user.getName());
			user.setAdress(user.getAdress());
			user.setEmail(user.getEmail());
			userJpaRepsitory.saveAndFlush(user);
			return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
		}
		@DeleteMapping(value="/{id}")
		public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id){
			if(userJpaRepsitory.findById(id)==null) {
				return new ResponseEntity<UserDTO>(new CustomerErrorType("no data for deleting.use with id"+id+"not found"),HttpStatus.NOT_FOUND);
			}
			userJpaRepsitory.deleteById(id);
			return new ResponseEntity<UserDTO>(new CustomerErrorType("Deleted user with id"+id+"not found"),HttpStatus.NO_CONTENT);
		}
}



















