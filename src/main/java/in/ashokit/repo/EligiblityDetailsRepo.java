package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.EligiblityDetails;

@Repository
public interface EligiblityDetailsRepo extends JpaRepository<EligiblityDetails, Integer>{

	@Query("select distinct(planName) from EligiblityDetails")
	public List<String> getUniquePlansName();
	
	@Query("select distinct(planStatus) from EligiblityDetails")
	public List<String> getUniquePlanStatus();
	
	
	
}
