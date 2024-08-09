@PreAuthorize("hasAnyRole('A')")
	@RequestMapping("/delete")
	public String delete(User user, @RequestParam String userIds, ModelMap model) {
		String[] ids = userIds.split(",");
		for (String eachId : Arrays.asList(ids)) {
			if (!user.getUserId().equals(eachId)) {
				userService.delete(eachId);
			}
		}
		model.clear();
		return "redirect:/user/";
	}