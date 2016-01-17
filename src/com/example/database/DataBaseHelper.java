package com.example.database;

import com.example.model.Employee;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DataBaseHelper extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "emp";
	private final static int DATABASE_VERSION = 1;
	private Context context;

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		context = this.context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTable = "create table emp(empid number,empname Text,empsal real)";
		db.execSQL(createTable);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		String dropTable = "if table exists drop";
		onCreate(db);

	}

	public String insertData(Employee employee) {

		String res = "";

		SQLiteDatabase database = null;

		try {
			database = getWritableDatabase();
			String sql = "insert into emp values(" + employee.getEmpId() + ",'" + employee.getEmpName() + "','"
					+ employee.getEmpSal() + "')";
			database.execSQL(sql);
			return res = "success";

		} catch (Exception e) {
			// TODO: handle exception
		}

		return res = "fail";
	}

	public String deleteRecord(int empId) {

		String res = "";
		SQLiteDatabase database = null;
		try {
			database = getWritableDatabase();

			String sqldelete = "delete from emp where empid=" + empId + "";
			database.execSQL(sqldelete);

			return "Record deleted successfully" + empId;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "problem to delete records";
	}

	public String updateRecord(Employee employee) {

		String res = "";
		SQLiteDatabase database = null;

		try {
			String sql = "update emp set empname='" + employee.getEmpName() + "',empsal='" + employee.getEmpSal()
					+ "' where empid=" + employee.getEmpId() + "";
			database = getWritableDatabase();
			database.execSQL(sql);

			return "successfully updated";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Record not updated successfully";

	}

	public Cursor searchRecords(int empId) {

		String res = "";
		SQLiteDatabase database = null;
		Cursor cursor = null;
		try {

			database = getReadableDatabase();
			String sql = "select * from emp where empid=" + empId + "";
			return cursor = database.rawQuery(sql, null);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

}
