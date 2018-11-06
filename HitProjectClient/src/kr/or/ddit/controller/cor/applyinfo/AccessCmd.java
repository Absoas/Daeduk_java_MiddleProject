package kr.or.ddit.controller.cor.applyinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import kr.or.ddit.controller.jmem.applyJob.coding.CodingController;


public class AccessCmd {
	String path = CodingController.setPath();

	public void execCommand(String fileName){
		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"javac "+fileName+".java");
		builder.redirectErrorStream(true);
		builder.directory(new File(path+"/sourceFolder"));
		try {
			builder.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public String compileCommand(String fileName) {
		ProcessBuilder builder2 = new ProcessBuilder("cmd.exe", "/c",
				"java "+fileName);
		builder2.redirectErrorStream(true);
		builder2.directory(new File(path+"/sourceFolder"));
		Process p = null;
		try {
			p = builder2.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String result = "";
		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				result += line + "\n";
			}
			bufReader.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			System.out.println(e);
		}
	
		return result;
	}
}
