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
		// �������ݿ������
		mc = new MongoClient();
		// �������ݿ�
		db = mc.getDB(dbName);
	}
	public static void main(String[] args) {
		// �������ݿ��������test���ݿ�
		MongoConnect1 mg = new MongoConnect1("test");
//		// ����һ������Ϊjavadb�ļ���
//		mg.createCollection("javadb");
//		
//		// Ϊ����javadb���һ���ĵ�
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
		
//		// Ϊ����javadb��������ĵ�
//		List<DBObject> objs1 = new ArrayList<DBObject>();
//		DBObject obj2 = new BasicDBObject("name", "zhangsan");
//		DBObject obj3 = new BasicDBObject("name", "lisi");
//		objs1.add(obj2);
//		objs1.add(obj3);
//		mg.insertBatch(objs1, "javadb");
		
//		// �Ӽ���javadb�а�idɾ���ĵ�
//		System.out.println(mg.deleteById("5cb18f08497e2124d08b2e97", "javadb"));
//		
		
//		// �Ӽ���javadb�и��ݶ������ɾ���ĵ�
//		DBObject obj4 = new BasicDBObject();
//		obj4.put("name", "yilei");
//		obj4.put("email", "2689119170@qq.com");
//		System.out.println(mg.deleteByCons(obj4, "javadb"));
		
//		// ���¼���javadb���ĵ�
//		DBObject find = new BasicDBObject();
//		find.put("name", "yilei");
//		DBObject upda = new BasicDBObject();
//		upda.put("$set", new BasicDBObject("email","wenbing_jia@sina.com"));
//		System.out.println(mg.update(find, upda, false, false, "javadb"));
//	
		
//		// ��ѯpersons�����ж�Ӧ�ĵ���ָ���ֶ�
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
		
		// ��ҳ��ѯ�������е��ĵ�
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
	
	// ���ݿ��д�������
	private void createCollection(String collName) {
		DBObject obj = new BasicDBObject();
		db.createCollection(collName, obj);
	}
	
	// Ϊ��Ӧ�ļ������һ���ĵ�
	private Integer insert(DBObject obj, String collName) {
		Integer res = 0;
		DBCollection dbc = db.getCollection(collName);
		res = dbc.insert(obj).getN();
		return res;
	}
	
	// Ϊ��Ӧ�ļ�����������ĵ�
	private void insertBatch(List<DBObject> objs, String collName) {
		DBCollection dbc = db.getCollection(collName);
		dbc.insert(objs);
	}
	
	// ����id����ɾ���ĵ�
	private Integer deleteById(String id, String collName) {
		Integer res = 0;
		DBCollection dbc = db.getCollection(collName);
		DBObject obj = new BasicDBObject("_id", new ObjectId(id));
		res = dbc.remove(obj).getN();
		return res;
	}
	
	// ���ݶ������ɾ���ĵ�
	private Integer deleteByCons(DBObject obj, String collName) {
		Integer res = 0;
		DBCollection dbc = db.getCollection(collName);
		res = dbc.remove(obj).getN();
		return res;
	}
	
	// ���¶�Ӧ�����е��ĵ�
	private Integer update(DBObject find, DBObject upda, boolean upsert, boolean multi, String collName) {
		Integer res = 0;
		DBCollection dbc = db.getCollection(collName);
		res = dbc.update(find, upda, upsert, multi).getN();
		return res;
	}
	
	// ��������������ѯ��Ӧ�ĵ��е�ָ���ֶ�
	private DBCursor find(DBObject ref, DBObject keys, String collName) {
		DBCursor dbcur = null;
		DBCollection dbc = db.getCollection(collName);
		dbcur = dbc.find(ref, keys);
		return dbcur;
	}
	
	// ��ҳ��ѯ�����е��ĵ�
	private DBCursor findByPage(DBObject ref, DBObject keys, Integer lim, Integer ski, String collName) {
		DBCursor dbcur = null;
		DBCollection dbc = db.getCollection(collName);
		dbcur = dbc.find(ref, keys).limit(lim).skip(ski);
		return dbcur;
	}
}
