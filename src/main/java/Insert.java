import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project name(项目名称)：JDBC事务处理
 * Package(包名): PACKAGE_NAME
 * Class(类名): Insert
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/1/19
 * Time(创建时间)： 20:12
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Insert
{
    /**
     * 数据库插入一条学生数据,无事务处理
     *
     * @param student 学生对象
     * @return 影响的行数
     */
    public static int insert(Student student)
    {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            //加载驱动,获得链接,从工具类中加载
            connection = JDBC.getConnection();
            //获得一个PreparedStatement对象,预编译
            String sql = "insert into information values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            //传递参数
            preparedStatement.setInt(1, student.getNo());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setInt(4, student.getAge());
            //System.out.println(sql);
            //执行sql语句，返回影响的行数
            result = preparedStatement.executeUpdate();
        }
        catch (SQLException e)                   //数据库异常
        {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("异常！异常内容为：" + e.getMessage());
            //调试使用：
            e.printStackTrace();
        }
        catch (Exception e)                     //其它异常
        {
            e.printStackTrace();
        }
        finally                                 //关闭
        {
            JDBC.close(connection, preparedStatement);
        }
        return result;
    }

    /**
     * 数据库插入一条数据，有事务处理
     *
     * @param connection 数据库连接对象
     * @param student    学生对象
     * @return 影响行数
     * @throws Exception 所有异常
     */
    private static int insert(Connection connection, Student student) throws Exception
    {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try
        {
            //获得一个PreparedStatement对象,预编译
            String sql = "insert into information values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            //传递参数
            preparedStatement.setInt(1, student.getNo());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setInt(4, student.getAge());
            //System.out.println(sql);
            //执行sql语句，返回影响的行数
            result = preparedStatement.executeUpdate();
        }
        finally                                 //关闭
        {
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 添加多条数据 支持事务处理
     *
     * @param list ArrayList<Student>对象
     * @return 影响行数
     */
    public static int insert(ArrayList<Student> list)
    {
        int result = 0;
        Connection connection = null;
        try
        {
            connection = JDBC.getConnection();      //取得链接
            connection.setAutoCommit(false);        //关闭自动提交
            for (int i = 0; i < list.size(); i++)
            {
                Student student = list.get(i);
                int result1 = insert(connection, student);
                if (result1 == 0)
                {
                    throw new Exception("执行第" + (i + 1) + "条数据时插入失败（结果为0）");
                }
            }
            connection.commit();            //提交
            result = list.size();
        }
        catch (Exception e)
        {
            Toolkit.getDefaultToolkit().beep();
            e.printStackTrace();
            if (connection != null)
            {
                try
                {
                    connection.rollback();     //事务回滚
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        finally                           //关闭连接
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }
}
