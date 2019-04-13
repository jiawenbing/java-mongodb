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
		// 连接数据库服务器
		mc = new MongoClient();
		// 连接数据库
		db = mc.getDB(dbName);
	}
	public static void main(String[] args) {
		// 连接数据库服务器的test数据库
		MongoConnect1 mg = new MongoConnect1("test");
		
	}
}
