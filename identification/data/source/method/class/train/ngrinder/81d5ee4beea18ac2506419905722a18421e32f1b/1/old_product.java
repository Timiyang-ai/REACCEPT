@PreAuthorize("hasAnyRole('A')")
	@RequestMapping("/{userId}/duplication_check")
	@ResponseBody
	public String checkUserId(ModelMap model, @PathVariable String userId) {
		User user = userService.getUserById(userId);
		return (user == null) ? returnSuccess() : returnError();
	}