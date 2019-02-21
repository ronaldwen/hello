package com.ronald;

import com.ronald.entity.User;

import java.util.*;

/**
 * lambda表达式的使用
 */
public class TestLambda {

    public static void main(String[] args) {
        TestLambda tl = new TestLambda();
//        tl.sortByCom();
//        tl.sortByLambda();
        tl.removeIfByLambda();
        tl.mapForEach();
    }

    /**
     * 普通写法，匿名内部类
     */
    public void sortByCom() {

        List<User> users = getUsers();
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        printUser(users, "----普通排序后---");
    }

    public void sortByLambda() {
        List<User> users = getUsers();
        users.sort((o1, o2) -> {
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            return o1.getAge() - o2.getAge();
        });
        printUser(users, "----lambda 写法，排序后---");

        users.sort((o1, o2) -> o2.getAge() - o1.getAge());
        users.sort(Comparator.comparingInt(User::getAge));
        printUser(users, "----lambda 写法，排序后2222---");

    }

    public void removeIfByLambda() {
        List<User> users = getUsers();
        //移除年龄 =5的user
        users.removeIf(user -> user.getAge() == 5);
        printUser(users, "----lambda 方式使用removeIf方法----");
    }

    public void mapForEach(){
        Map<String, User> usersMap = getUsersMap();
        usersMap.forEach((k, v) -> System.out.println(k + ": " + v.getAge()));
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("1", "a", 16));
        users.add(new User("2", "z", 5));
        users.add(new User("3", "c", 20));
        users.add(new User("4", "fe", 32));
        return users;
    }

    private Map<String, User> getUsersMap(){
        List<User> users = getUsers();
        HashMap<String, User> map = new HashMap<>();
        users.forEach(u -> map.put(u.getId(), u));
        return map;
    }


    private void printUser(List<User> users, String msg) {
        System.out.println(msg);
        for (User u : users) {
            System.out.println(u);
        }
    }

    /**
     * lambda 方式使用foreach 方法输出
     */
    private void printUserByLambda(List<User> users, String msg) {
        //lambda forEach 输出
        //forEach需要一个Comsumer函数接口
        users.forEach(user -> System.out.println("lambda方式输出的--" + user.getCode() + ":" + user.getAge()));
    }
}
