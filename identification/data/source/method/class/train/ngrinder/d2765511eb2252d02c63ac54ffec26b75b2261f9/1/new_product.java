@RequestMapping("/list")
	public String getTestList(User user, @RequestParam(required = false) String query,
			@RequestParam(required = false) boolean isFinished, @RequestParam(required = false) PageRequest pageable,
			ModelMap model) {
		// FIXME
		// not to paging on server side for now. Get all tests and
		// paging/sorting in page.
		// if (pageable == null) {
		// pageable = new PageRequest(0, DEFAULT_TEST_PAGE_ZISE);
		// }
		// TODO please provide sort as well in request.
		Page<PerfTest> testList = perfTestService.getPerfTestList(user, query, isFinished, pageable);
		model.addAttribute("testListPage", testList);
		return "perftest/list";
	}