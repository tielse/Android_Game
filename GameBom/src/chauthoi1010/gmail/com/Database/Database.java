package chauthoi1010.gmail.com.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
	public static final String RowID = "id";
	public static final String Name = "name";
	public static final String Diem = "diem";

	private static final String Database_name = "Database";
	private static final String Database_table = "Table";
	private static final int Version = 1;
	// Code SQLife
	private static final String Create_Table = "CREATE TABLE IF NOT EXIT"
			+ Database_table + " (" + RowID
			+ "INTEGER PRIMARY KEY AUTOINCREMENT, " + Name + " TEXT NOT NULL, "
			+ Diem + " INTEGER) ";
	private final Context c;
	private SQLiteDatabase ourDb;
	private DbHelper helper;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, Database_name, null, Version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(Create_Table);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISIT" + Database_table);
			onCreate(db);
		}
	}

	public Database(Context c) {
		this.c = c;
		helper = new DbHelper(this.c);
	}

	public Database open() {
		ourDb = helper.getWritableDatabase();
		return this;
	}

	public void close() {
		helper.close();
	}

	// Insert diem vao databse
	public long isertRow(String name, int diem) {
		ContentValues cv = new ContentValues();
		cv.put(Name, name);
		cv.put(Diem, diem);
		return ourDb.insert(Create_Table, null, cv);
	}

	// Lay tat ca cac cot
	public Cursor getAllRow() {
		return ourDb.query(Database_table, new String[] { RowID, Name, Diem },
				null, null, null, null, Diem + "DESC");
	}

	// Xoa diem tat ca trong cot
	public long deleteAllRow() {
		return ourDb.delete(Database_table, null, null);
	}

	// Update diem nguoi choi
	public long updatePlayer(String id, String name, int diem) {
		ContentValues cv = new ContentValues();
		cv.put(Name, name);
		cv.put(Diem, diem);
		return ourDb.update(Create_Table, cv, RowID + " = " + id, null);
	}

	// Kiem tra xem voi so diem truyen vao co duoc phep luu vao csdl khong
	public boolean kt_luu(int diem) {
		try {
			this.open();
			Cursor c = this.getAllRow();
			if (c.moveToPosition(5)) {
				int d = c.getInt(c.getColumnIndex(Database.Diem));
				if (d < diem) {
					c.close();
					this.close();
					return true;
				}
			}
			// Duoc phep luu
			c.close();
			this.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
