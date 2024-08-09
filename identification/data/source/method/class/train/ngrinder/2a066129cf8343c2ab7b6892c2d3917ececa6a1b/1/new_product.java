@RestAPI
	@PreAuthorize("hasAnyRole('A')")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getOne(@PathVariable("userId") String userId) {
		return userService.getOne(userId);
	}