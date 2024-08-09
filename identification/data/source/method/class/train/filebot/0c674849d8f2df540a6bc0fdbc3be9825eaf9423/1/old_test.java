	@Test
	public void add() {
		List<Integer> list = PreferencesList.map(numbers, SimpleAdapter.forClass(Integer.class));

		list.add(3);

		assertEquals("3", numbers.get("3", null));
	}