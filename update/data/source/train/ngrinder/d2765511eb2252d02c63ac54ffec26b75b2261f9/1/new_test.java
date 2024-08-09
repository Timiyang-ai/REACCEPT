@SuppressWarnings("unchecked")
	@Test
	public void testGetTestList() {
		ModelMap model = new ModelMap();
		controller.getTestList(getTestUser(), null, false, null, model);
		Page<PerfTest> testPage = (Page<PerfTest>) model.get("testListPage");
		List<PerfTest> testList = testPage.getContent();

		assertThat(testList.size(), is(1));

	}