package com.ronald;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.ronald.entity.Address;
import com.ronald.entity.Person;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.jongo.Oid;

import java.util.*;

/**
 * 测试MongoDB Java 驱动Jongo的使用
 * @author ronald
 * @date 2016年3月28日下午9:05:11
 */
public class TestJongo {

    private static MongoClient mongo = null;
    private static Jongo jongo = null;

    static{
        mongo = new MongoClient("localhost", 27017);
        @SuppressWarnings("deprecation")
        DB db = mongo.getDB("runoob");
        jongo = new Jongo(db);
    }


    public static void main(String[] args) {
        try {

            TestJongo tj = new TestJongo();

//			MongoCollection coll = jongo.getCollection("person");
//			System.out.println("记录总数：" + coll.count());

//			coll.remove();
//			tj.add();
//			tj.query();
//			tj.optArray();
            tj.testDate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            mongo.close();
        }
    }

    /**
     * 新增和插入
     */
    public void add(){
        MongoCollection coll = jongo.getCollection("person");
        //新增
        Person p = new Person(0, "ronald", "男", "帅哥");
        WriteResult save = coll.save(p);
        System.out.println(save);
        //插入
        coll.insert(new Person(1, "liu", "男", "高大"));
        //插入一条记录包含name属性
        coll.insert("{name:'wen'}");
    }

    public void update(){
        MongoCollection coll = jongo.getCollection("person");
        Map<String, Object> map = coll.findOne("{name:'wen'}").as(HashMap.class);
        map.remove("_id");
        map.put("love", "pingpong pingpin");
        coll.update("{name:'wen'}").with(map);
        //修改
        coll.update(new ObjectId("56f934655435cc01aa5c1561")).with(new Person(1, "sy", "女", "美女"));
        coll.update("{name:'liu'}").with("{$set:{address:#}}", new Address("410008", "长沙"));
        //修改多个
        coll.update("{name:'ronald'}").multi().with("{$set:{name:'wen',rela:'聪明'}}");
    }

    public void query(){
        MongoCollection coll = jongo.getCollection("person");

        //查询多个
        MongoCursor<Person> persons = coll.find().as(Person.class);
        //排序
        persons = coll.find().sort("{name:1}").as(Person.class);
        //跳过记录
        persons = coll.find().skip(4).as(Person.class);
        //查询指定数量记录
        persons = coll.find().skip(2).limit(2).as(Person.class);
        //强制使用索引，注意索引必须存在
        coll.ensureIndex("{name:-1}");
        persons = coll.find().hint("{name:-1}").as(Person.class);
        Iterator<Person> iterator = persons.iterator();
        while (iterator.hasNext()) {
            Person person = (Person) iterator.next();
            System.out.println("Many:" + person);
        }
        //查询一个
        Person person = coll.findOne("{sex:'男'}").as(Person.class);
        //是否显示某个字段
        Person person2 = coll.findOne("{sex:'男'}").projection("{name:0}").as(Person.class);
        Person person3 = coll.findOne(Oid.withOid("56f9d3f8b88cdc5a0260aabf")).as(Person.class);
        System.out.println("One:" + person);
        System.out.println("One2:" + person2);
        System.out.println("One3:" + person3);

        //查询模板
        Person person4 = coll.findOne("{id:#,name:#}",1,"liu").as(Person.class);
        System.out.println("One4:" + person4);
        //正则查询
        Person person5 = coll.findOne("{name:{$regex:#}}","l.*").as(Person.class);
        System.out.println("One5:" + person5);

        //查询记录数
        long count = coll.count();
        System.out.println("共：" + count + " 条记录");
    }

    /**
     * 聚合
     */
    public void aggregate(){
        MongoCollection coll = jongo.getCollection("person");
        //聚合操作
        //distinct
        int size = coll.distinct("name").query("{id:1}").as(Person.class).size();
        System.out.println("size:" + size);
    }


    public void delete(){
        MongoCollection coll = jongo.getCollection("person");
        //删除
//		coll.remove();
        coll.remove(new ObjectId("56f934655435cc01aa5c1561"));
    }

    /**
     * 操作数组类型
     */
    public void optArray(){
        MongoCollection coll = jongo.getCollection("paramAlarm");
//		Map<String,Object> map = new HashMap<String, Object>();		map.put("orgId", "1");
//		map.put("mach", "MH2015080221");
//		map.put("setting", new String[]{"T8012","T9080", "T1820"});
//		coll.insert(map);

        //修改
//		coll.update("{mach:'MH2015080221'}").with("{$push:{\"setting\":\"T9999\"}}");

        String[] setting = new String[]{"T9090","T9081","T9094"};
//		coll.update("{mach:'MH2015080221'}").with("{$addToSet:{setting:{$each:#}}}", Arrays.asList(setting));
        coll.update("{mach:'MH2015080221'}").with("{$set:{setting:#}}", Arrays.asList(setting));

        HashMap as = coll.findOne("{mach:'MH2015080221'}").as(HashMap.class);
        System.out.println(as.get("setting"));
        ArrayList<String> strs = (ArrayList<String>) as.get("setting");
        String[] array = strs.toArray(new String[]{});
        boolean contains = strs.contains("T8012");
        System.out.println(contains);
    }

    /**
     * 测试时间类型
     */
    public void testDate(){
        MongoCollection coll = jongo.getCollection("person");
        Map str = coll.findOne("{id:4}").as(Map.class);
        System.err.println(str.toString());
    }
}
