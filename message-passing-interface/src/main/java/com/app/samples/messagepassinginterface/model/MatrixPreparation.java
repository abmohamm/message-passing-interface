package com.app.samples.messagepassinginterface.model;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

public class MatrixPreparation {
	
	private Map<String,Integer> matrixDimension;
	private List<String> rows;
	private BufferedReader bufferedReader;
	
	public static final String ROW_LENGTH = "ROWLENGTH";
	public static final String COLUMN_LENGTH = "COLUMNLENGTH";
	public static final String OUTPUT_FILE_LOCATION = "c:/temp/samplefile.txt";

	public MatrixPreparation() {
		super();
	}

	public Map<String, Integer> getMatrixDimension() {
		return matrixDimension;
	}

	public void setMatrixDimension(Map<String, Integer> matrixDimension) {
		this.matrixDimension = matrixDimension;
	}

	public List<String> getRows() {
		return rows;
	}

	public void setRows(List<String> rows) {
		this.rows = rows;
	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	

}
