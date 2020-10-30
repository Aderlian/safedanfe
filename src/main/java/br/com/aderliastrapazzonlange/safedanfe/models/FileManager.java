package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Essa classe estatica é responsavel por manipular os arquivos de dados.
 * This class static is responsible for manipulate file data.
 * 
 * @author Aderlian Strapazzon Lange
 *
 */

public class FileManager {
/**
 * Crie uma nova instância de arquivo usando o caminho
 * Create a new instance of file using path 
 * 
 * @param path caminho absoluto do arquivo, inclua seu nome e extensão, exemplo: "/home/file1.xml"
 * @param path path absolute of file, include your name and extension, example: "/home/file1.xml"
 * @return nova instancia do arquivo.
 * @return new instance of File.
 * @see java.io.File
 * 
 */
	public static File openFile(String path) {
		File file = new File(path);
		return file;
	}
/**
 * Grava o arquivo de memória em um novo caminho.
 * Writes memory file in new path.  
 * 
 * @param file objeto na memória representa um arquivo.
 * @param file object in memory represents a file.
 * @param newWay novo diretório para gravação, inclua seu nome e extensão, exemplo: "/home/file1.xml".
 * @param newWay new directory for write, include your name and extension, example: "/home/file1.xml".
 */
	public static void saveFile(File file, String newWay){
		try {
			InputStream fileIn = new FileInputStream(file);
			OutputStream fileFor = new FileOutputStream(newWay);
			byte[] buf = new byte[1024];
			int len;
			while ((len = fileIn.read(buf)) > 0) {
				fileFor.write(buf, 0, len);
			}
			fileIn.close();
			fileFor.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
/**
 * Remove o arquivo.
 * Remove file.
 * @param file objeto na memória representa um arquivo.
 * @param file object in memory represents a file.
 */
	public static void deleteFile(File file) {
		file.delete();		
	}
/**
 * ler o diretório e listar todos os arquivos existentes.
 * read directory and list all files existents.
 * @param dir diretório principal de arquivos.
 * @param dir directory main from files.
 * @return lista de caminho de arquivo.
 * @return list file path.
 */
	public static List<String> readDirector(File dir) {
		File[] files;
		if(dir == null) {
			return null;
		}
		files = dir.listFiles();
		List<String> listFiles = new ArrayList<String>();
		if(files == null || files.length == 0) {
			return null;
		}
		for(int i = 0; i < files.length; i++) {
			listFiles.add(files[i].getAbsolutePath());
		}
		return listFiles;
	}
}
