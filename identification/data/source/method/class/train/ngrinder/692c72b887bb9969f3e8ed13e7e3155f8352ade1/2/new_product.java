@RequestMapping("/new")
	@PreAuthorize("hasAnyRole('A') or #user.userId == #userId")
	public String openForm(User user, final ModelMap model) {
		model.addAttribute("roleSet", EnumSet.allOf(Role.class));
		return "user/detail";
	}