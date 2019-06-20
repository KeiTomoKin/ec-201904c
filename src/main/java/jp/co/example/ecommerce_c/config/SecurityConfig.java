package jp.co.example.ecommerce_c.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**"
								 , "/img/**"
								 , "/js/**"
								 , "/fonts/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//ログイン処理うまくいかなかった時用
		//http.formLogin().disable();
		
		http.authorizeRequests() //認可に関する設定
			.antMatchers("/", "/registration", "/registration/insert", "/toLogin").permitAll() //すべてのユーザがアクセスできるリンク
			.anyRequest().authenticated();
		
		http.formLogin() //ログインに関する設定
			.loginPage("/toLogin") // ログイン画面に遷移させるパス(ログイン認証が必要なパスを指定してかつログインされていないとこのパスに遷移される)
			.loginProcessingUrl("/login") // ログインボタンを押した際に遷移させるパス(ここに遷移させれば自動的にログインが行われる)
			.failureUrl("toLogin/?error=true") //ログイン失敗に遷移させるパス
			.defaultSuccessUrl("/item_list", true) // 第1引数:デフォルトでログイン成功時に遷移させるパス
		                                        	// 第2引数: true :認証後常に第1引数のパスに遷移 
		                                        	//         false:認証されてなくて一度ログイン画面に飛ばされてもログインしたら指定したURLに遷移
			.usernameParameter("email") // 認証時に使用するユーザ名のリクエストパラメータ名(今回はメールアドレスを使用)
			.passwordParameter("password"); // 認証時に使用するパスワードのリクエストパラメータ名
		
		http.logout() // ログアウトに関する設定
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout**")) // ログアウトさせる際に遷移させるパス
			.logoutSuccessUrl("/item_list") // ログアウト後に遷移させるパス(ここではログイン画面を設定)
			.deleteCookies("JSESSIONID") // ログアウト後、Cookieに保存されているセッションIDを削除
			.invalidateHttpSession(true); // true:ログアウト後、セッションを無効にする false:セッションを無効にしない
	}
	
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(memberDetailsService)
//			.passwordEncoder(new BCryptPasswordEncoder());
//	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
    		return new BCryptPasswordEncoder();
    }
	

}
