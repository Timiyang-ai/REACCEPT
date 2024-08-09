@PreAuthorize("hasAnyRole('A')")
	@RequestMapping({ "", "/" })
	public String getUserList(ModelMap model, @RequestParam(required = false) String roleName,
					@RequestParam(required = false) String keywords) {

		List<User> userList = null;
		if (StringUtils.isEmpty(keywords)) {
			userList = userService.getAllUserByRole(roleName);
		} else {
			userList = userService.getUserListByKeyWord(keywords);
			model.put("keywords", keywords);
		}

		model.addAttribute("userList", userList);
		EnumSet<Role> roleSet = EnumSet.allOf(Role.class);
		model.addAttribute("roleSet", roleSet);
		model.addAttribute("roleName", roleName);

		return "user/list";
	}