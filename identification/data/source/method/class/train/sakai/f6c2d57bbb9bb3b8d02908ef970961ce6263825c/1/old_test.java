	@Test
	public void convertToMap() {
		Properties props = new Properties();
		props.setProperty("foo", "bar");
		props.setProperty("lms", "Sakai");
		props.setProperty("", "something");

		Map<String, String> results = BasicLTIUtil.convertToMap(props);
		assertNotNull(results);
		assertEquals(3, results.size());
		assertEquals("bar", results.get("foo"));
		assertEquals("Sakai", results.get("lms"));
		assertEquals("something", results.get(""));
		assertNull(results.get("taco"));
	}