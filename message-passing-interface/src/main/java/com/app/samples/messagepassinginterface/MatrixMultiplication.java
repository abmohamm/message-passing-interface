package com.app.samples.messagepassinginterface;

import com.app.samples.messagepassinginterface.filereaderutil.FileReaderLogic;
import com.app.samples.messagepassinginterface.model.MatrixPreparation;
import static com.app.samples.messagepassinginterface.model.MatrixPreparation.*;


public class MatrixMultiplication {

	//Read the matrix and vector from two different files
	private static MatrixPreparation matrixPreparation = null;
	private static MatrixPreparation vectorPreparation = null;

	public static void main(String args[]) {
		matrixMultiplicationResult();

	}

	public static void matrixMultiplicationResult() {

		int matrixRowLength = 0;
		int matrixColumnLength = 0;		
		//read matrix file
		matrixPreparation = FileReaderLogic.readFileData("matrix.txt");
		if(matrixPreparation != null) {
			matrixRowLength = matrixPreparation.getMatrixDimension().get(ROW_LENGTH+"_MATRIX");
			matrixColumnLength = matrixPreparation.getMatrixDimension().get(COLUMN_LENGTH+"_MATRIX");			
		}

		int vectorRowLength = 0;
		int vectorColumnLength = 0;
		//read vector file	
		vectorPreparation = FileReaderLogic.readFileData("vector.txt");
		if(vectorPreparation != null) {
			vectorRowLength = vectorPreparation.getMatrixDimension().get(ROW_LENGTH+"_VECTOR");
			vectorColumnLength = vectorPreparation.getMatrixDimension().get(COLUMN_LENGTH+"_VECTOR");
		}

		double matrix[][] = null;
		double vector[][] = null;
		double product[][] = null;

		if(matrixColumnLength == vectorRowLength) {
			matrix = FileReaderLogic.prepareMatrix(matrixPreparation,"_MATRIX");
			vector = FileReaderLogic.prepareMatrix(vectorPreparation,"_VECTOR");
			product = FileReaderLogic.matrixMultplicationResult(matrix, vector);
		}
		int productRowLength = product.length;
		int productColumnLength = product[0].length;
		System.out.println("Matrix Product : ");

		for(int i = 0;i<productRowLength;i++) {
			for(int j = 0;j<productColumnLength;j++) {
				System.out.print(product[i][j]+" ");
			}
			System.out.println();
		}
		
		StringBuffer stringBuffer = FileReaderLogic.prepareProductMatrix(product);
		String data = stringBuffer.toString();
		FileReaderLogic.writeFileData("matrix.txt", data);
		
	}
}
