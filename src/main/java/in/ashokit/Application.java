package in.ashokit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import in.ashokit.entity.EligiblityDetails;
import in.ashokit.repo.EligiblityDetailsRepo;

@SpringBootApplication
public class Application implements CommandLineRunner  {

	@Autowired
	EligiblityDetailsRepo elr;
	
	public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	    
	    
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		EligiblityDetails e1 = new EligiblityDetails();
	    e1.setEmail("a.patil@gmail.com");
	    e1.setGender('M');
	    e1.setName("Akshay");
	    e1.setMobile(9545884547L);
	    e1.setPlanName("Medical");
	    e1.setPlanStatus("Accepted");
	    
	    
	    EligiblityDetails e2 = new EligiblityDetails();
	    e2.setEmail("ashish@gmail.com");
	    e2.setGender('M');
	    e2.setName("Ashish");
	    e2.setMobile(9822182143L);
	    e2.setPlanName("Medical");
	    e2.setPlanStatus("Accepted");
	    
	    
	    EligiblityDetails e3 = new EligiblityDetails();
	    e3.setEmail("love@gmail.com");
	    e3.setGender('F');
	    e3.setName("Ankita");
	    e3.setMobile(9545954595L);
	    e3.setPlanName("CCAP");
	    e3.setPlanStatus("Denied");

	    elr.save(e1);
	    elr.save(e2);
	    elr.save(e3);
	}

}
