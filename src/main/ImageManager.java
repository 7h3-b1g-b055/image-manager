package main;

import java.io.File;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class ImageManager {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the path of a file or directory");
		String path = in.next();
		File f = new File(path);
		if (f.exists()) {
			if (f.isFile()) {
				System.out.println("File detected. Renaming.");
				rename(f);
			} else if (f.isDirectory()) {
				System.out.println("Directory detected. Renaming all files.");
				massEdit(f);
			}
			
		} else {
			System.out.println("No such file or directory");
		}
	}
	
	public static String generateName(File in) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yy HH:mm:ss");
		return sdf.format(in.lastModified());
	}
	
	
	public static void rename(File in) {
		String newName = generateName(in);
		//Rename file
		
	}
	
	public static void massEdit(File in) {
		File[] folder = in.listFiles();
		for (File file : folder) {
			rename(file);
		}
	}

}
