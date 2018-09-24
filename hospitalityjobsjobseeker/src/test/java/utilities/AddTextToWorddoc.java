package utilities;

import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class AddTextToWorddoc {
	public static void textToWordDoc(String textToAdd, String Docpath) throws Exception
	    {   
	  try {
		XWPFDocument document = new XWPFDocument();
		  XWPFParagraph paragraph = document.createParagraph();
		  XWPFRun run = paragraph.createRun();
		  run.setText(textToAdd);
		  run.setFontSize(12);
		  FileOutputStream out = new FileOutputStream(Docpath);
		  document.write(out); 
		  out.close();
		  document.close();
	} catch (Exception e) {
		System.out.println(e);
	}
  }
}
