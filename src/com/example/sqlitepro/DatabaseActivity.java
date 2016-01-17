package com.example.sqlitepro;

import com.example.database.DataBaseHelper;
import com.example.model.Employee;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseActivity extends Activity implements OnClickListener {

	private EditText empNameEditText;
	private EditText empIdEditText;
	private EditText empSalEditText;
	private Button insertButton;
	private Button deleteButton;
	private Button updateButton;

	private Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		registerEvents();
	}

	private void registerEvents() {

		insertButton.setOnClickListener(this);
		deleteButton.setOnClickListener(this);
		updateButton.setOnClickListener(this);
		searchButton.setOnClickListener(this);

	}

	private void initViews() {

		searchButton = (Button) findViewById(R.id.searchButton);
		insertButton = (Button) findViewById(R.id.insertButton);
		updateButton = (Button) findViewById(R.id.updateButton);
		deleteButton = (Button) findViewById(R.id.deleteButton);
		empNameEditText = (EditText) findViewById(R.id.empnameEditText);
		empIdEditText = (EditText) findViewById(R.id.empidEditText);
		empSalEditText = (EditText) findViewById(R.id.empsalEditText);

	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.insertButton:

			insertDataToDataBase();

			break;
		case R.id.deleteButton:

			deleteDataFromDataBase();

			break;
		case R.id.updateButton:

			updateDataFromDataBase();

			break;
		case R.id.searchButton:

			searchDataFromDataBase();

			break;

		default:
			break;
		}

	}

	/**
	 * This method use to search the data from the database.
	 */

	private void searchDataFromDataBase() {

		String values = "Details:";
		int id = 0;
		String empname = null;
		double empsal = 0.0;

		DataBaseHelper baseHelper = new DataBaseHelper(getApplicationContext());
		int empid = Integer.parseInt(empIdEditText.getText().toString());
		Cursor cursor = baseHelper.searchRecords(empid);

		if (cursor.moveToFirst()) {

			do {

				id = cursor.getInt(0);
				empname = cursor.getString(1);
				empsal = cursor.getDouble(2);

				values = values + "--" + id + "--" + empname + "--" + empsal;

			} while (cursor.moveToNext());

		}
		empIdEditText.setText(String.valueOf(empid));
		empNameEditText.setText(String.valueOf(empname));
		empSalEditText.setText(String.valueOf(empsal));

	}

	/**
	 * This method use to search data from the database.
	 */
	private void updateDataFromDataBase() {

		Employee employee = new Employee();

		DataBaseHelper baseHelper = new DataBaseHelper(getApplicationContext());

		employee.setEmpId(Integer.parseInt(empIdEditText.getText().toString()));
		employee.setEmpName(empNameEditText.getText().toString());
		employee.setEmpSal(Double.parseDouble(empSalEditText.getText().toString()));

		String res = baseHelper.updateRecord(employee);
		Toast.makeText(this, res, 3000).show();

	}

	/**
	 * This method use to delete record from the database.
	 */
	private void deleteDataFromDataBase() {

		DataBaseHelper baseHelper = new DataBaseHelper(getApplicationContext());
		int empid = (Integer.parseInt(empIdEditText.getText().toString()));
		String res = baseHelper.deleteRecord(empid);
		Toast.makeText(this, res, 2000).show();
		;

	}

	/**
	 * This method use to insert data into database.
	 */
	private void insertDataToDataBase() {

		Employee employee = new Employee();

		DataBaseHelper baseHelper = new DataBaseHelper(getApplicationContext());

		employee.setEmpId(Integer.parseInt(empIdEditText.getText().toString()));
		employee.setEmpName(empNameEditText.getText().toString());
		employee.setEmpSal(Double.parseDouble(empSalEditText.getText().toString()));
		String res = baseHelper.insertData(employee);
		System.out.println(">>>>>>>>>>>Response:" + res);

	}
}
