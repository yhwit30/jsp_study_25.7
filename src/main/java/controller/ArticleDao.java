package controller;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import koreaIT.util.DBUtil;
import koreaIT.util.SecSql;

public class ArticleDao {

	private Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	public int getTotalCnt() {
		SecSql sql = new SecSql();
		sql.append("SELECT count(*)");
		sql.append("FROM `article`;");

		return DBUtil.selectRowIntValue(conn, sql);
	}

	public List<Map<String, Object>> getSelectRows(int limitFrom, int itemsInAPage) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `article` a");
		sql.append("INNER JOIN `member` m");
		sql.append("ON a.memberId = m.id");
		sql.append("ORDER BY a.`id` DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		return DBUtil.selectRows(conn, sql);
	}

}
