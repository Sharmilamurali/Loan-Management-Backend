package com.cts.loan.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
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
@Table(name = "ROLES")
public class RolesEntity {
	@Id
	@Column(name = "Role_Id")
	private int roleId;

	@Column(name = "Role_Name")
	private String roleName;

	@OneToMany(mappedBy = "roles")
	@JsonManagedReference
	private List<UserEntity> userEntity;
}
