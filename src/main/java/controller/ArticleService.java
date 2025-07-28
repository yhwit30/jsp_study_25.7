package controller;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import koreaIT.util.DBUtil;
import koreaIT.util.SecSql;

public class ArticleService {
	
	private Connection conn;
	
	private ArticleDao articleDao;

	public ArticleService(Connection conn) {
		this.conn = conn;
		this.articleDao = new ArticleDao(conn);
	}

	public int getTotalCnt() {
		
//		return DBUtil.selectRowIntValue(conn, sql);
		return articleDao.getTotalCnt();
	}

	public List<Map<String, Object>> getArticleRows(int limitFrom, int itemsInAPage) {
		
//		return DBUtil.selectRows(conn, sql);
		return articleDao.getSelectRows(limitFrom, itemsInAPage);
	}

}
