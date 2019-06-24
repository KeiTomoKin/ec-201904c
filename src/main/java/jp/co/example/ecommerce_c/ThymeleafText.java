package jp.co.example.ecommerce_c;

import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Thymeleafでプレーンテキストを作成するクラス.
 *
 * @author takuya.aramaki
 */
@Component
@Scope("prototype")
public class ThymeleafText {
	/** Thymeleafのテンプレートエンジン */
	private final TemplateEngine templateEngine;
	/** Thymeleafが使う変数を格納するオブジェクト */
	private final Context context;

	public ThymeleafText(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
		context = new Context(Locale.getDefault());
	}

	/**
	 * Thymeleafテンプレートを処理します.
	 *
	 * @param template テンプレートのパス
	 * @return 処理した結果の文字列
	 */
	public String process(String template) {
		return templateEngine.process(template, context);
	}

	/**
	 * テンプレート内で使う変数をセットします.
	 * 
	 * @param name  変数名
	 * @param value 変数の中身のオブジェクト
	 */
	public void setVariable(String name, Object value) {
		context.setVariable(name, value);
	}
}
