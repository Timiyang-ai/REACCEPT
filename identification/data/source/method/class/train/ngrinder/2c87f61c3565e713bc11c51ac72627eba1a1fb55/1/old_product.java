@RequestMapping("/save")
	@PreAuthorize("hasAnyRole('A', 'S') or #user.id == #updatedUser.id")
	public String saveOrUpdateUserDetail(User user, ModelMap model, @ModelAttribute("user") User updatedUser,
			@RequestParam(required = false) String followers) {
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
			userService.modifyUser(updatedUser);
		} else {
			userService.saveUser(updatedUser);
		}
		if (user.getId() == updatedUser.getId()) {
			return "redirect:/";
		} else {
			return "redirect:/user/list";
		}
	}