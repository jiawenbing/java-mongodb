package test;

import com.mongodb.*;
/*
 * private     class
 * default     class package
 * protected   class package subclass
 * public      class package subclass everywhere
 */
public class MongoConnect1 {
	public static MongoClient mc = null;
	public static DB db = null;
	public MongoConnect1(String dbName) {
		// �������ݿ������
		mc = new MongoClient();
		// �������ݿ�
		db = mc.getDB(dbName);
	}
	public static void main(String[] args) {
		// �������ݿ��������test���ݿ�
		MongoConnect1 mg = new MongoConnect1("test");
		
	}
}
