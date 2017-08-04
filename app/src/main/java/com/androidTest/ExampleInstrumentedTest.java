package com.androidTest;

import android.content.Context;

import com.ailutie.activity.MainActivity;
import com.ailutie.datamodel.bean.User;
import com.ailutie.datamodel.db.UserDao;
import com.ailutie.tools.LogAndroid;

import org.junit.Test;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.

    }

    Context context = MainActivity.getInstance();

    @Test
    public void TestDB() {
        LogAndroid.e("This");
    }

    @Test
    public void testAddUser() {
        User u = new User();
        u.setName("张鸿洋");
        LogAndroid.e("id: " + u.getId());
        new UserDao(context).add(u);


    }

//    public void testAddTrainPlan(int id) {
//        User u = new User();
//        u.setName("张鸿洋");
//        LogAndroid.e("id: " + u.getId());
//        new UserDao(this).add(u);
//        TrainPlan trainPlan = new TrainPlan(new Date());
//
//        trainPlan.setUser(u);
//        new BodyPartDao(context).add(trainPlan);
//        Article article = new Article(new Date());
//        article.setTitle("ORMLite的使用" + id);
//        article.setUser(u);
//        new ArticleDao(this).add(article);
//
//    }
//
//    public void testGetArticleById() {
////        for (Article article:(new ArticleDao(this)).get())
//        Article article = new ArticleDao(this).get(1);
//        LogAndroid.e(article.getUser() + " , " + article.getTitle());
//    }
//
//    public void testGetArticleWithUser() {
//
//        Article article = new ArticleDao(this).getArticleWithUser(1);
//        LogAndroid.e(article.getUser() + " , " + article.getTitle());
//    }
//
//    public void testListArticlesByUserId() {
//
//        List<Article> articles = new ArticleDao(this).listByUserId(1);
//        for (Article article : articles)
//            LogAndroid.e(article.toString());
//    }
//
//    public void testGetUserById() {
//        User user = new UserDao(this).get(1);
//        L.e(user.getName());
//        if (user.getArticles() != null)
//            for (Article article : user.getArticles()) {
//                L.e(article.toString());
//            }
//    }
//
//    public void testAddStudent() throws SQLException {
//        Dao dao = DatabaseHelper.getHelper(this).getDao(Student.class);
//        Student student = new Student();
//        student.setDao(dao);
//        student.setName("张鸿洋");
//        student.create();
//    }
}
