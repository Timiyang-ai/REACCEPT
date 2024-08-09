@RequestMapping("/list")
	public String getTestList(User user, ModelMap model, @RequestParam(required = false) boolean isFinished,
			@RequestParam(required = false) PageRequest pageable) {
		// FIXME
		// not to paging on server side for now. Get all tests and
		// paging/sorting in page.
		// if (pageable == null) {
		// pageable = new PageRequest(0, DEFAULT_TEST_PAGE_ZISE);
		// }
		Page<PerfTest> testList = perfTestService.getTestList(user, isFinished, pageable);
		model.addAttribute("testListPage", testList);
		return "perftest/list";
	}