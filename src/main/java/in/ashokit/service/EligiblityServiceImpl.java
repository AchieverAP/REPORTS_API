package in.ashokit.service;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.entity.EligiblityDetails;
import in.ashokit.repo.EligiblityDetailsRepo;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;

@Service
public class EligiblityServiceImpl implements EligiblityService {

	@Autowired
	EligiblityDetailsRepo eligRepo;
	
	@Override
	public List<String> getUniquePlanNames() {
       List<String> plansName = eligRepo.getUniquePlansName();
		return plansName;
	}

	@Override
	public List<String> getUniquePlanStatus() {
		List<String> planStatus = eligRepo.getUniquePlanStatus();
		return null;
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		// TODO Auto-generated method stub
		
		List<SearchResponse> response = new ArrayList<>();
		
		EligiblityDetails queryBuilder = new EligiblityDetails();
		
		if(request.getPlanName()!=null && request.getPlanName()!="") {
			queryBuilder.setPlanName(request.getPlanName());
		}
		if(request.getPlanStatus()!=null && request.getPlanStatus()!="") {
			queryBuilder.setPlanName(request.getPlanStatus());
		}
		if(request.getPlanStartDate() !=null) {
			queryBuilder.setPlanStartDate(request.getPlanStartDate());
		}
		if(request.getPlanEndDate() !=null) {
			queryBuilder.setPlanEndDate(request.getPlanEndDate());
		}
		
		Example<EligiblityDetails> example = Example.of(queryBuilder);
		List<EligiblityDetails> entities = eligRepo.findAll(example);
		
		for(EligiblityDetails entity:entities) {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(entity, sr);
			response.add(sr);
		}
			
		return response;
	}

	@Override
	public void genrateExcel(HttpServletResponse response) throws IOException {
		
		List<EligiblityDetails> findAll = eligRepo.findAll();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Plans");
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Mobile");
		headerRow.createCell(2).setCellValue("Email");
		headerRow.createCell(3).setCellValue("Gender");
		
		int rowIndex=1;
		for(EligiblityDetails details:findAll) {
			XSSFRow row = sheet.createRow(rowIndex);
			row.createCell(0).setCellValue(details.getName());
			row.createCell(1).setCellValue(details.getMobile());
			row.createCell(2).setCellValue(details.getEmail());
			row.createCell(3).setCellValue(details.getGender()+"");
			rowIndex++;	
			
		}
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
	}

	@Override
	public void genratePdf(HttpServletResponse response) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		List<EligiblityDetails> findAll = eligRepo.findAll();
			
		 Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.ORANGE);
	         
	        Paragraph p = new Paragraph("Search Report", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(5);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
	        table.setSpacingBefore(10);
	        
	        
	        //write table header
	        
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	         font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("Sr. NO", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Name", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Mobile", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Email", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Gender", font));
	        table.addCell(cell);       
		
	        
	        //write Table data
	        
	        
	        for (EligiblityDetails details : findAll) {
	            table.addCell(String.valueOf(details.getEligid()));
	            table.addCell(details.getName());
	            table.addCell(String.valueOf(details.getMobile()));
	            table.addCell(details.getEmail());
	            table.addCell(String.valueOf(details.getGender()));
	        }
	        
	        document.add(table);
	         
	        document.close();
		
	}
  
}
