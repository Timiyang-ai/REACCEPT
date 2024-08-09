	@Test
	public void decimalFormatTest() {
		long c = 299792458;// 光速

		String format = NumberUtil.decimalFormat(",###", c);
		Assert.assertEquals("299,792,458", format);
	}