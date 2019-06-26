package jp.co.example.ecommerce_c.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_c.domain.LoginUser;
import jp.co.example.ecommerce_c.domain.User;
import jp.co.example.ecommerce_c.form.UpdateUserForm;
import jp.co.example.ecommerce_c.service.UserService;

/**
 * ユーザ情報を更新するコントローラー.
 * 
 * @author takara.miyazaki
 *
 */
@Controller
@RequestMapping("/edit")
public class EditUserController {
	
	@Autowired
	UserService userService;
	
	@ModelAttribute
	public UpdateUserForm setUpdateUserForm(@AuthenticationPrincipal LoginUser loginUser) {
		UpdateUserForm userForm = new UpdateUserForm();
		User user = loginUser.getUser();
		BeanUtils.copyProperties(user, userForm);
		
		String[] userName = user.getName().split(" ");
		String[] userTel = user.getTelephone().split("-");
		userForm.setFirstName(userName[0]);
		userForm.setLastName(userName[1]);
		userForm.setFirstNumber(userTel[0]);
		userForm.setMiddleNumber(userTel[1]);
		userForm.setLastNumber(userTel[2]);
		
		return userForm;
	}
	
	/**
	 * ユーザ編集画面を出力.
	 * 
	 * @return ユーザ編集画面
	 */
	@RequestMapping("")
	public String showEdit() {
		return "edit_user";
	}
	
	/**
	 * ユーザ情報を更新
	 * 
	 * @param form
	 * @param result
	 * @param loginUser
	 * @param model
	 * @return 商品一覧画面
	 */
	@RequestMapping("/updateUser")
	public String updateUser(@Validated UpdateUserForm form, BindingResult result,
			@AuthenticationPrincipal LoginUser loginUser, Model model) {
		
		if(result.hasErrors()) {
			return showEdit();
		}
		if (!form.getEmail().equals(loginUser.getUsername()) && userService.findByEmail(form.getEmail()) != null) {
			model.addAttribute("mailAddressError", "既に同じメールアドレスが登録されています");
			return showEdit();
		}
		
		//Userテーブルを更新
		User user = loginUser.getUser();
		BeanUtils.copyProperties(form, user);
		user.setName(form.getFirstName() + " " + form.getLastName());
		user.setTelephone(form.getFirstNumber() + "-" + form.getMiddleNumber() + "-" + form.getLastNumber());
		
		userService.update(user);
		
		//現在ログインしているユーザの情報を更新
		LoginUser updateLoginUser = new LoginUser(user,loginUser.getAuthorities());
		Authentication authentication = new UsernamePasswordAuthenticationToken(updateLoginUser, null, updateLoginUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/?edited";
	}

}
