package test;

import com.mongodb.*;

public class MongoConnect {

	public static void main(String[] args) {
		// ����MongoDB������
		MongoClient mc = new MongoClient("localhost",27017);
		
		// ��ѯ�������ϵ��������ݿ�
		for(String name : mc.getDatabaseNames()) {
			System.out.println(name);
		}
		// ����test���ݿ�
		DB db = mc.getDB("test");
		
		// ��ѯtest���ݿ��µ����м���
		for(String name : db.getCollectionNames()) {
			System.out.println(name);
		}
		// ����persons����
		DBCollection dbc = db.getCollection("persons");
		
		// ��ѯpersons�����µ������ĵ�
		DBCursor dbcur = dbc.find();
		// ��������ĵ�
		while(dbcur.hasNext()){
			DBObject obj = dbcur.next();
			System.out.println(obj.get("name"));
		}
		// ��ѯpersons�����µ�nameΪ��lisi�����ĵ�
		DBObject obj1 = new BasicDBObject();
		obj1.put("name", "lisi");
		DBCursor dbcur1 = dbc.find(obj1);
		while(dbcur1.hasNext()){
			DBObject obj2 = dbcur1.next();
			System.out.println(obj2);
		}
		
		
	}

}
