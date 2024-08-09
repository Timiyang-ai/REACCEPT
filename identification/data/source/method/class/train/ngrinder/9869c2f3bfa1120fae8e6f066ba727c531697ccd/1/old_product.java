@RestAPI
	@GetMapping("/profile")
	public HttpEntity<String> getOne(User user) {
		checkNotEmpty(user.getUserId(), "UserID should not be NULL!");
		Map<String, Object> model = new HashMap<>();
		User one = userService.getOneWithFollowers(user.getUserId());
		model.put("user", one);
		model.put("allowPasswordChange", !config.isDemo());
		model.put("allowRoleChange", false);
		model.put("showPasswordByDefault", false);
		attachCommonAttribute(one, model);
		return toJsonHttpEntity(model);
	}