	@Test
	public void isEmptyTest() {
		int[] a = {};
		Assert.assertTrue(ArrayUtil.isEmpty(a));
		Assert.assertTrue(ArrayUtil.isEmpty((Object) a));
		int[] b = null;
		Assert.assertTrue(ArrayUtil.isEmpty(b));
		Object c = null;
		Assert.assertTrue(ArrayUtil.isEmpty(c));
	}