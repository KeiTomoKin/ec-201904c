package jp.co.example.ecommerce_c.domain;

import java.util.Date;

/**
 * クーポンの種類を表すドメイン.
 * 
 * @author keita.tomooka
 *
 */
public class Coupon {
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** 説明 */
	private String description;
	/** 機能（クラス名） */
	private String className;
	/** 有効期限 */
	private Date deadline;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + name + ", description=" + description + ", className=" + className
				+ ", deadLine=" + deadline + "]";
	}

}
