package jsp.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
		   uniqueConstraints = @UniqueConstraint(columnNames = "departmentName")
		)
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer departmentId;
	

	private String departmentName;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Doctor> doctors;

	public synchronized Integer getDepartmentId() {
		return departmentId;
	}

	public synchronized void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public synchronized String getDepartmentName() {
		return departmentName;
	}

	public synchronized void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public synchronized List<Doctor> getDoctors() {
		return doctors;
	}

	public synchronized void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}


}
