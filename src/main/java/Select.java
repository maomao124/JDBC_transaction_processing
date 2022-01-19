import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Project name(项目名称)：JDBC事务处理
 * Package(包名): PACKAGE_NAME
 * Class(类名): Select
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/1/19
 * Time(创建时间)： 20:09
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Select
{
    /**
     * 数据库查找
     *
     * @return ArrayList<Student>对象
     */
    public static ArrayList<Student> find()
    {
        ArrayList<Student> list = new ArrayList<>();
        Connection connection = null;
        //PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try
        {
            //加载驱动,获得链接,从工具类中加载
            connection = JDBC.getConnection();
            //创建一个Statement对象
            statement = connection.createStatement();
            String sql = "select * from information";
            //执行sql语句，返回结果集
            resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Integer no = resultSet.getInt("no");
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                Integer age = resultSet.getInt("age");
                Student student = new Student(no, name, sex, age);
                list.add(student);
            }
        }
        catch (SQLException e)                   //数据库异常
        {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("异常！异常内容为：" + e.getMessage());
            //调试使用：
            //e.printStackTrace();
        }
        catch (Exception e)                     //其它异常
        {
            e.printStackTrace();
        }
        finally                                 //关闭
        {
            JDBC.close(connection, statement, resultSet);
        }
        return list;
    }
}
