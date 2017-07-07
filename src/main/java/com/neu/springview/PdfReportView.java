package com.neu.springview;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.neu.pojo.Vitalsign;

public class PdfReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("model" + model);
			List<Vitalsign> vsList = (List) model.get("vitalList");
			String firstName = (String) model.get("firstName");
			String lastName = (String) model.get("lastName");
			response.addHeader("content-disposition", "inline; filename=report.pdf");
			 Font font_times_16_bold_black = new Font(Font.TIMES_ROMAN, 18,
					 Font.BOLD, Color.BLACK);
			 
					Paragraph prg1 = new Paragraph("Patient Name:",
							 font_times_16_bold_black);
					//pdfdoc.add(prg1);
					pdfdoc.addSubject("Vital Signs Report");
					pdfdoc.addHeader("Header", "Vital Signs Report");
					pdfdoc.addTitle("Vital Sign Report");
					Font font_courier_16_italic_red = new Font(Font.COURIER, 16,
							 Font.ITALIC, Color.black);
					//Chunk c1 = new Chunk(lastName + ", " +firstName, font_courier_16_italic_red);
					// pdfdoc.add(c1);
					 Chunk c2 = new Chunk("", font_courier_16_italic_red);
					 pdfdoc.add(c2);
					 pdfdoc.add(c2);
					 pdfdoc.add(c2);
					pdfdoc.add(Chunk.NEWLINE);
					pdfdoc.add(Chunk.NEWLINE);
					pdfdoc.add(Chunk.NEWLINE);
					pdfdoc.add(Chunk.NEWLINE);
					pdfdoc.add(Chunk.NEWLINE);
					pdfdoc.add(Chunk.NEWLINE);
					pdfdoc.add(Chunk.NEWLINE);
					pdfdoc.add(Chunk.NEWLINE);
			if(null != vsList && vsList.size() >0)
			{
				PdfPTable table = new PdfPTable(new float[] { 2, 2, 2, 2, 2, 2 });
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell("Capture Date");
				table.addCell("Respiration Rate");
				table.addCell("Heart Rate");
				table.addCell("Weight");
				table.addCell("BP");
				table.addCell("Normal/Abnormal");
				table.setHeaderRows(1);
				PdfPCell[] cells = table.getRow(0).getCells();
				for (int j = 0; j < cells.length; j++) {
					cells[j].setBackgroundColor(Color.LIGHT_GRAY);
				}
				for (int i = 0; i < vsList.size(); i++) {
					PdfPCell captureDate = new PdfPCell(new Phrase("" +vsList.get(i).getCaptureDate()));
					table.addCell(captureDate);
					PdfPCell respRate = new PdfPCell(new Phrase("" + vsList.get(i).getRespirationRate()));
					table.addCell(respRate);
					PdfPCell heartRate = new PdfPCell(new Phrase("" + vsList.get(i).getHeartRate()));
					table.addCell(heartRate);
					PdfPCell weight = new PdfPCell(new Phrase("" + vsList.get(i).getWeight()));
					table.addCell(weight);
					PdfPCell bp = new PdfPCell(new Phrase("" + vsList.get(i).getSysBp()));
					table.addCell(bp);
					PdfPCell conc = new PdfPCell(new Phrase("" + vsList.get(i).getConclusion()));
					table.addCell(conc);
					String conclusion = vsList.get(i).getConclusion();
					if(null != vsList.get(i).getConclusion() && vsList.get(i).getConclusion().equalsIgnoreCase("Abnormal"))
						{System.out.println("inside if else");
//							captureDate.setBackgroundColor(Color.red);
//							respRate.setBackgroundColor(Color.red);
//							heartRate.setBackgroundColor(Color.red);
//							weight.setBackgroundColor(Color.red);
//							bp.setBackgroundColor(Color.red);
//							conc.setBackgroundColor(Color.red);
							PdfPCell[] cellss = table.getRow(i+1).getCells();
							for (int j = 0; j < cellss.length; j++) {
								cellss[j].setBackgroundColor(Color.red);
							}
							
						}
						
					else
					{
//						captureDate.setBackgroundColor(Color.green);
//						respRate.setBackgroundColor(Color.green);
//						heartRate.setBackgroundColor(Color.green);
//						weight.setBackgroundColor(Color.green);
//						bp.setBackgroundColor(Color.green);
//						conc.setBackgroundColor(Color.green);
						PdfPCell[] cellsss = table.getRow(i+1).getCells();
						for (int j = 0; j < cellsss.length; j++) {
							cellsss[j].setBackgroundColor(Color.green);
						}
					}
				}
				
						
				pdfdoc.open();
				pdfdoc.add(table);
				//pdfdoc.close();
			}
			else
			{
				 Font font_times_16_bold_green = new Font(Font.TIMES_ROMAN, 18,
				 Font.BOLD, Color.GREEN);
				Paragraph prg2 = new Paragraph("No records found!",
						 font_times_16_bold_green);
				pdfdoc.add(prg2);
			}
		// Font font_helvetica_16_normal_blue = new Font(Font.HELVETICA, 16,
		// Font.NORMAL, Color.BLUE);
		// Font font_courier_16_italic_red = new Font(Font.COURIER, 16,
		// Font.ITALIC, Color.RED);
		// Font font_times_16_bold_green = new Font(Font.TIMES_ROMAN, 18,
		// Font.BOLD, Color.GREEN);
		//
		// Chunk c1 = new Chunk("Chunk 1", font_courier_16_italic_red);
		// Chunk c2 = new Chunk("Chunk 2", font_courier_16_italic_red);
		//
		// Phrase phr1 = new Phrase("Phrase 1", font_helvetica_16_normal_blue);
		// Phrase phr2 = new Phrase("Phrase 2", font_helvetica_16_normal_blue);
		//
		// Paragraph prg1 = new Paragraph("Paragraph 1",
		// font_times_16_bold_green);
		// Paragraph prg2 = new Paragraph("Paragraph 2",
		// font_times_16_bold_green);
		//
		// pdfdoc.add(c1);
		// pdfdoc.add(c2);
		//
		// pdfdoc.add(phr1);
		// pdfdoc.add(phr2);
		//
		// pdfdoc.add(prg1);
		// pdfdoc.add(prg2);

		
		System.out.println("Done");
	}

}
