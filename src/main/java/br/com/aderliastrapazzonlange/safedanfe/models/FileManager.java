package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class FileManager {

	private File[] paths;

	public void deleteXml(File file) {
		System.out.println("Deletando o arquivo:" + file);
		file.delete();
	}

	public void saveXml(String newPath, File file) {
		InputStream fileIn = null;
		OutputStream fileFor = null;

		try {
			fileIn = new FileInputStream(file);
			fileFor = new FileOutputStream(newPath);
			byte[] buf = new byte[1024];
			int len;
			while ((len = fileIn.read(buf)) > 0) {
				fileFor.write(buf, 0, len);
			}
			System.out.println("Salvando o arquivo:" + file + " no diretorio: " + newPath);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fileFor != null) {
				try {
					fileFor.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void readDirector(String path) {
		File rootPath = new File(path);
		paths = rootPath.listFiles();
	}

	public boolean xmlCheck(File file) {
		boolean result = false;
		String name = file.getName().substring(file.getName().lastIndexOf(".") + 1);
		name = name.toLowerCase();

		if (name.equals("xml")) {
			System.out.println("entrou no if da igualdade xml");
			fileProcess(file);

			result = true;
		}

		return result;
	}

	private void fileProcess(File file) {

		BufferedReader br;
		Boolean modified = false;
		String textUpdating = "";
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuilder linha = new StringBuilder();

			while (br.ready()) {

				linha.append(br.readLine());
				if (br.ready()) {
					linha.append("\n");
				}
			}
			if (linha.toString().indexOf("&") != -1 && linha.toString().indexOf("&amp;") == -1) {
				textUpdating = linha.toString().replaceAll("&", "&amp;");
				modified = true;
			} else {
				System.out.println("arquivo não tem &");
			}
			br.close();
			
			if(modified) {
				updatingFile(file,textUpdating);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updatingFile(File file, String textUpdating) {
		
		try {
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.printf("%s",textUpdating);
			printWriter.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
