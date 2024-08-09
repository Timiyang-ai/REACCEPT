@Test
	public void testQueryTodoList() {
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("creater", "abc");
		getJBPMApplication().startProcess("defaultPackage.Trade", "aaa", XmlParseUtil.paramsToXml(values));
		List<TaskVO> tasks = getJBPMApplication().queryTodoList("fhjl");
		Assert.assertTrue(tasks.size() > 0);
		//getJBPMApplication().removeProcessInstance(i);
	}