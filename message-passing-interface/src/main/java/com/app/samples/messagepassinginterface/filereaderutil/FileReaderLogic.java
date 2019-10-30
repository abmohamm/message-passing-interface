package com.app.samples.messagepassinginterface.filereaderutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.app.samples.messagepassinginterface.MatrixMultiplication;
import com.app.samples.messagepassinginterface.model.MatrixPreparation;
import static com.app.samples.messagepassinginterface.model.MatrixPreparation.*;

public class FileReaderLogic {	
	static ClassLoader classLoader = MatrixMultiplication.class.getClassLoader();

	public static MatrixPreparation readFileData(String fileName) {
		MatrixPreparation matrixPreparation = new MatrixPreparation();
		boolean loadFromClassPath = true;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		InputStreamReader inputStreamReader = null;
		File file = null;

		try {			
			if(loadFromClassPath){
				inputStream = classLoader.getResourceAsStream(fileName);
				inputStreamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(inputStreamReader);
			}
			else {
				file = new File(fileName);
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
			}
			matrixPreparation.setBufferedReader(bufferedReader);
			matrixPreparation = prepareMatrixDimension(bufferedReader,fileName);
		}
		catch(FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				} 
			}
			catch (IOException e) {
				e.printStackTrace();
			}		
		}
		return matrixPreparation;		
	}

	public static MatrixPreparation prepareMatrixDimension(BufferedReader bufferedReader,String fileName){
		if(fileName.equalsIgnoreCase("matrix.txt")) {
			fileName = "_MATRIX";
		}
		else if(fileName.equalsIgnoreCase("vector.txt")) {
			fileName = "_VECTOR";
		}
		MatrixPreparation matrixPreparation = new MatrixPreparation();
		Map<String,Integer> matrixDimension = new HashMap<String, Integer>();
		StringTokenizer stringTokenizer = null;
		List<String> rows = new ArrayList<String>(); 
		int rowLength = 0;
		int columnLength = 0;
		String line = null;
		try {
			if(bufferedReader != null) {
				line = bufferedReader.readLine();
				rows.add(line);				
				stringTokenizer = new StringTokenizer(line," ");
				System.out.println("Elements : ");
				while(stringTokenizer.hasMoreElements()) {
					System.out.println(stringTokenizer.nextToken());
					columnLength++;					
				}
				matrixDimension.put(COLUMN_LENGTH+fileName,columnLength);
				while(line != null) {
					line = bufferedReader.readLine();
					if(line != null) {
						rows.add(line);
					}
					rowLength++;
				}
				matrixDimension.put(ROW_LENGTH+fileName, rowLength);
				matrixPreparation.setMatrixDimension(matrixDimension);
				matrixPreparation.setRows(rows);
				
			} 			
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
		return matrixPreparation;		
	}
	
	public static double[][] prepareMatrix(MatrixPreparation matrixPreparation,String fileName){
		
		int rowLength = 0;
		int columnLength = 0;	
		List<String> rows = null;
		String line = null;
		String[] token = null;
		double d = 0.0;
		if(matrixPreparation != null && matrixPreparation.getMatrixDimension() != null) {
			rowLength = matrixPreparation.getMatrixDimension().get(ROW_LENGTH+fileName);
			columnLength = matrixPreparation.getMatrixDimension().get(COLUMN_LENGTH+fileName);	
			rows = matrixPreparation.getRows();
		}
		double[][] matrixArr = new double[rowLength][columnLength];		
		for(int row = 0;row<rowLength;row++) {
			line = rows.get(row);
			token = line.split("     ");
			for(int column = 0;column<columnLength;column++) {
				d = Double.valueOf(token[column]);
				matrixArr[row][column] = d;
			}
		}		
		return matrixArr;
	}
	
	public static double[][] matrixMultplicationResult(double[][] matrix,double[][] vector) {
		
		//matrix dimension
		int matrixRowLength = matrix.length;
		int matrixColumnLength = matrix[0].length;
		
		//vector dimenstion
		int vectorRowLength = vector.length;
		int vectorColumnLength = vector[0].length;
		
		//for a matrix multiplication columns in first matrix should be equal to rows in second matrix and the resultant dimension is (rows in first/columns in second)
		double[][] result = new double[matrixRowLength][vectorColumnLength];
		
		for(int i = 0;i<matrixRowLength;i++) {			
			for(int j = 0;j<vectorColumnLength;j++) {				
				for(int k = 0;k<matrixColumnLength;k++) {
					result[i][j] =  result[i][j]+matrix[i][k]*vector[k][j];
				}				
			}
		}
		return result;
	}
	
	public static StringBuffer prepareProductMatrix(double[][] product) {
		StringBuffer stringBuffer = new StringBuffer();
		
		int rowLength = product.length;
		int columnLength = product[0].length;
		stringBuffer.append("Product Result : "+"\n"+"\n");
		for(int row=0;row<rowLength;row++) {
			for(int column=0;column<columnLength;column++) {
				stringBuffer.append(product[row][column]+"     ");
			}
			stringBuffer.append("\n");
		}
		
		return stringBuffer;
	}
	
	public static void writeFileData(String fileName,String writeData) {
		FileOutputStream fileOutputStream = null;
		byte[] strBytes = null;
		
		try {
			strBytes = writeData.getBytes();
			fileOutputStream = new FileOutputStream(OUTPUT_FILE_LOCATION);
			fileOutputStream.write(strBytes);
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			try {
				if(fileOutputStream != null) {
					fileOutputStream.close();
				}
			}
			catch(IOException ioException) {
				ioException.printStackTrace();
			}
		}
		
		
	}	
}
