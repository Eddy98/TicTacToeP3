package course.oop.util;

import java.util.HashMap;


public class TwoDArray {
	
	String[][] arr;
	int rows;
	int cols;
	String defaultVal;
	
	public TwoDArray(int rows, int cols, String defaultVal){
		
		this.arr = new String[rows][cols];
		this.rows = rows;
		this.cols = cols;
		this.defaultVal = defaultVal;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				arr[i][j] = defaultVal;
			}
		}
	}
	
	public void initArray(String defaultVal) {
		/*TODO - (Re)Initialize the array by 
		 * setting each int to be the defaulVal 
		 */
		this.defaultVal = defaultVal;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				arr[i][j] = defaultVal;
			}
		}
	}
	
	public boolean insertMark(int row, int col, String val) {
		/*TODO - "Insert" based on the following conditions:
		 * 1. The location [row][col] is still set to the default value
		 * 		-return "Success! (val) was inserted.
		 * 
		 * 2. The location [row][col] is no longer the default value
		 * 		-return "Failure: (row), (col) is not empty.
		 * 
		 * 3. val is the default value; 
		 * 		-return "Failure: (val) is not allowed."
		 * 
		 * Note: Print the int value in place of (). 
		 * e.g., replace (val) with val.
		 */

		if (val.equalsIgnoreCase(defaultVal)) {
			return false;
		} else if (!arr[row][col].equalsIgnoreCase(defaultVal)) {
			return false;
		} else if(row < 0 || col < 0 || row > 2 || col > 2){
			return false;
		} else {
			arr[row][col] = val;
			return true;
		}
	}
	
	public String getMark(int row, int col) {
		/*TODO - Return the value at the specified row, col
		 * 
		 */
		return arr[row][col];
	}
	
	public String getArrayDisplay() {

		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(j!=2)
					str.append(arr[i][j] + "\t|\t");
				else
					str.append(arr[i][j]);
			}
			if(i!= 2)
				str.append("\n-----------------------------------\n");
		}
		str.append("\n");
		return str.toString();
	}
	
	public String getArrayDetails() {
		
		StringBuilder str = new StringBuilder();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		str.append("# " + rows + "\n");
		str.append("# " + cols + "\n");
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (!map.containsKey(arr[i][j])) {
					map.put(arr[i][j], 1);
				} else {
					map.put(arr[i][j], map.get(arr[i][j]) + 1);
				}
			}
		}
		for (String i : map.keySet()) {
			str.append("value:" + i + " \tcount:" + map.get(i) + "\n");
		}
		
		return str.toString();
	}
	
	
	
	

}
