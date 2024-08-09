@PreAuthorize("hasAnyRole('A')")
	@RequestMapping("/delete")
	public String delete(ModelMap model, @RequestParam String userIds) {
		String[] ids = userIds.split(",");
		for (String eachId : Arrays.asList(ids)) {
			userService.delete(eachId);
		}
		model.clear();
		return "redirect:/user/";
	}