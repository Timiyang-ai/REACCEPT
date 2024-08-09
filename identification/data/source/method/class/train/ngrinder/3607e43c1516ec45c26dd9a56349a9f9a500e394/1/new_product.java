@PreAuthorize("hasAnyRole('A')")
	@RequestMapping({"", "/"})
	public String getUsers(ModelMap model, @RequestParam(required = false) Role role,
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
			pagedUser = userService.getUsersByRole(role, pageable);
		} else {
			pagedUser = userService.getUsersByKeyWord(keywords, pageable);
			model.put("keywords", keywords);
		}
		model.addAttribute("userPage", pagedUser);
		EnumSet<Role> roleSet = EnumSet.allOf(Role.class);
		model.addAttribute("roleSet", roleSet);
		model.addAttribute("role", role);
		model.addAttribute("page", pageable);
		if (sort != null) {
			Order sortProp = (Order) sort.iterator().next();
			model.addAttribute("sortColumn", sortProp.getProperty());
			model.addAttribute("sortDirection", sortProp.getDirection());
		}
		return "user/list";
	}