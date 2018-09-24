package HJ.hospitalityjobsjobseeker;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class SampleTextToWord {
  public static void main(String[] args) throws IOException, InvalidFormatException 
	    {   
	  	XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Sample Text to add a word file programatically");
        run.setFontSize(14);
        FileOutputStream out = new FileOutputStream("C:\\Users\\Ethreyas Admin\\Desktop\\Success.docx");
        document.write(out); 
        out.close();
        document.close();
    }
	}

