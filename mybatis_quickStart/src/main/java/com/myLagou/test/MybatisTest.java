package com.myLagou.test;

import com.myLagou.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author zhy
 * @create 2022-08-07 14:48
 */
public class MybatisTest {

    //mybatis快速入门的测试方法
    @Test
    public void mybatisQuickStart() throws IOException {
        // 1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3.获取sqlSession会话对象    此对象可以调用很多的CRUD方法
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.执行sql
        /*selectList(参数)：所需要的参数就是一个statementid，也就是之前在mapper映射文件中的namespace.id*/
        List<User> userList = sqlSession.selectList("user.findAll");
        // 5.处理结果  打印结果
        for (User user: userList ) {
            System.out.println(user);
        }
        // 6.释放资源
        sqlSession.close();
    }


    /*测试新增用户的方法*/
    @Test
    public void testAddUser() throws IOException {
        // 1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.执行sql
        //创建user对象
        User user = new User();
        user.setUsername("rose");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("湖南长沙");
        int insert = sqlSession.insert("user.insertUser", user);
        // 5.打印结果
        System.out.println(insert);

        //6、手动提交事务   插入操作在没有提交事务之前，数据库不会有改变
        sqlSession.commit();
        // 7.释放资源
        sqlSession.close();
    }

    /*更新User*/
    @Test
    public void testUpdateUser() throws IOException {
        //1、加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建user对象
        User user = new User();
        user.setId(6);
        user.setUsername("大憨憨");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("湖南长沙");
        //4、执行sql
        int update = sqlSession.update("user.updateUser", user);
        //5、处理结果，返回结果
        System.out.println(update);

        //6、手动提交事务   修改操作在没有提交事务之前，数据库不会有改变
        sqlSession.commit();
        // 7.释放资源
        sqlSession.close();
    }

    /*删除User*/
    @Test
    public void testDeleteUser() throws IOException {
        //1、加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3、获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4、执行sql
        int delete = sqlSession.delete("user.deleteUser", 7);
        //5、处理结果，返回结果
        System.out.println(delete);

        //6、手动提交事务   修改操作在没有提交事务之前，数据库不会有改变
        sqlSession.commit();
        // 7.释放资源
        sqlSession.close();
    }

}
