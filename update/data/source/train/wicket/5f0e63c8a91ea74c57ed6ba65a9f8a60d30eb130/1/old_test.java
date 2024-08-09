@Test
	public void set()
	{
		PageParameters parameters = new PageParameters();
		parameters.add("named1", "value1").add("named2", "value2");

		assertEquals(0, parameters.getPosition("named1"));
		assertEquals(1, parameters.getPosition("named2"));

		// overwrite it
		parameters.set("named1", "newValue");
		parameters.set("named3", "value3");
		assertEquals(0, parameters.getPosition("named1"));
		assertEquals("newValue", parameters.get("named1").toString());
		assertEquals(1, parameters.getPosition("named2"));
		assertEquals(2, parameters.getPosition("named3"));
	}