package com.lris.ain.other.entities;

/**
 * 文章
 * @author lris
 *
 */
public class Article {

	private long id;//
	
	private String  categoryId;//
	
	private String title;//
	
	private String content;//
	
	private String words;//

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", categoryId=" + categoryId + ", title=" + title + ", content=" + content
				+ ", words=" + words + "]";
	}
	
	
	
}
