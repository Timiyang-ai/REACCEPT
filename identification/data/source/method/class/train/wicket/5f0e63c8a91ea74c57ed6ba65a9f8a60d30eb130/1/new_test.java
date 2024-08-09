@Test
	public void set()
	{
		PageParameters parameters = new PageParameters();
		parameters
				.add("named1", "value1", INamedParameters.Type.MANUAL)
				.add("named2", "value2", INamedParameters.Type.MANUAL);

		assertEquals(0, parameters.getPosition("named1"));
		assertEquals(1, parameters.getPosition("named2"));

		// overwrite it
		parameters.set("named1", "newValue", INamedParameters.Type.MANUAL);
		parameters.set("named3", "value3", INamedParameters.Type.MANUAL);
		assertEquals(0, parameters.getPosition("named1"));
		assertEquals("newValue", parameters.get("named1").toString());
		assertEquals(1, parameters.getPosition("named2"));
		assertEquals(2, parameters.getPosition("named3"));
	}