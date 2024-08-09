@PreAuthorize("hasAnyRole('A')")
	@RequestMapping({"", "/"})
	public String getAll(ModelMap model, @RequestParam(required = false) Role role,
						 @PageableDefault(page = 0, size = 10) Pageable pageable,
	                     @RequestParam(required = false) String keywords) {
		pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), defaultIfNull(pageable.getSort(), DEFAULT_SORT));
		Pageable defaultPageable = new PageRequest(0, pageable.getPageSize(), defaultIfNull(pageable.getSort(), DEFAULT_SORT));
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
			model.put("keywords", keywords);
		}


		model.addAttribute("users", pagedUser);
		EnumSet<Role> roleSet = EnumSet.allOf(Role.class);
		model.addAttribute("roleSet", roleSet);
		model.addAttribute("role", role);
		putPageIntoModelMap(model, pageable);
		return "user/list";
	}