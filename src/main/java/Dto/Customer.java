package Dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;
@Data
@Entity
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private  int id;
private String fullname;
private String password;
private String email;
private long number;
private String gender;
private LocalDate dob;
@Lob
private byte[] picture;
private int age;
private String country;
}
