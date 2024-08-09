@Test
	public void testQueryHistoryLog() {
		long i = getJBPMApplication().startProcess("defaultPackage.Trade",
				"aaa", null);
		List<HistoryLogVo> vos = getJBPMApplication().queryHistoryLog(i);
		Assert.assertTrue(vos.size() > 0);
		getJBPMApplication().removeProcessInstance(i);
	}