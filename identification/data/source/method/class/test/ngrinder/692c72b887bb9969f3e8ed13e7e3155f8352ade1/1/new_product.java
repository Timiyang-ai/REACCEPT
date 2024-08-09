@RequestMapping("/save")
	public String saveOrUpdateUserDetail(@ModelAttribute("user") User newUser) {
		checkArgument(newUser.getRole().equals(Role.USER), "User role must be General user !");
		userService.createUser(newUser);
		return "redirect:/home";
	}