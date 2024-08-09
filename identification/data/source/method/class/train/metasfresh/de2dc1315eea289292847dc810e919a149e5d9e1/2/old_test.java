	@Test
	public void test_isEmpty_collection()
	{
		test_isEmpty_collection(true, null);
		test_isEmpty_collection(true, Collections.emptyList());
		test_isEmpty_collection(true, Collections.emptySet());
		test_isEmpty_collection(true, new ArrayList<Object>());
		test_isEmpty_collection(true, new HashSet<Object>());

		test_isEmpty_collection(false, Arrays.asList("1"));

		final List<Object> listNotEmpty = new ArrayList<Object>();
		listNotEmpty.add("one element");
		test_isEmpty_collection(false, listNotEmpty);
	}