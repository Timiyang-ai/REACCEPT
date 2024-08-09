	@Test
	public void newHashSetTest() {
		Set<String> set = CollectionUtil.newHashSet((String[]) null);
		Assert.assertNotNull(set);
	}