@RequestMapping(method = RequestMethod.POST)
	public String handleSubmission(HttpSession httpSession,
			@RequestParam(required = true, value = "oldPassword") String oldPassword,
	        @RequestParam(required = true, value = "password") String password,
	        @RequestParam(required = true, value = "confirmPassword") String confirmPassword,
	        @RequestParam(required = false, value = "question") String question,
	        @RequestParam(required = false, value = "answer") String answer,
	        @RequestParam(required = false, value = "confirmAnswer") String confirmAnswer,
	        @ModelAttribute("user") User user, BindingResult errors) {
		
		NewPassword newPassword = new NewPassword(password, confirmPassword);
		NewQuestionAnswer newQuestionAnswer = new NewQuestionAnswer(question, answer, confirmAnswer);
		new NewPasswordValidator(user).validate(newPassword, errors);
		new NewQuestionAnswerValidator().validate(newQuestionAnswer, errors);
		
		if (errors.hasErrors()) {
			return showForm(httpSession);
		}
		
		changeUserPasswordAndQuestion(user, oldPassword, newPassword, newQuestionAnswer);
		httpSession.removeAttribute(WebConstants.OPENMRS_MSG_ATTR);
		return "redirect:/index.htm";
		
	}