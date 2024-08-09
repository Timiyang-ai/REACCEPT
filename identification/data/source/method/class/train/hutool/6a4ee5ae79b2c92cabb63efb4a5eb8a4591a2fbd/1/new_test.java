	@Test
	public void containsTest() {
		Integer[] a = { 1, 2, 3, 4, 3, 6 };
		boolean contains = ArrayUtil.contains(a, 3);
		Assert.assertTrue(contains);

		long[] b = { 1, 2, 3, 4, 3, 6 };
		boolean contains2 = ArrayUtil.contains(b, 3);
		Assert.assertTrue(contains2);
	}