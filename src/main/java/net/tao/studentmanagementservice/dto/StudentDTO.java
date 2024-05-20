package net.tao.studentmanagementservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StudentDTO {

	private String name;
	
	private String address;
	
	private String gender;
	
	private Date dob;
	
	private String email;
	
	private String mobile;
	
	private String phone;
}
