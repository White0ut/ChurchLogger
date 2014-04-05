import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


	public static final int LAST_NAME = 0, FIRST_NAME = 1, STREET_ADDRESS = 4, CITY = 5, STATE = 6, ZIP = 7, PHONE = 8;
	public static void main(String[] args) throws FileNotFoundException {

		List<String[]> things = new ArrayList<String[]>();

		Scanner input = new Scanner(new FileReader("church.csv"));
		while(input.hasNextLine()){
			String[] cols = input.nextLine().replace("\"", "").replaceFirst(" ", "").replace("  ", " ").toLowerCase().split(",");
			cols[LAST_NAME] = capFirst(cols[LAST_NAME]);
			cols[FIRST_NAME] = capFirst(cols[FIRST_NAME]);
			
			things.add(cols);
		}

		System.out.println("Phone numbers:");
		for(String[] s : things){
			System.out.print(s[PHONE] + ",");
		}
		System.out.println();
		System.out.println("First name");
		for(String[] s : things){
			System.out.print(s[FIRST_NAME] + ",");
		}
		System.out.println();
		System.out.println("Last name");
		for(String[] s : things){
			System.out.print(s[LAST_NAME] + ",");
		}
		System.out.println();
		System.out.println("Address");
		for(String[] s : things){
			System.out.print(s[STREET_ADDRESS] + ",");
		}
		input.close();


	}

	public static String capFirst(String s){
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
