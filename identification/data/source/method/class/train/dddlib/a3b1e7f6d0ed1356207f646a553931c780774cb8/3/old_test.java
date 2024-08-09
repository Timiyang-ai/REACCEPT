@Test
	public void testRoolBack2(){
		long i = getJBPMApplication()
				.startProcess("defaultPackage.Trade","aaa",null);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("approveStatus", "1");
		getJBPMApplication().completeTask(1l, 1l, "fhjl",
				XmlParseUtil.paramsToXml(data), null);

		getJBPMApplication().roolBack(i, 0, "fwzy");
		List<TaskVO> tasks = getJBPMApplication().queryTodoList("fhjl");
		Assert.assertTrue(tasks.size()==1);
	}