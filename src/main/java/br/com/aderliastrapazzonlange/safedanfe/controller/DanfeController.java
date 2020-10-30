package br.com.aderliastrapazzonlange.safedanfe.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.aderliastrapazzonlange.safedanfe.conf.AppWebConfiguration;
import br.com.aderliastrapazzonlange.safedanfe.models.Danfe;
import br.com.aderliastrapazzonlange.safedanfe.models.FileManager;
import br.com.aderliastrapazzonlange.safedanfe.models.FileManagerOld;
import br.com.aderliastrapazzonlange.safedanfe.repository.DanfeRepository;

@RestController
@RequestMapping("/invoices")
@CrossOrigin("http://localhost:4200")
public class DanfeController {

	private final DanfeRepository repository;
	private final FileManagerOld file;
	
	@Autowired
	public DanfeController(DanfeRepository repository, FileManagerOld file) {
		this.repository = repository;
		this.file = file;
	}
	
	@GetMapping
	public List<Danfe> ListCompany() {
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public Danfe searchId(@PathVariable Long id) {
		return repository
		.findById(id)
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Danfe não encontrada."));
	}
	
	@GetMapping("/downloadDir/{id}")
	public void downloadDirId(@PathVariable Long id) throws ResponseStatusException{
		Danfe danfe = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Danfe não encontrada."));
		File xml = new File(AppWebConfiguration.getFilerootpath() + AppWebConfiguration.getFilepathbkp()
								+ danfe.getKeyNFE() + ".xml");
		String newPath = danfe.getCompany().getFilePath() + xml.getName();
		file.saveXml(newPath, xml);
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadId(@PathVariable Long id) throws IOException {
		Danfe danfe = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Danfe não encontrada."));
		
		File file = new File(AppWebConfiguration.getFilerootpath() + AppWebConfiguration.getFilepathbkp()
		+ danfe.getKeyNFE() + ".xml");
		
		byte[] bar = Files.readAllBytes(file.toPath());
				
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_XML)
				.header("Content-Disposition","attachment; filename=\"" + danfe.getKeyNFE() +".xml\"")
				.body(bar);
	}


}
