package kr.or.ddit.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class SendPdf {
	

	public void makePDF(File file){
        String DEST2 = null;

		try {
			PDDocument doc = PDDocument.load(file);
			
			PDPage page = doc.getPage(0);
			
			PDPageContentStream contentStream = new PDPageContentStream(doc, page);
			
			InputStream fontStream = new FileInputStream("NanumBarunGothic.ttf");
			
			PDImageXObject pdImage = PDImageXObject.createFromFile("resume.jpg", doc);
			PDImageXObject dojang = PDImageXObject.createFromFile("sign.png", doc);
			
			contentStream.drawImage(pdImage,  0, 0, PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight());
			contentStream.drawImage(dojang, 324, 544);
			
			PDType0Font fontNanum = PDType0Font.load(doc, fontStream);
			
			contentStream.setFont(fontNanum, 12);
			
			contentStream.beginText();
			
			contentStream.newLineAtOffset(140, 675);
			
			contentStream.newLineAtOffset(0, 0);	
			contentStream.showText("이름");

			contentStream.newLineAtOffset(0, -34);	
			contentStream.showText("나이");
			
			contentStream.newLineAtOffset(0, -30);	
			contentStream.showText("ㅇㅇ");
			contentStream.newLineAtOffset(253, 62);	
			contentStream.showText("ㄴㄴ");

			contentStream.newLineAtOffset(0, -24);	
			contentStream.showText("ㄱㄴㅇㄹ");
			
			
			contentStream.newLineAtOffset(-395, 148);	
			contentStream.showText("V");

			contentStream.newLineAtOffset(3, -234);

	        contentStream.endText();
	        contentStream.close();
	        
	        DEST2 = "../sourceFolder/asd.pdf";

	        doc.save(new File(DEST2));
	        doc.close();

		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
