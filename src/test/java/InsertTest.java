import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InsertTest
{

    @Test
    void insert()       //无事务处理
    {
        Student student1 = new Student(20, "zzz", "男", 24);
        Student student2 = new Student(21, "fgs", "男", 25);
        Student student3 = new Student(20, "dyi", "女", 26);     //主键重复
        System.out.println(Insert.insert(student1));
        System.out.println(Insert.insert(student2));
        System.out.println(Insert.insert(student3));
    }

    @Test
    void testInsert()      //有事务处理
    {
        Student student1 = new Student(20, "zzz", "男", 24);
        Student student2 = new Student(21, "fgs", "男", 25);
        Student student3 = new Student(20, "dyi", "女", 26);
        ArrayList<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        System.out.println(Insert.insert(list));
    }
}