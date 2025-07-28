package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ArticleDao;
import dto.Article;

public class ArticleService {

	private Connection conn;

	private ArticleDao articleDao;

	public ArticleService(Connection conn) {
		this.conn = conn;
		this.articleDao = new ArticleDao(conn);
	}

	public int getTotalCnt() {
		return articleDao.getTotalCnt();
	}

	public List<Article> getArticleRows(int limitFrom, int itemsInAPage) {

		List<Map<String, Object>> rows = articleDao.getSelectRows(limitFrom, itemsInAPage);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> articleMap : rows) {
			Article article = new Article(articleMap);
			articles.add(article);
		}
		
		System.out.println(articles.get(0).toString());

		return articles;
	}

}
