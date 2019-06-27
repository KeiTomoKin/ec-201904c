package jp.co.example.ecommerce_c.service;

public class ReviewForm {
	/** 名前 */
	private String name;
	/** コメント */
	private String content;
	/** 商品ID */
	private String itemId;
	/** 評価 */
	private String evaluation;
	@Override
	public String toString() {
		return "ReviewForm [name=" + name + ", content=" + content + ", itemId=" + itemId + ", evaluation=" + evaluation
				+ "]";
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getContent() {
		return content;
	}
	public final void setContent(String content) {
		this.content = content;
	}
	public final String getItemId() {
		return itemId;
	}
	public final void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public final String getEvaluation() {
		return evaluation;
	}
	public final void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	
}
