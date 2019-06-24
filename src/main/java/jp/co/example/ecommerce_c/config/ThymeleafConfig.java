package jp.co.example.ecommerce_c.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * メールのテンプレートとしてThymeleafを用いるための設定
 *
 * @author takuya.aramaki
 */
@Configuration
public class ThymeleafConfig {
	/**
	 * プレーンテキストモードのThymeleafテンプレートエンジンを返します.
	 * 
	 * @return プレーンテキストモードのThymeleafエンジン
	 */
	@Bean
	public TemplateEngine textTemplateEngine() {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setSuffix(".txt");
		resolver.setTemplateMode(TemplateMode.TEXT);
		resolver.setCharacterEncoding("UTF-8");

		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(resolver);

		return engine;
	}
}
