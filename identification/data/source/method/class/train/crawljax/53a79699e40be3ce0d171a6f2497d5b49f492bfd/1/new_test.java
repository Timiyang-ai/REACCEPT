@Test
	public final void testGetInputWithRandomValue() {
		assertNull("Wrong Xpath so null as result of InputWithRandomValue",
				browser.getInputWithRandomValue(new FormInput(InputType.TEXT,
						new Identification(How.xpath, "/RUBISH"), "abc")));
	}