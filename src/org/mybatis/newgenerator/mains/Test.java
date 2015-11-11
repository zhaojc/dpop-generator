package org.mybatis.newgenerator.mains;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		File file = new File("c:\\aa\\bb\\cc\\tt.txt");//创建多级目录文件  
		//mkdir()创建单级目录  
		//mkdirs()创建多级目录  
		file.getParentFile().mkdirs();  
		file.createNewFile();  
		BufferedWriter output;  
		output = new BufferedWriter(new FileWriter(file));  
		output.write("--test-122112test12112-");  
		output.close(); 
		
	}

}
