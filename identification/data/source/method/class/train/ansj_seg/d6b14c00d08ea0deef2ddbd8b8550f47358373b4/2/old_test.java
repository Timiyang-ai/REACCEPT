	@Test
	public void wordAlertTest() {
		List<Element> wordAlert = Config.wordAlert("超过1亿") ;
		System.out.println(wordAlert);
	}