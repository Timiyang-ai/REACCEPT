@RestAPI
	@PreAuthorize("hasAnyRole('A')")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public HttpEntity<String> getOne(@PathVariable("userId") String userId) {
		return toJsonHttpEntity(userService.getOne(userId));
	}