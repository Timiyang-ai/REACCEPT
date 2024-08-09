@Test
	public void testAssignToNode() {
		long i = getJBPMApplication().startProcess("defaultPackage.Trade",
				"aaa", null);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("approveStatus", "1");
		getJBPMApplication().completeTask(i, 1l, "fhjl",
				XmlParseUtil.paramsToXml(data), null);
		
		// [id=2, name=分行经理审批]
		getJBPMApplication().assignToNode(i, 2l);
		
		List<TaskVO> tasks = getJBPMApplication().queryTodoList("fhjl");
		Assert.assertTrue(tasks.size() > 0);
		
		data = new HashMap<String, Object>();
		data.put("approveStatus", "1");
		getJBPMApplication().completeTask(i, tasks.get(0).getTaskId(), "fhjl",
				XmlParseUtil.paramsToXml(data), null);
		
		tasks = getJBPMApplication().queryTodoList("fwzy");
		Assert.assertTrue(tasks.size() > 0);
//		
		getJBPMApplication().removeProcessInstance(i);
	}