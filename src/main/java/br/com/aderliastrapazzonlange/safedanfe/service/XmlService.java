package br.com.aderliastrapazzonlange.safedanfe.service;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aderliastrapazzonlange.safedanfe.conf.AppWebConfiguration;
import br.com.aderliastrapazzonlange.safedanfe.models.CancellationLetter;
import br.com.aderliastrapazzonlange.safedanfe.models.Company;
import br.com.aderliastrapazzonlange.safedanfe.models.Danfe;
import br.com.aderliastrapazzonlange.safedanfe.models.FileManager;
import br.com.aderliastrapazzonlange.safedanfe.models.FileXml;
import br.com.aderliastrapazzonlange.safedanfe.models.Provider;
import br.com.aderliastrapazzonlange.safedanfe.models.XmlEnum;
import br.com.aderliastrapazzonlange.safedanfe.repository.CancellationLetterRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.CompanyRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.DanfeRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.ProviderRepository;

@Service
public class XmlService {

	private final FileManager file;
	private final FileXml xml;
	private final ProviderRepository providerRepository;
	private final CompanyRepository companyRepository;
	private final DanfeRepository danfeRepository;
	private final CancellationLetterRepository cancellationLetterRepository;

	@Autowired
	public XmlService(FileManager file, FileXml xml, ProviderRepository providerRepository,
			CompanyRepository companyRepository, DanfeRepository danfeRepository,
			CancellationLetterRepository cancellationLetterRepository) {
		this.file = file;
		this.xml = xml;
		this.companyRepository = companyRepository;
		this.danfeRepository = danfeRepository;
		this.providerRepository = providerRepository;
		this.cancellationLetterRepository = cancellationLetterRepository;
	}

	public void readXml() {
		file.readDirector(AppWebConfiguration.getFilerootpath() + AppWebConfiguration.getFilepathxml());
		File[] paths = file.getPaths();

		for (int i = 0; i < file.getPaths().length; i++) {
			System.out.println("---------------------------------inicio -------------------------------");
			System.out.println("caminho do arquivo posição " + i + " diretorio: " + paths[i].getAbsolutePath());

			if (file.xmlCheck(paths[i])) {

				Company company;
				Provider provider;
				Danfe danfe;

				xml.document(paths[i], file);

				String[] tagNode = { "emit", "dest", "ide", "infProt", "ICMSTot", "infEvento", "detEvento" };
				String[] tagElement = { "mod", "CNPJ", "CPF", "xNome", "xMun", "UF", "nNF", "dhEmi", "chNFe", "vNF",
						"dhEvento", "tpEvento", "descEvento", "xJust" };

				HashMap<String, String> dados = xml.tagName(tagNode, tagElement);
				XmlEnum xmlEnum = xml.whatIs(dados);

				switch (xmlEnum) {

				case Invoice:
					System.out.println("é uma nota fiscal" + paths[i].getPath());
					
					company = getCompany(dados);
					System.out.println("retorno de company: " + company.toString());
					provider = getProvider(dados);
					System.out.println("retorno de provider: " + provider.toString());

					if (company.getId() == 0) {
						System.out.println("EMPRESA DE DESTINO NÃO CADASTRADA !!!!");
						file.saveXml(company.getFilePath() + paths[i].getName(), paths[i]);
						file.deleteXml(paths[i]);
					} else {

						danfe = getDanfe(dados, company, provider);
						System.out.println("retorno de danfe: " + danfe);

						if (danfe != null) {
							System.out.println("persistindo a danfe no banco de dados.");
							danfe = danfeRepository.save(danfe);
							file.saveXml(danfe.getCompany().getFilePath() + danfe.getProvider().getName() + "-"
									+ danfe.getNumberNFE() + ".xml", paths[i]);
							file.saveXml(AppWebConfiguration.getFilerootpath()
									+ AppWebConfiguration.getFilepathbkp() + danfe.getKeyNFE() + ".xml", paths[i]);

						}
						if (danfe == null) {
							System.out.println("Danfe já cadastrada...");
						}
					}
						file.deleteXml(paths[i]);
					
					
					break;
					
				case CancellationLetter:
					System.out.println("emit.CNPJ" + paths[i].getPath());
																				
					System.out.println("carta de cancelamento !!!!");
					danfe = findDanfe(dados.get("infEvento.chNFe"));
					if (danfe != null) {
						System.out.println("existe a nota !!!");
						CancellationLetter cancellationLetter;
						cancellationLetter = getCancellationLetter(dados, danfe);
						cancellationLetterRepository.save(cancellationLetter);
						file.saveXml(danfe.getCompany().getFilePath() + danfe.getProvider().getName() + "-"
								+ danfe.getNumberNFE() + "CANCELAMENTO-" + ".xml", paths[i]);
						file.saveXml(AppWebConfiguration.getFilerootpath() + AppWebConfiguration.getFilepathbkp()
								+ danfe.getKeyNFE() + "CANCELAMENTO-" + ".xml", paths[i]);

					} else {
						System.out.println("Nota fiscal não inportada ainda...");
						file.saveXml(AppWebConfiguration.getFilerootpath() + AppWebConfiguration.getFilepatherror()
								+ paths[i].getName(), paths[i]);

					}

					file.deleteXml(paths[i]);
								
					
					
					
					break;

				case Undefined:
					System.out.println("Não foi possivel identificar o tipo do arquivo!" + paths[i].getPath());
					file.saveXml(AppWebConfiguration.getFilerootpath() + AppWebConfiguration.getFilepatherror()
							+ paths[i].getName(), paths[i]);
					file.deleteXml(paths[i]);
					
				}

			}

			System.out.println("---------------------------------fim -------------------------------");
		}

	}

	private CancellationLetter getCancellationLetter(HashMap<String, String> dados, Danfe danfe) {
		CancellationLetter cancellationLetter = new CancellationLetter();
		cancellationLetter.setDanfe(danfe);
		cancellationLetter.setEvent(dados.get("detEvento.descEvento").toString());
		cancellationLetter.setJustification(dados.get("detEvento.xJust").toString());
		cancellationLetter.setTypeEvent(Long.parseLong(dados.get("infEvento.tpEvento")));
		cancellationLetter
				.setIssuanceDate(LocalDate.parse(dados.get("infEvento.dhEvento").toString().substring(0, 10)));
		cancellationLetter.setCreationDate(LocalDate.now());

		return cancellationLetter;
	}

	private Danfe findDanfe(String keyNFE) {
		Danfe danfe = null;
		Optional<Danfe> opt = danfeRepository.findByKeyNFE(keyNFE);
		if (!opt.isEmpty()) {
			danfe = opt.get();
		}
		return danfe;
	}

	private Danfe getDanfe(HashMap<String, String> dados, Company company, Provider provider) {
		Danfe danfe = new Danfe();

		Optional<Danfe> opt = danfeRepository.findByKeyNFE(dados.get("infProt.chNFe").toString());
		if (opt.isEmpty()) {
			danfe.setCompany(company);
			danfe.setProvider(provider);
			danfe.setNumberNFE(Long.parseLong(dados.get("ide.nNF")));
			danfe.setKeyNFE(dados.get("infProt.chNFe").toString());
			danfe.setAmount(new BigDecimal(dados.get("ICMSTot.vNF").toString()));
			danfe.setIssuanceDate(LocalDate.parse(dados.get("ide.dhEmi").toString().substring(0, 10)));
			danfe.setCreationDate(LocalDate.now());
			return danfe;
		}
		danfe = null;
		return danfe;
	}

	private Provider getProvider(HashMap<String, String> dados) {
		String cnpj;
		if (dados.get("emit.CNPJ") != null) {
			System.out.println("Existe a teg CNPJ");
			cnpj = dados.get("emit.CNPJ").toString();
		} else {
			System.out.println("Bloco else teg CNPJ nao existe ");
			cnpj = dados.get("emit.CPF").toString();
		}

		Provider provider = new Provider();
		Optional<Provider> opt = providerRepository.findByCnpj(cnpj);
		if (!opt.isEmpty()) {
			provider = opt.get();
		} else {

			provider.setName(dados.get("emit.xNome").toString().replaceAll("/", "").replaceAll("&", ""));
			provider.setCity(dados.get("emit.xMun").toString());
			provider.setState(dados.get("emit.UF").toString());
			provider.setCnpj(cnpj);
			provider = providerRepository.save(provider);

		}
		return provider;
	}

	private Company getCompany(HashMap dados) {
		Company company = new Company();
		System.out.println(dados.get("dest.CNPJ").toString());
		Optional<Company> opt = companyRepository.findByCnpj(dados.get("dest.CNPJ").toString());
		System.out.println("Foi localizado a empresa pelo CNPJ? " + !opt.isEmpty());
		if (!opt.isEmpty()) {
			System.out.println("Empresa encontrada");
			company = opt.get();
		} else {
			System.out.println("Empresa não encontrada");
			company.setFilePath(AppWebConfiguration.getFilerootpath() + AppWebConfiguration.getFilepathother());
		}

		return company;
	}

}
