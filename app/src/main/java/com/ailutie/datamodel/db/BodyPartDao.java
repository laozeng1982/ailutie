package com.ailutie.datamodel.db;

import android.content.Context;

import com.ailutie.datamodel.bean.BodyPart;
import com.ailutie.datamodel.bean.User;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

//import com.ailutie.datamodel.bean.BodyPart;

public class BodyPartDao
{
	private Dao<BodyPart, Integer> articleDaoOpe;
	private DatabaseHelper helper;

	@SuppressWarnings("unchecked")
	public BodyPartDao(Context context)
	{
		try
		{
			helper = DatabaseHelper.getHelper(context);
			articleDaoOpe = helper.getDao(BodyPart.class);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 添加一个BodyPart
	 * 
	 * @param article
	 */
	public void add(BodyPart article)
	{
		try
		{
			articleDaoOpe.create(article);

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

    /**
     * 通过Id得到一篇文章
     *
     * @param id
     * @return
     */
    public BodyPart get(int id)
    {
        BodyPart article = null;
        try
        {
            article = articleDaoOpe.queryForId(id);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return article;
    }

	/**
	 * 通过Id得到一个BodyPart
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BodyPart getBodyPartWithUser(int id)
	{
		BodyPart article = null;
		try
		{
			article = articleDaoOpe.queryForId(id);
			helper.getDao(User.class).refresh(article.getUser());

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return article;
	}

	/**
	 * 通过UserId获取所有的文章
	 * 
	 * @param userId
	 * @return
	 */
	public List<BodyPart> listByUserId(int userId)
	{
		try
		{
			/*QueryBuilder<BodyPart, Integer> articleBuilder = articleDaoOpe
					.queryBuilder();
			QueryBuilder userBuilder = helper.getDao(User.class).queryBuilder();
			articleBuilder.join(userBuilder);
			
			
			Where<BodyPart, Integer> where = queryBuilder.where();
			where.eq("user_id", 1);
			where.and();
			where.eq("name", "xxx");

			// 或者
			articleDaoOpe.queryBuilder().//
					where().//
					eq("user_id", 1).and().//
					eq("name", "xxx");
			//
			articleDaoOpe.updateBuilder().updateColumnValue("name","zzz").where().eq("user_id", 1);
			where.or(
					//
					where.and(//
							where.eq("user_id", 1), where.eq("name", "xxx")),
					where.and(//
							where.eq("user_id", 2), where.eq("name", "yyy")));*/

			return articleDaoOpe.queryBuilder().where().eq("user_id", userId)
					.query();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
