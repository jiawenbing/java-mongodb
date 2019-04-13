package test;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
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
//		// 创建一个名字为javadb的集合
//		mg.createCollection("javadb");
//		
//		// 为集合javadb添加一条文档
//		DBObject obj1 = new BasicDBObject();
//		obj1.put("name", "yilei");
//		obj1.put("email", "2689119170@qq.com");
//		List<String> books = new ArrayList<String>();
//		books.add("C");
//		books.add("Java");
//		books.add("Python");
//		books.add("JavaScript");
//		obj1.put("books", books);
//		mg.insert(obj1, "javadb");
		
//		// 为集合javadb批量添加文档
//		List<DBObject> objs1 = new ArrayList<DBObject>();
//		DBObject obj2 = new BasicDBObject("name", "zhangsan");
//		DBObject obj3 = new BasicDBObject("name", "lisi");
//		objs1.add(obj2);
//		objs1.add(obj3);
//		mg.insertBatch(objs1, "javadb");
		
//		// 从集合javadb中按id删除文档
//		System.out.println(mg.deleteById("5cb18f08497e2124d08b2e97", "javadb"));
//		
		
//		// 从集合javadb中根据多个条件删除文档
//		DBObject obj4 = new BasicDBObject();
//		obj4.put("name", "yilei");
//		obj4.put("email", "2689119170@qq.com");
//		System.out.println(mg.deleteByCons(obj4, "javadb"));
		
//		// 更新集合javadb的文档
//		DBObject find = new BasicDBObject();
//		find.put("name", "yilei");
//		DBObject upda = new BasicDBObject();
//		upda.put("$set", new BasicDBObject("email","wenbing_jia@sina.com"));
//		System.out.println(mg.update(find, upda, false, false, "javadb"));
//	
		
//		// 查询persons集合中对应文档的指定字段
//		DBObject ref = new BasicDBObject();
//		ref.put("age", new BasicDBObject("$gte",26));
//		ref.put("e", new BasicDBObject("$lte", 80));
//		DBObject keys = new BasicDBObject();
//		keys.put("_id", false);
//		keys.put("name", true);
//		keys.put("age", true);
//		DBCursor dbcur = mg.find(ref, keys, "persons");
//		while(dbcur.hasNext()) {
//			System.out.println(dbcur.next());
//		}
		
		// 分页查询出集合中的文档
		DBObject ref = null;
		DBObject keys = new BasicDBObject();
		keys.put("_id", false);
		keys.put("name", true);
		keys.put("age", true);
		DBCursor dbcur = mg.findByPage(ref, keys, 3, 10, "persons");
		while(dbcur.hasNext()) {
			System.out.println(dbcur.next());
		}
		
	}
	
	// 数据库中创建集合
	private void createCollection(String collName) {
		DBObject obj = new BasicDBObject();
		db.createCollection(collName, obj);
	}
	
	// 为相应的集合添加一个文档
	private Integer insert(DBObject obj, String collName) {
		Integer res = 0;
		DBCollection dbc = db.getCollection(collName);
		res = dbc.insert(obj).getN();
		return res;
	}
	
	// 为相应的集合批量添加文档
	private void insertBatch(List<DBObject> objs, String collName) {
		DBCollection dbc = db.getCollection(collName);
		dbc.insert(objs);
	}
	
	// 根据id条件删除文档
	private Integer deleteById(String id, String collName) {
		Integer res = 0;
		DBCollection dbc = db.getCollection(collName);
		DBObject obj = new BasicDBObject("_id", new ObjectId(id));
		res = dbc.remove(obj).getN();
		return res;
	}
	
	// 根据多个条件删除文档
	private Integer deleteByCons(DBObject obj, String collName) {
		Integer res = 0;
		DBCollection dbc = db.getCollection(collName);
		res = dbc.remove(obj).getN();
		return res;
	}
	
	// 更新对应集合中的文档
	private Integer update(DBObject find, DBObject upda, boolean upsert, boolean multi, String collName) {
		Integer res = 0;
		DBCollection dbc = db.getCollection(collName);
		res = dbc.update(find, upda, upsert, multi).getN();
		return res;
	}
	
	// 根据所给条件查询对应文档中的指定字段
	private DBCursor find(DBObject ref, DBObject keys, String collName) {
		DBCursor dbcur = null;
		DBCollection dbc = db.getCollection(collName);
		dbcur = dbc.find(ref, keys);
		return dbcur;
	}
	
	// 分页查询集合中的文档
	private DBCursor findByPage(DBObject ref, DBObject keys, Integer lim, Integer ski, String collName) {
		DBCursor dbcur = null;
		DBCollection dbc = db.getCollection(collName);
		dbcur = dbc.find(ref, keys).limit(lim).skip(ski);
		return dbcur;
	}
}
