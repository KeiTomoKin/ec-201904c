package jp.co.example.ecommerce_c.domain;

/**
 * 商品レビューのドメイン.
 * 
 * @author koichi.nagata
 *
 */
public class Review {
	/** コメントID */
	private Integer id;
	/** コメント投稿者氏名 */
	private String name;
	/** コメント内容 */
	private String content;
	/** 紐づける商品のID */
	private Integer itemId;
	/** 評価 */
	private Integer evaluation;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
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

	public final Integer getItemId() {
		return itemId;
	}

	public final void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	

	public final Integer getEvaluation() {
		return evaluation;
	}

	public final void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", name=" + name + ", content=" + content + ", itemId=" + itemId + ", evaluation="
				+ evaluation + "]";
	}

}
