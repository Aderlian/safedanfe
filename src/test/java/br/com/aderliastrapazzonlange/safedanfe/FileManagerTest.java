package br.com.aderliastrapazzonlange.safedanfe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.aderliastrapazzonlange.safedanfe.models.FileManager;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileManagerTest {
	String path = "/home/aderlian/Downloads/XML/teste.xml";
	File file = FileManager.openFile(path);
	File file2;
	
	@Test
	public void openFileTest() {
		assertEquals(true, file.exists());
	}
	@Test
	public void saveFileTest() {
		String newWay = "/home/aderlian/Downloads/XML/teste2.xml";
		FileManager.saveFile(file, newWay);
		File file2 = FileManager.openFile(newWay);
		assertEquals(true, file.exists());
		assertEquals(true, file2.exists());
	}
	@Test
	public void deleteFileTest() {
		file2 = FileManager.openFile("/home/aderlian/Downloads/XML/teste2.xml");
		assertEquals(true, file2.exists());
		FileManager.deleteFile(file2);
		assertEquals(false, file2.exists());
	}
	@Test
	public void readDirectorTest() {
		File fileA = FileManager.openFile("/home/aderlian/Downloads/XML/");
		List listA = FileManager.readDirector(fileA);
		File fileB = FileManager.openFile("/home/aderlian/Downloads/XML/error/");
		List listB = FileManager.readDirector(fileB);
		File fileC = null;
		List listC = FileManager.readDirector(fileC);
		
		assertEquals(true, listA.size() > 0);
		assertEquals(null, listB);
		assertEquals(null, listC);
		
	}
}
