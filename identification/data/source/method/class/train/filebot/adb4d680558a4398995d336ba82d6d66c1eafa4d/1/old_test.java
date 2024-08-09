	@Test
	public void remove() throws Exception {
		Map<String, Integer> map = PreferencesMap.map(numbers, SimpleAdapter.forClass(Integer.class));

		map.remove("A");

		assertFalse(Arrays.asList(numbers.keys()).contains("A"));
	}