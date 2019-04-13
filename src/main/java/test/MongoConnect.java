package test;

import com.mongodb.*;

public class MongoConnect {

	public static void main(String[] args) {
		// 连接MongoDB服务器
		MongoClient mc = new MongoClient("localhost",27017);
		
		// 查询服务器上的所有数据库
		for(String name : mc.getDatabaseNames()) {
			System.out.println(name);
		}
		// 连接test数据库
		DB db = mc.getDB("test");
		
		// 查询test数据库下的所有集合
		for(String name : db.getCollectionNames()) {
			System.out.println(name);
		}
		// 连接persons集合
		DBCollection dbc = db.getCollection("persons");
		
		// 查询persons集合下的所有文档
		DBCursor dbcur = dbc.find();
		// 遍历输出文档
		while(dbcur.hasNext()){
			DBObject obj = dbcur.next();
			System.out.println(obj.get("name"));
		}
		// 查询persons集合下的name为“lisi”的文档
		DBObject obj1 = new BasicDBObject();
		obj1.put("name", "lisi");
		DBCursor dbcur1 = dbc.find(obj1);
		while(dbcur1.hasNext()){
			DBObject obj2 = dbcur1.next();
			System.out.println(obj2);
		}
		
		
	}

}
