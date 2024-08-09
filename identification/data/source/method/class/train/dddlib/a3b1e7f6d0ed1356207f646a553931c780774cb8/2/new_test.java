@Test
	public void testQueryTodoList() {
		
		long i = getJBPMApplication().startProcess("defaultPackage.Trade", "aaa", null);
		List<TaskVO> tasks = getJBPMApplication().queryTodoList("fhjl");
		Assert.assertTrue(tasks.size() > 0);
		//getJBPMApplication().removeProcessInstance(i);
	}