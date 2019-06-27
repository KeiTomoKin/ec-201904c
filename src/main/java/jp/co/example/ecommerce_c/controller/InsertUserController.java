package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.InsertUserForm;
import jp.co.example.ecommerce_c.service.UserService;

/**
 * ユーザー登録のコントローラーです
 * 
 * @author kazuya.makida
 *
 */
@Controller
@RequestMapping("/registration")
public class InsertUserController {

	@Autowired
	private UserService userService;

	@ModelAttribute
	public InsertUserForm setUpInsertUserForm() {
		return new InsertUserForm();
	}

	/**
	 * ユーザー登録画面を表示します
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String toInsert() {
		return "register_user";
	}

	/**
	 * ユーザー情報を登録します
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertUserForm form, BindingResult result, Model model) {

		// 入力値チェック
		if (result.hasErrors()) {
			return this.toInsert();
		}

		// パスワードチェック
		if (!form.getPassword().equals(form.getPasswordAgain())) {
			model.addAttribute("passwordError", "入力されたパスワードが異なります");
			return this.toInsert();
		}

		// メールアドレスチェック
		if (userService.findByEmail(form.getEmail()) != null) {
			model.addAttribute("mailAddressError", "既に同じメールアドレスが登録されています");
			return this.toInsert();
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);

		// 名前の作成
		user.setName(form.getFirstName() + " " + form.getLastName());

		// 電話番号の生成
		user.setTelephone(form.getFirstNumber() + "-" + form.getMiddleNumber() + "-" + form.getLastNumber());

		userService.insert(user);

		return "redirect:/toLogin?inserted";
	}
	
}
