@PreAuthorize("hasAnyRole('A')")
	@DeleteMapping({"/api", "/api/"})
	public HttpEntity<String> deleteUsers(User user, @RequestParam String userIds) {
		String[] ids = userIds.split(",");
		for (String eachId : ids) {
			if (!user.getUserId().equals(eachId)) {
				userService.delete(eachId);
			}
		}
		return successJsonHttpEntity();
	}