@RequestMapping("/save")
	@PreAuthorize("hasAnyRole('A') or #user.id == #updatedUser.id")
	public String saveOrUpdateUserDetail(User user, ModelMap model, @ModelAttribute("user") User updatedUser,
	                                     @RequestParam(required = false) String followersStr) {
		checkArgument(updatedUser.validate());
		if (user.getRole() == Role.USER) {
			// General user can not change their role.
			User updatedUserInDb = userService.getUserById(updatedUser.getUserId());
			checkNotNull(updatedUserInDb);
			updatedUser.setRole(updatedUserInDb.getRole());

			// prevent user to modify with other user id
			checkArgument(updatedUserInDb.getId().equals(updatedUser.getId()), "Illegal request to update user:%s",
					updatedUser);
		}
		if (updatedUser.exist()) {
			userService.saveUser(updatedUser, followersStr);
		} else {
			userService.saveUser(updatedUser);
		}
		model.clear();
		if (user.getId().equals(updatedUser.getId())) {
			return "redirect:/";
		} else {
			return "redirect:/user/";
		}
	}