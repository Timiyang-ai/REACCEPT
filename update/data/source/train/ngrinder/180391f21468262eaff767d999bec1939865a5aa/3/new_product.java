@PreAuthorize("hasAnyRole('A')")
	@RequestMapping({"/api/list", "/api/list/"})
	@ResponseBody
	public Page<User> getAll(@RequestParam(required = false) Role role,
							   @PageableDefault(page = 0, size = 10) Pageable pageable,
							   @RequestParam(required = false) String keywords) {
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), defaultIfNull(pageable.getSort(), DEFAULT_SORT));
		Pageable defaultPageable = PageRequest.of(0, pageable.getPageSize(), defaultIfNull(pageable.getSort(), DEFAULT_SORT));
		Page<User> pagedUser;
		if (StringUtils.isEmpty(keywords)) {
			pagedUser = userService.getPagedAll(role, pageable);
			if (pagedUser.getNumberOfElements() == 0) {
				pagedUser = userService.getPagedAll(role, defaultPageable);
			}
		} else {
			pagedUser = userService.getPagedAll(keywords, pageable);
			if (pagedUser.getNumberOfElements() == 0) {
				pagedUser = userService.getPagedAll(keywords, defaultPageable);
			}
		}

		return pagedUser;
	}