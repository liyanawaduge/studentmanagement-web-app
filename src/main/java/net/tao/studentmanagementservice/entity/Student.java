package net.tao.studentmanagementservice.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Name must not be blank")
	@Column(length=45, nullable=false)
	private String name;
	
	@NotBlank(message = "Address must not be blank")
	@Column(length=45, nullable=true)
	private String address;
	
	@NotBlank(message = "Gender must not be blank")
	@Column(length=1, nullable=false)
	private String gender = "M";
	
	@NotNull(message = "DoB must not be blank")
	@PastOrPresent(message = "Date Cannot be future or present")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable=true)
	private Date dob;
	
	@NotBlank(message = "Email must not be blank")
	@Email
	@Column(length=45, nullable=true)
	private String email;
	
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Enter valid mobile number")
	@Column(length=15, nullable=true)
	private String mobile;
	
	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Enter valid phone number")
	@Column(length=15, nullable=true)
	private String phone;

}
