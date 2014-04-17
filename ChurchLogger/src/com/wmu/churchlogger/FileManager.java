package com.wmu.churchlogger;
 
 import java.io.BufferedReader;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.IOException;
 
 public class FileManager {
 
 	public String readFileLineByLine(String fileName){
 		BufferedReader br = null;
 		try {
 			br = new BufferedReader(new FileReader("kjvdat.txt"));
 		} catch (FileNotFoundException e1) {
 			e1.printStackTrace();
 		}
 		String line = "";
 		String l = "";
 		try {
 			while ((l = br.readLine()) != null) {
 				line += br.readLine() + "\n";
 			}
		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		try {
 			br.close();
		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		return line;
 	}
 
 }