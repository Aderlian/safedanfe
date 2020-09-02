package br.com.aderliastrapazzonlange.safedanfe.service;

import java.io.File;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import br.com.aderliastrapazzonlange.safedanfe.conf.AppWebConfiguration;
import br.com.aderliastrapazzonlange.safedanfe.models.Company;
import br.com.aderliastrapazzonlange.safedanfe.models.Danfe;
import br.com.aderliastrapazzonlange.safedanfe.models.Provider;
import br.com.aderliastrapazzonlange.safedanfe.repository.CompanyRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.DanfeRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.ProviderRepository;

@Service
public class ManagerXmlService {

	private ProviderRepository providerRepository;
	private CompanyRepository companyRepository;
	private DanfeRepository danfeRepository;

	public ManagerXmlService(ProviderRepository providerRepository, CompanyRepository companyRepository,
			DanfeRepository danfeRepository) {
		this.providerRepository = providerRepository;
		this.companyRepository = companyRepository;
		this.danfeRepository = danfeRepository;
	}

	public void start() {
		while (true) {

			System.out.println("Interando no inicio Start... ");
			File rootPath = new File(AppWebConfiguration.getFilerootpath());
			File[] paths = rootPath.listFiles();
			System.out.println("antes do for " + paths.length);
			if (paths.length == 0) {
				System.out.println("Não exixtem mais arquivos para serem processados...");
				break;
			}
			for (int i = 0; i < paths.length; i++) {

				if (i > 50) {
					break;
				}
				System.out.println("Diretorio do arquivo: " + paths[i]);

				File path = paths[i];
				XmlServer xmlServer = new XmlServer();
				try {
					Document document = xmlServer.openXml(path);

					Provider provider = xmlServer.providerXml(document, providerRepository);
					System.out.println(provider.getName());
					Company company = xmlServer.companyXml(document, companyRepository);
					if (company.getId() == 0) {
						xmlServer.saveXml(AppWebConfiguration.getFilepathother(), path);
						xmlServer.deleteXml(path);
						System.out.println("Não existe empresa cadastrada!!! ");
						return;
					}
					Danfe danfe = xmlServer.danfeXml(document, provider, company);
					Optional<Danfe> opt = danfeRepository.findByKeyNFE(danfe.getKeyNFE());
					if (opt.isEmpty()) {
						danfeRepository.save(danfe);
						String companyPath = danfe.getCompany().getFilePath() + danfe.getProvider().getName() + "-"
								+ danfe.getNumberNFE() + ".xml";
						String bkpPath = AppWebConfiguration.getFilepathbkp() + danfe.getKeyNFE() + ".xml";
						File filePath = path;
						xmlServer.saveXml(companyPath, filePath);
						xmlServer.saveXml(bkpPath, filePath);
						xmlServer.deleteXml(path);
						System.out.println("Cadastrado com sucesso...");

					} else {
						xmlServer.deleteXml(path);
						System.out.println("já existe no banco de dados...");

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Catch 4");
					try {
						xmlServer.saveXml(AppWebConfiguration.getFilepathother() + path.getName(), path);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					xmlServer.deleteXml(path);
					e.printStackTrace();
				}

			}
		}

	}

	public static void stop() {

	}

	public static void restart() {

	}

}
