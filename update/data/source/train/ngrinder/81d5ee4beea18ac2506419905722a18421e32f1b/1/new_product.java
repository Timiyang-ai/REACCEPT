@RestAPI
	@PreAuthorize("hasAnyRole('A')")
	@RequestMapping("/api/{userId}/check_duplication")
	public HttpEntity<String> checkDuplication(ModelMap model, @PathVariable String userId) {
		User user = userService.getUserById(userId);
		return (user == null) ? successJsonHttpEntity() : errorJsonHttpEntity();
	}