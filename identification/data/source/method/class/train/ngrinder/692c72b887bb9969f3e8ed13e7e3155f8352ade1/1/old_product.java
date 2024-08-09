@PreAuthorize("hasAnyRole('A')")
	@RequestMapping("/delete")
	public String deleteUser(ModelMap model, @RequestParam String userIds) {
		String[] ids = userIds.split(",");
		for (String eachId : Arrays.asList(ids)) {
			userService.deleteUser(eachId);
		}
		model.clear();
		return "redirect:/user/";
	}