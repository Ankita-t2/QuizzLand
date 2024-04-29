package com.exam.controller;



import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.exam.helper.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.exam.helper.UserNotFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class Usercontroller {
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	public static String uploadDir = "D:\\examportal\\examserver\\src\\main\\resources\\Image";
//	@PostMapping("/upload-image/{username}")
//	public ResponseEntity<String> uploadImage(@RequestParam("username") String username, @RequestParam("img") MultipartFile file) {
//		try {
//
//
//			// Get the user by username
//			User user = userService.getUser(username);
//			if (user == null) {
//				throw new UserNotFoundException("User not found with username: " + username);
//			}
//
//			// Save the file to the upload directory
//			Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
//			Files.write(filePath, file.getBytes());
//
//			// Update the user's profile image
////			user.setProfile(file.getOriginalFilename().getBytes());
//			userService.updateUser(user);
//
//			return ResponseEntity.ok("Image uploaded successfully for user: " + username);
//		} catch (IOException | UserNotFoundException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
//		}
//	}

	@PostMapping("/upload-image/{username}")
	public ResponseEntity<?> uploadImage(@PathVariable("username") String username, @RequestParam("img") MultipartFile file) {
		try {
			User user = userService.getUser(username);
			if (user == null) {
				throw new UserNotFoundException("User not found with username: " + username);
			}

			// Set the image data into the User entity
			user.setProfile(file.getBytes());


			// Update the user with the new image data
		userService.updateUser(user);

			return ResponseEntity.ok().body(new UploadImageResponse("Image uploaded successfully for user: " + username, user.getProfile()));
		} catch (IOException | UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
		}
	}



	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception{
		//Set<UserRole> roles=new HashSet<>();
//		user.setProfile("default.jpg");
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		Set<UserRole> roles=new HashSet<>();
		
		Role role=new Role();
		role.setRoleId(45L);
		role.setRoleName("Normal");
		
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		roles.add(userRole);
		return	this.userService.createUser(user,roles );
		
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
		
	}
	
	//delete the user by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")Long userId) {
		this.userService.deleteUser(userId);
	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<String> exceptionHandler(UserFoundException ex){

	        return new ResponseEntity<>("User Found", HttpStatus.FOUND);
	}


	@GetMapping("/profile-image/{username}")
	public ResponseEntity<Resource> getProfileImage(@PathVariable String username) throws IOException {
		byte[] imageData = userService.getUser(username).getProfile();
		if (imageData == null) {
			// If the user doesn't have a profile image, return an empty image or an appropriate error response
			// For example:
			// return ResponseEntity.notFound().build();
			// or
//			 Resource defaultImage = new ByteArrayResource(Files.readAllBytes(Paths.get("path_to_default_image")));
//			 return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(defaultImage);
		}

		ByteArrayResource resource = new ByteArrayResource(imageData);


		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + username + ".jpg\"")
				.contentType(MediaType.IMAGE_JPEG)
				.contentLength(imageData.length)
				.body(resource);
	}


	private static class UploadImageResponse {
		private final String message;
		private final byte[] imageData;

		public UploadImageResponse(String message, byte[] imageData) {
			this.message = message;
			this.imageData = imageData;
		}

		public String getMessage() {
			return message;
		}

		public byte[] getImageData() {
			return imageData;
		}
	}



}
