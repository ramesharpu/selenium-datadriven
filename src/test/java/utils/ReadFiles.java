package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadFiles {
	String currDir = System.getProperty("user.dir") + "\\";
	FileInputStream file;
	Properties prop;
	public Properties readPropertyFile(String fileName) {
		try {
			file = new FileInputStream(currDir + fileName);
			prop = new Properties();
			prop.load(file);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found in the location");
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				file.close();
			} catch (IOException e) {
				System.out.println("Unable to close the file - "+fileName);
				e.printStackTrace();
			}
		}
		return prop;
	}
	
	public static void main(String[] args) {
		ReadFiles obj = new ReadFiles();
		Properties result = obj.readPropertyFile("config.properties");
		System.out.println(result);
	}
}
