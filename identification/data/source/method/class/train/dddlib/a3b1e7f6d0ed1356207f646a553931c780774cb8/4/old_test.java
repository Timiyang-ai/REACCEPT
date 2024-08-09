@Test
	public void testQueryHistoryLog() {
		List<HistoryLogVo> vos = getJBPMApplication().queryHistoryLog(1l);
		Assert.assertTrue(vos.size() > 0);
	}