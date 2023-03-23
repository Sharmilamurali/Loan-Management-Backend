package com.cts.loan.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
@Table(name = "USERDATA")
public class UserEntity {
	@Column(name = "user_id")
	private String userid;

	@Column(name = "user_password")
	@JsonIgnore
	private String userpassword;

	@Id
	@Column(name = "user_name")
	private String username;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "Role_Id", nullable = false)
	private RolesEntity roles;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<LoanEntity> loanEntity;
}
