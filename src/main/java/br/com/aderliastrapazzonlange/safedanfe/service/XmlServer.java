package br.com.aderliastrapazzonlange.safedanfe.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.aderliastrapazzonlange.safedanfe.conf.AppWebConfiguration;
import br.com.aderliastrapazzonlange.safedanfe.models.Company;
import br.com.aderliastrapazzonlange.safedanfe.models.Danfe;
import br.com.aderliastrapazzonlange.safedanfe.models.Provider;
import br.com.aderliastrapazzonlange.safedanfe.repository.CompanyRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.ProviderRepository;

@Component
public class XmlServer {

	Provider provider = new Provider();
	Company company = new Company();
	Danfe danfe = new Danfe();

	public Document openXml(File path) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document document = builder.parse(path);
		return document;

	}

	public Provider providerXml(Document document, ProviderRepository providerRepository) {
		NodeList emit = document.getElementsByTagName("emit");
		System.out.println("tamanho do retorno provider: " + emit.getLength());
		Element el = (Element) emit.item(0);
		String cnpj = null;
		if(el.getElementsByTagName("CNPJ").item(0) !=null) {
			cnpj = el.getElementsByTagName("CNPJ").item(0).getTextContent();
		}else if(el.getElementsByTagName("CPF").item(0) !=null) {
			cnpj = el.getElementsByTagName("CPF").item(0).getTextContent();
		}
		System.out.println("conteudo da variavel cnpj: " + cnpj);
		Optional<Provider> opt = providerRepository.findByCnpj(cnpj);

		if (!opt.isEmpty()) {
			provider = opt.get();
		} else {
			provider = new Provider();
			String name = el.getElementsByTagName("xNome").item(0).getTextContent();
			name = name.replaceAll("/", "");
			name = name.replaceAll("&", "");

			NodeList enderEmit = document.getElementsByTagName("enderEmit");
			el = (Element) enderEmit.item(0);
			String city = el.getElementsByTagName("xMun").item(0).getTextContent();
			String estado = el.getElementsByTagName("UF").item(0).getTextContent();
			provider.setCnpj(cnpj);
			provider.setCity(city);
			provider.setName(name);
			provider.setEstado(estado);
			provider = providerRepository.save(provider);
		}

		return provider;

	}

	public Company companyXml(Document document, CompanyRepository companyRepository) {
		NodeList dest = document.getElementsByTagName("dest");
		Element el = (Element) dest.item(0);
		String cnpj = el.getElementsByTagName("CNPJ").item(0).getTextContent();
		Optional<Company> opt = companyRepository.findByCnpj(cnpj);
		if (!opt.isEmpty()) {
			company = opt.get();
		} else {
			company.setFilePath(AppWebConfiguration.getFilerootpath() + AppWebConfiguration.getFilepathother());
		}

		return company;
	}

	public Danfe danfeXml(Document document, Provider provider, Company companyRepository) {

		NodeList node = document.getElementsByTagName("ide");
		Element el = (Element) node.item(0);
		String numberNFE = el.getElementsByTagName("nNF").item(0).getTextContent();
		System.out.println("numero Nfe: " + numberNFE);
		String dataEmissao = el.getElementsByTagName("dhEmi").item(0).getTextContent();
		dataEmissao = dataEmissao.substring(0, 10);
		LocalDate issuanceDate = LocalDate.parse(dataEmissao);

		node = document.getElementsByTagName("infProt");
		el = (Element) node.item(0);
		String keyNFE = el.getElementsByTagName("chNFe").item(0).getTextContent();

		node = document.getElementsByTagName("ICMSTot");
		el = (Element) node.item(0);
		BigDecimal amount = new BigDecimal(el.getElementsByTagName("vNF").item(0).getTextContent());

		danfe.setCompany(company);
		danfe.setProvider(provider);
		danfe.setNumberNFE(numberNFE);
		danfe.setKeyNFE(keyNFE);
		danfe.setAmount(amount);
		danfe.setIssuanceDate(issuanceDate);
		danfe.setCreationDate(LocalDate.now());

		return danfe;
	}

	public void deleteXml(File filePath) {
		File file = filePath;
		file.delete();
		System.out.println("Arquivo Deletado...");
	}

	public void saveXml(String newPath, File filePath) throws Exception {

		InputStream in = new FileInputStream(filePath);
		OutputStream company = new FileOutputStream(newPath);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			company.write(buf, 0, len);
		}
		company.close();
		in.close();

	}

}
