	@Test(dataProvider = "configParams", dataProviderClass = DataProviderClass.class)
	public void getValuesByCategoryTest(ConfigManager conf) {
		Map<String, String> map = conf.getValuesByCategory("acct");
		Iterator<String> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			assert (((String) itr.next()).contains("acct"));
		}
	}