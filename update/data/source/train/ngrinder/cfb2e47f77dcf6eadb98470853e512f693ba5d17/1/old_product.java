@GetMapping("/{userId}/detail")
	@PreAuthorize("hasAnyRole('A')")
	public Map<String, Object> getOne(@PathVariable final String userId) {
		User one = userService.getOneWithFollowers(userId);
		Map<String, Object> model = buildMap(
			"user", one,
			"allowPasswordChange", true,
			"allowRoleChange", true,
			"roleSet", EnumSet.allOf(Role.class),
			"showPasswordByDefault", false
		);
		attachCommonAttribute(one, model);
		return model;
	}