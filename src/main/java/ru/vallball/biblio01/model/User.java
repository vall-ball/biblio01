package ru.vallball.biblio01.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	private static final Logger logger = LoggerFactory.getLogger(User.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, max = 30)
	private String login;

	@NotNull
	@Size(min = 3, max = 300)
	private String password;

	@NotNull
	@Size(min = 3, max = 30)
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min = 3, max = 30)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	@NotNull
	@Column(name = "date_of_birth")
	@JsonFormat(pattern = "yyyy.MM.dd")
	private LocalDate dateOfBirth;
		
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.getAuthority()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String createLogin(String firstName, String lastName) {
		StringBuilder builder = new StringBuilder();
		if (firstName.length() <=4)	builder.append(firstName);
		else builder.append(firstName.substring(0,4));
		logger.info(builder.toString());
		if (lastName.length() <=4)	builder.append(lastName);
		else builder.append(lastName.substring(0,4));
		logger.info(builder.toString());
		return builder.toString().toLowerCase();
	}
	
	public User toUser(PasswordEncoder passwordEncoder, String firstName, String lastName) {
		this.setPassword(passwordEncoder.encode(password));
		this.setLogin(this.createLogin(firstName, lastName));
		return this;
	}

}
