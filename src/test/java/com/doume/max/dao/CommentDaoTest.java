package com.doume.max.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.spring.annotation.SpringBean;

import com.doume.max.entity.Comment;
import com.doume.max.entity.GoodsComment;
import com.doume.max.dao.CommentDao;

public class CommentDaoTest extends BaseDaoTest {

	@SpringBean("commentDao")
	private static CommentDao commentDao;
	private static Comment[] data;

	private static Comment createComment(Long cmmId, Date cmmTime, Long userId, String tagType,
			Long tagId, String content)
	{
		Comment cmm = new Comment();
		cmm.setCmmId(cmmId);
		cmm.setCmmTime(cmmTime);
		cmm.setContent(content);
		cmm.setTagId(tagId);
		cmm.setUserId(userId);
		return cmm;
	}
	@BeforeClass
	public static void initData() {
		data = new Comment[3];
		data[0] = createComment(1L,new Date(),1L,"Goods",1L,"Good");
		data[1] = createComment(2L,new Date(),2L,"News",2L,"Nice");
		data[2] = createComment(3L,new Date(),3L,"Goods",1L,"Bad");
	}

	@AfterClass
	public static void clearData() {
	}
	@Before
	public void saveData() {
		for(Comment cmm:data)
		{
			commentDao.save(cmm);
		}
	}

	@After
	public void removeData() {
		for(Comment cmm:data)
		{
			commentDao.remove(cmm);
		}
	}
	
	@Test
	public void get()
	{
		for(Comment cmm:data)
		{
			Comment rs = commentDao.get(cmm.getCmmId());
			assertBeanEquals(cmm,rs);
		}
	}
	@Test
	public void findByUserId()
	{
		List<Comment> rs;
		rs = commentDao.findByUserId(1L);
		assertEquals(new Integer(rs.size()),new Integer(1));
		rs = commentDao.findByUserId(2L);
		assertEquals(new Integer(rs.size()),new Integer(1));
		rs = commentDao.findByUserId(4L);
		assertEquals(new Integer(rs.size()),new Integer(0));
	}
	
	@Test
	public void saveGoodsComment()
	{
		GoodsComment gc = new GoodsComment();
		gc.setCmmTime(new Date());
		gc.setTagId(1L);
		gc.setUserId(1L);
		commentDao.save(gc);
		Comment dbgc = commentDao.get(gc.getCmmId());
		assertEquals(dbgc.getCmmId(),gc.getCmmId());
		List<Comment> rs = commentDao.findByUserIdEmpty(gc.getUserId());
		assertEquals(1, rs.size());
		commentDao.remove(gc);
	}
}
