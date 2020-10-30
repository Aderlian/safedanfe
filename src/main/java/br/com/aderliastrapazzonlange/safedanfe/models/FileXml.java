package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Essa clase é responsavel por carregar e validar o arquivo XML 
 * this class is responsible from load and validate the xml file.
 * @author Aderlian Strapazzon Lange
 *
 */
@Component
public class FileXml {
// carregar o arquivo na memroia
// verificar se é um arquivo valido
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document fileXml;
	private boolean isXmlValid = false;
	
	public boolean loadXml(File path) {
		return checkXml(path);
	}
	
	private void openXml(File path) {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			fileXml = builder.parse(path);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private boolean checkXml(File path) {
		String fileName = path.getName();
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
		if(fileExtension.equals("XML")) {
			isXmlValid = true;
		}
		return isXmlValid;
	}
}
