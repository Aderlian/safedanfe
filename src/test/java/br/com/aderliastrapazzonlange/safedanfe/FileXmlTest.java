package br.com.aderliastrapazzonlange.safedanfe;

import java.io.File;

import org.junit.Test;

import br.com.aderliastrapazzonlange.safedanfe.models.FileManager;
import br.com.aderliastrapazzonlange.safedanfe.models.FileXml;

public class FileXmlTest {
	
	private FileXml fileXml= new FileXml(); 
	private String path = "/home/aderlian/Downloads/XML/teste.xml";
	private File file = FileManager.openFile(path);

	
	@Test
	public void loadXmlTest() {
		System.out.println(fileXml.loadXml(file));
	}
}
