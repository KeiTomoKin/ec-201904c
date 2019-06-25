package jp.co.example.ecommerce_c.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SpringSecurity設定用クラス
 * 
 * @author takara.miyazaki
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * 静的リソースに対してセキュリティ設定を無視する
	 *
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**"
								 , "/img/**"
								 , "/js/**"
								 , "/fonts/**");

		
	}
	
	/**
	 * 認可とログインログアウトの設定.
	 *
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//ログイン処理うまくいかなかった時用
		//http.formLogin().disable();
		//http.csrf().ignoringAntMatchers("/displayItemList/findName","/displayItemList/**","/logout/**","/login**");
		
		http.authorizeRequests() //認可に関する設定
			//すべてのユーザがアクセスできるリンク
			.antMatchers("/registration/**", "/toLogin",  "/displayItemList/**", "/item/**", "/cart/**").permitAll() 
			.anyRequest().authenticated();
		
		http.formLogin() //ログインに関する設定
			.loginPage("/toLogin") // ログイン画面に遷移させるパス(ログイン認証が必要なパスを指定してかつログインされていないとこのパスに遷移される)
			.loginProcessingUrl("/login") // ログインボタンを押した際に遷移させるパス(ここに遷移させれば自動的にログインが行われる)
			.failureUrl("/toLogin?error=true") //ログイン失敗に遷移させるパス
			.defaultSuccessUrl("/displayItemList/showList", false) // 第1引数:デフォルトでログイン成功時に遷移させるパス
		                                        	// 第2引数: true :認証後常に第1引数のパスに遷移 
		                                        	//         false:認証されてなくて一度ログイン画面に飛ばされてもログインしたら指定したURLに遷移
			.usernameParameter("email") // 認証時に使用するユーザ名のリクエストパラメータ名(今回はメールアドレスを使用)
			.passwordParameter("password"); // 認証時に使用するパスワードのリクエストパラメータ名
		
		http.logout() // ログアウトに関する設定
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout**")) // ログアウトさせる際に遷移させるパス
			.logoutSuccessUrl("/displayItemList/showList?logout") // ログアウト後に遷移させるパス(ここではログイン画面を設定)
			.deleteCookies("JSESSIONID") // ログアウト後、Cookieに保存されているセッションIDを削除
			.invalidateHttpSession(true); // true:ログアウト後、セッションを無効にする false:セッションを無効にしない
	}
	
	/**
	 * 「認証」に関する設定.
	 *
	 */
	@Override
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/**
	 * ログイン成功した時の処理.
	 * 実装する際は
	 * .formLogin().successHandler(new MyAuthenticationSuccessHandler());
	 *
	 */
	/*
	public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	    @Override
	    public void onAuthenticationSuccess(
	            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	                throws IOException, ServletException {
	    	System.out.println("ログイン成功");
	        
	    }
	}
	*/
	
	/**
	 * パスワードのハッシュ化を行うためのエンコーダー.
	 * 
	 * @return エンコーダー
	 */
	@Bean
    public PasswordEncoder passwordEncoder() {
    		return new BCryptPasswordEncoder();
    }
	

}
