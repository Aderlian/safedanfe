package br.com.aderliastrapazzonlange.safedanfe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.aderliastrapazzonlange.safedanfe.models.FileManager;

@RunWith(SpringRunner.class)
public class FileManagerTest {

	private FileManager fileManager = new FileManager();
	private File dir;

	public FileManagerTest() {

	}

	@Before
	public void prepare() {
		dir = new File("/home/aderlian/Downloads/test-data");
		dir.mkdirs();
	}

	@After
	public void clean() {

		if (dir.listFiles() != null) {
			for (File f : dir.listFiles()) {
				f.delete();
			}
		}

		dir.delete();
	}

	@Test
	public void deleteExistingFile() throws Exception {

		// scenario
		new File(dir, "teste.xml").createNewFile();
		File[] files = dir.listFiles();

		// action
		fileManager.deleteXml(files[0]);
		files = dir.listFiles();

		// validation
		assertEquals(0, files.length);
	}

	@Test
	public void saveFile() throws Exception {

		// scenario
		new File(dir, "teste.xml").createNewFile();
		File[] files = dir.listFiles();

		// action
		fileManager.saveXml("/home/aderlian/Downloads/test-data/teste1.xml", files[0]);
		files = dir.listFiles();

		// validation
		assertEquals(2, dir.listFiles().length);
		assertEquals("teste.xml", files[0].getName());
		assertEquals("teste1.xml", files[1].getName());
	}

	@Test
	public void readDirector() throws Exception {
		// scenario
		new File(dir, "teste.xml").createNewFile();
		new File(dir, "teste1.xml").createNewFile();
		new File(dir, "teste2.xml").createNewFile();

		// action
		fileManager.readDirector("/home/aderlian/Downloads/test-data");
		

		// validation
		assertEquals(3, fileManager.getPaths().length);
	}
}
