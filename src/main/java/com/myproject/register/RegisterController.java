package com.myproject.register;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //변환
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
//		binder.setValidator(new UserValidator()); // 자동검증 UserValidator를 WebDataBinder의 로컬 validator로 등록
//		binder.addValidators(new UserValidator()); // 자동검증 Global,User Validator 
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList=" + validatorList);
	}
	
	@GetMapping("/add")
	public String register() {
		return "registerForm"; // WEB-INF/views/registerForm.jsp
	}
	
	@PostMapping("/save")
	public String save(@Valid User user, BindingResult result, Model m) throws Exception { //자동검증 @Valid등록
		System.out.println("reault="+result);
		
//		수동검증 Validator를 직접생성하고 validate()를 직접호출
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result); // BindingResult는 Errors의 자손
		
		//User객체를 검증한 결과 에러가 발생하면 registerForm.jsp으로 보낸다.
		if(result.hasErrors()) {
			return "registerForm";
		}
		
//		// 1.유효성 검사.
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
//			
//			m.addAttribute("msg", msg);
//			return "redirect:/register/add"; //redirect는 view로 모델을 전달할 수 없다. 
////			return "redirect:/register/add?msg="+msg; // URL재작성(rewriting)
//		}
		
		// 2. DB 신규회원 정보 저장.
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}
