package in.ashokit.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;
import in.ashokit.service.EligiblityService;

@RestController
public class ReportController {

	
	@Autowired
	EligiblityService eligiblityService;
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanNames(){		
	List<String> planNames = eligiblityService.getUniquePlanNames();
	return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	
	
	@GetMapping("/statuses")
	public ResponseEntity<List<String>> getPlanStatus(){		
	List<String> status = eligiblityService.getUniquePlanStatus();
	return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest SearchRequest ){
		List<SearchResponse> result = eligiblityService.search(SearchRequest);
		return new ResponseEntity<List<SearchResponse>>(result,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xlsx";
		response.setHeader(headerKey, headerValue);
		eligiblityService.genrateExcel(response);
		
	}
	
	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.pdf";
		response.setHeader(headerKey, headerValue);
		eligiblityService.genratePdf(response);
		
	}
}
