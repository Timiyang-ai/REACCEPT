	@Test
	public void diff() {
		List<JobDiffInfo.ConfigDiffInfo> diffInfoList = Lists.newArrayList();
		// case #1: empty string
		zkDBDiffService.diff("ns", "", "", diffInfoList);
		assertEquals(0, diffInfoList.size());
		diffInfoList.clear();
		// case #2: db is not empty but zk is empty
		zkDBDiffService.diff("ns", "123", "", diffInfoList);
		assertEquals(1, diffInfoList.size());
		diffInfoList.clear();
		// case #3: db is empty but zk is not empty
		zkDBDiffService.diff("ns", "", "123", diffInfoList);
		assertEquals(1, diffInfoList.size());
		diffInfoList.clear();
		// case #4: trim
		zkDBDiffService.diff("ns", "123", "123  ", diffInfoList);
		assertEquals(0, diffInfoList.size());
		diffInfoList.clear();
		// case #5: db and zk not equal string
		zkDBDiffService.diff("ns", "123", "456", diffInfoList);
		assertEquals(1, diffInfoList.size());
		diffInfoList.clear();
	}