package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.aderliastrapazzonlange.safedanfe.conf.AppWebConfiguration;
import lombok.Getter;

@Getter
@Component
public class FileXml {
	
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document fileXml;
	
	public FileXml() {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void document(File path, FileManager file) {
		
		try {
			fileXml = builder.parse(path);
					//.replaceChild("&", "&amp;");
		} catch (SAXException e) {
			System.out.println("Occoreu uma exceção SAXException...");
			file.saveXml(AppWebConfiguration.getFilerootpath() +
					AppWebConfiguration.getFilepatherror() +
					path.getName(), path);
			file.deleteXml(path);
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Occoreu uma exceção IOException...");
			file.saveXml(AppWebConfiguration.getFilerootpath() +
					AppWebConfiguration.getFilepatherror() +
					path.getName(), path);
			file.deleteXml(path);
//			e.printStackTrace();
		}
	}
	
	public HashMap tagName(String[] tagNode, String[] tagElement) {
		NodeList emit;
		Element el;
		HashMap<String, String> dados = new HashMap<String, String>();
		
		for(int i =0; i < tagNode.length; i++){
			emit = fileXml.getElementsByTagName(tagNode[i]);
			
			for(int a = 0; a < emit.getLength(); a++) {
				el = (Element) emit.item(a);
				for(int b = 0; b < tagElement.length; b++) {
					if(el.getElementsByTagName(tagElement[b]).item(0) !=null) {
						dados.put(tagNode[i] +"."+ tagElement[b],el.getElementsByTagName(tagElement[b]).item(0).getTextContent());
					}
				}
			}
		}
		
		return dados;
		
	}
	
	public XmlEnum whatIs(HashMap data) {
		
		XmlEnum xmlEnum = null;
		
		if(data.get("ide.nNF") != null &&
				data.get("infProt.chNFe") != null &&
				data.get("ICMSTot.vNF") != null &&
				data.get("ide.dhEmi") != null &&
				data.get("dest.CNPJ") != null &&
				data.get("emit.xNome") != null &&
				data.get("emit.xMun") != null &&
				data.get("emit.UF") != null &&
				(data.get("emit.CNPJ") != null ||
				data.get("emit.CPF") != null)
				) {
			xmlEnum = xmlEnum.Invoice;
			System.out.println("é nota fiscal");
			return xmlEnum;
		}
		
		if(data.get("detEvento.descEvento") != null &&
				data.get("detEvento.xJust") != null &&
				data.get("infEvento.tpEvento") != null &&
				data.get("infEvento.dhEvento") != null) {
			xmlEnum = xmlEnum.CancellationLetter;
			System.out.println("é carta de correção");
			return xmlEnum;
		}
		xmlEnum = xmlEnum.Undefined;
		return xmlEnum;
		
	}
	
	
	
	

	
	
	
	
	
	
	
}
