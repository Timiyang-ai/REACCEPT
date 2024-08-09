	@Test
	public void roundTest() {

		// 四舍
		String round1 = NumberUtil.roundStr(2.674, 2);
		String round2 = NumberUtil.roundStr("2.674", 2);
		Assert.assertEquals("2.67", round1);
		Assert.assertEquals("2.67", round2);

		// 五入
		String round3 = NumberUtil.roundStr(2.675, 2);
		String round4 = NumberUtil.roundStr("2.675", 2);
		Assert.assertEquals("2.68", round3);
		Assert.assertEquals("2.68", round4);

		// 补0
		String round5 = NumberUtil.roundStr(2.6005, 2);
		String round6 = NumberUtil.roundStr("2.6005", 2);
		Assert.assertEquals("2.60", round5);
		Assert.assertEquals("2.60", round6);
		
		// 补0
		String round7 = NumberUtil.roundStr(2.600, 2);
		String round8 = NumberUtil.roundStr("2.600", 2);
		Assert.assertEquals("2.60", round7);
		Assert.assertEquals("2.60", round8);
	}