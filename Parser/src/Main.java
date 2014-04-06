import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


	public static final int LAST_NAME = 0, FIRST_NAME = 1, STREET_ADDRESS = 4, CITY = 5, STATE = 6, ZIP = 7, PHONE = 8;
	public static void main(String[] args) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("file"), false);
		pw.close();
		List<String[]> things = new ArrayList<String[]>();

		Scanner input = new Scanner(new FileReader("church.csv"));
		while(input.hasNextLine())
		{
			String[] cols = input.nextLine().replace("\"", "").replaceFirst(" ", "").toLowerCase().split(",");
			cols[LAST_NAME] = capFirst(cols[LAST_NAME]);
			cols[FIRST_NAME] = capFirst(cols[FIRST_NAME]);
			cols[PHONE] = cols[PHONE].trim();
			cols[STATE] = cols[STATE].toUpperCase();

			String finalString = "";
			if(!cols[STREET_ADDRESS].isEmpty()){
				String[] addArray = cols[STREET_ADDRESS].split(" ");
				for(int i = 0; i < addArray.length; i++){
					if(i != 0){
						finalString += " ";
					}
					String temp = addArray[i];
					if(temp.length() > 0){
						if(Character.isAlphabetic(addArray[i].charAt(0))){
							addArray[i] = capFirst(addArray[i]);
						}
						finalString += addArray[i];
					}
				}
				cols[STREET_ADDRESS] = finalString;
			}
			sqlBuild(cols);
			things.add(cols);
		}
		input.close();
	}

	public static String sqlBuild(String[] col) throws IOException{
		PrintWriter pw = new PrintWriter(new FileWriter("file", true));
		//pw.println("BEGIN");
		pw.println("\tINSERT INTO members(mID, fname, lname)");
		pw.println("\tVALUES(NULL,'" + col[FIRST_NAME] + "', '" + col[LAST_NAME] + "');");
		pw.println();
		pw.println("\tINSERT INTO info(mID, phone)");
		pw.println("\tVALUES(LAST_INSERT_ID(), '" + col[PHONE] + "');");
		pw.println();
		pw.println("\tINSERT INTO member_address(mID, straddress, state, zip)");
		pw.println("\tVALUES(LAST_INSERT_ID(), '" + col[STREET_ADDRESS] + "','" + col[STATE] + "','" + col[ZIP] + "');");
		//pw.println("COMMIT;");
		pw.println("# -------------------------------------------------------------------------------------------");
		pw.close();
		System.out.println(col[1]);
		return null;
	}

	public static String capFirst(String s){
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
