package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.nio.file.Path;
public class ImageManager {

	/**
	 * Main method for running the program.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the path of a file or directory");
		String path = in.next();
		in.close();
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
	
	/**
	 * Generates a name for a given file.
	 * @param in file to base name on
	 * @return the generated name as a string
	 */
	public static String generateName(File in) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy HH~mm~ss");
		return sdf.format(in.lastModified()) + "." + getFileExtension(in);
	}
	
	public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
        	return fileName.substring(fileName.lastIndexOf(".")+1);
        }
        else return "";
    }
	
	/**
	 * Renames a file.
	 * @see https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html#move(java.nio.file.Path,%20java.nio.file.Path,%20java.nio.file.CopyOption...)
	 * @see https://stackoverflow.com/questions/1158777/rename-a-file-using-java
	 * @param in the file to rename
	 */
	public static void rename(File in) {
		String newName = generateName(in);
		Path originalPath = in.toPath();
		try {
			Files.move(originalPath, originalPath.resolveSibling(newName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Calls rename on a folder of files.
     * @param in the folder to rename
	 */
	public static void massEdit(File in) {
		File[] folder = in.listFiles();
		for (File file : folder) {
			rename(file);
		}
	}

}
