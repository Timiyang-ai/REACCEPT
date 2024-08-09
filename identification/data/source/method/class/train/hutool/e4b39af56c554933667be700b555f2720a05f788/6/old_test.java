	@Test
	public void filterTest() {
		ArrayList<String> list = CollUtil.newArrayList("a", "b", "c");
		
		Collection<String> filtered = CollUtil.filter(list, new Editor<String>() {
			@Override
			public String edit(String t) {
				return t +1;
			}});
		
		Assert.assertEquals(CollUtil.newArrayList("a1", "b1", "c1"), filtered);
	}