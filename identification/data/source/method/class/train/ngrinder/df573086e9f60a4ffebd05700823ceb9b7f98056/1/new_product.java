@PreAuthorize("hasAnyRole('A')")
	@RequestMapping({ "", "/" })
	public String getUserList(ModelMap model, @RequestParam(required = false) String roleName,
					@PageableDefaults(pageNumber = 0, value = 10) Pageable pageable,
					@RequestParam(required = false) String keywords) {

		PageRequest pageReq = ((PageRequest) pageable);
		Sort sort = pageReq == null ? null : pageReq.getSort();
		if (sort == null && pageReq != null) {
			sort = new Sort(Direction.ASC, "userName");
			pageable = new PageRequest(pageReq.getPageNumber(), pageReq.getPageSize(), sort);
		}
		Page<User> pagedUser = null;
		if (StringUtils.isEmpty(keywords)) {
			pagedUser = userService.getAllUserByRole(roleName, pageable);
		} else {
			pagedUser = userService.getUserListByKeyWord(keywords, pageable);
			model.put("keywords", keywords);
		}
		model.addAttribute("userPage", pagedUser);
		EnumSet<Role> roleSet = EnumSet.allOf(Role.class);
		model.addAttribute("roleSet", roleSet);
		model.addAttribute("roleName", roleName);
		model.addAttribute("page", pageable);
		return "user/list";
	}