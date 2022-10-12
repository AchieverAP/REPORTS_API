package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ELIGIBLITY_DETAILS")
@Data
public class EligiblityDetails {
	@Id
	@GeneratedValue
	private int eligid;						
	private String name;				
	private long mobile;				
	private String email;				
	private Character gender;				
	private long ssn;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private LocalDate createDate;		
	private LocalDate updateDate;
	private String createdBy;
	private String updatedBy;
}
