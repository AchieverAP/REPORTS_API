package in.ashokit.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;

import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;

public interface EligiblityService {

	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
	
	public List<SearchResponse> search(SearchRequest request);
	
	public void genrateExcel(HttpServletResponse response) throws Exception;
	
	public void genratePdf(HttpServletResponse response)throws DocumentException, IOException;
	
	
}
