@Test
	public void testRoolBack2() {
		long i = getJBPMApplication().startProcess("defaultPackage.Trade",
				"aaa", null);
		List<TaskVO> tasks = getJBPMApplication().queryTodoList("fhjl");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("approveStatus", "1");
		getJBPMApplication().completeTask(i, tasks.get(0).getTaskId(), "fhjl",
				XmlParseUtil.paramsToXml(data), null);

		getJBPMApplication().roolBack(i, 0, "fwzy");
		tasks = getJBPMApplication().queryTodoList("fhjl");
		Assert.assertTrue(tasks.size() > 0);
		getJBPMApplication().removeProcessInstance(i);
	}