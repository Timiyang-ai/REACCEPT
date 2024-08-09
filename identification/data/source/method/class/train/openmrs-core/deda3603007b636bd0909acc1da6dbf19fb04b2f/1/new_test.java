	@Test
	public void setRequiredModules_shouldSetModulesWhenThereIsANullRequiredModulesMap() {
		testModule.setRequiredModulesMap(null);
		assertNull(testModule.getRequiredModulesMap());

		ArrayList<String> first = new ArrayList<>();
		ArrayList<String> second = new ArrayList<>();

		first.add("mod1");
		first.add("mod2");
		second.add("mod2");
		second.add("mod3");

		testModule.setRequiredModules(first);
		testModule.setRequiredModules(second);

		ArrayList<String> ret = new ArrayList<>(testModule.getRequiredModules());
		assertTrue(ret.contains("mod1"));
		assertTrue(ret.contains("mod2"));
		assertTrue(ret.contains("mod3"));
		assertEquals(3, ret.size());
	}