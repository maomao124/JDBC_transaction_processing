/**
 * Project name(项目名称)：JDBC事务处理
 * Package(包名): PACKAGE_NAME
 * Class(类名): Student
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/1/19
 * Time(创建时间)： 20:10
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Student
{
    private Integer no;
    private String name;
    private String sex;
    private Integer age;

    public Student()
    {

    }

    public Student(Integer no, String name, String sex, Integer age)
    {
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Integer getNo()
    {
        return no;
    }

    public void setNo(Integer no)
    {
        this.no = no;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    @Override
    @SuppressWarnings("all")
    public String toString()
    {
        final StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("no：").append(no).append('\t');
        stringbuilder.append("name：").append(name).append('\t');
        stringbuilder.append("sex：").append(sex).append('\t');
        stringbuilder.append("age：").append(age).append('\n');
        return stringbuilder.toString();
    }
}
