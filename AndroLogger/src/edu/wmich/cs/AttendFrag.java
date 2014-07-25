package edu.wmich.cs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AttendFrag extends Fragment {

	EditText firstName;
	EditText lastName;
	Button finish;
	Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = inflater.getContext();
		RelativeLayout layout = (RelativeLayout) inflater.inflate(
				R.layout.attend_frag, container, false);

		firstName = (EditText) layout.findViewById(R.id.first_name_input);
		lastName = (EditText) layout.findViewById(R.id.last_name_input);

		finish = (Button) layout.findViewById(R.id.finish_button);

		finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate = df.format(c.getTime());
				String fname = firstName.getText().toString();
				String lname = lastName.getText().toString();
				UpdateUser task = new UpdateUser();
				task.execute(fname, lname, formattedDate);

			}
		});

		return layout;
	}

	private class UpdateUser extends AsyncTask<String, Void, Void> {
		String url = "jdbc:mysql://10.0.2.2:3306/";
		String dbName = "CLogger";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "pastor";
		String password = "loggingrox";

		@Override
		protected Void doInBackground(String... urls) {
			String fname = urls[0];
			String lname = urls[1];
			String date = urls[2];

			try {
				Class.forName(driver);
				Connection connection = DriverManager.getConnection(url
						+ dbName, userName, password);
				// System.out.println("Database connection success");
				Statement st;
				st = connection.createStatement();

				String tempDate = date;
				String executeString = "INSERT INTO attendance (mID, service_date, cID) VALUES ((SELECT mID FROM members WHERE fname = '"
						+ fname
						+ "' and lname = '"
						+ lname
						+ "'), '"
						+ tempDate + "', '0')";
				st.execute(executeString);
				
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			Toast.makeText(context, "User updated", Toast.LENGTH_LONG).show();
		}

	}

}