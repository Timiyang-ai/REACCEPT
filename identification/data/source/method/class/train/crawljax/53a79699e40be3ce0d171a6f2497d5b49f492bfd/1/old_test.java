@Test
	public final void testGetInputWithRandomValue() {
		assertNull("Wrong Xpath so null as result of InputWithRandomValue",
		        browser.getInputWithRandomValue(new FormInput("text", new Identification(
		                How.xpath, "/RUBISH"), "abc")));
	}