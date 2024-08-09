@Test
	public void defaultProfilableClassFilter() throws IOException {
		ProfilerConfig profilerConfig = new ProfilerConfig();
		Filter<String> profilableClassFilter = profilerConfig.getProfilableClassFilter();
		Assert.assertFalse(profilableClassFilter.filter("net/spider/king/wang/Jjang"));
	}