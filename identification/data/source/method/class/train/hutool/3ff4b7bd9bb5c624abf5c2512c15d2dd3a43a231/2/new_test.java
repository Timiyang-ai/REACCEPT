	@Test
	public void formatTest() {
		String f1 = NumberChineseFormater.format(10889.72356, false);
		Assert.assertEquals("一万零八百八十九点七二", f1);
		f1 = NumberChineseFormater.format(12653, false);
		Assert.assertEquals("一万二千六百五十三", f1);
		f1 = NumberChineseFormater.format(215.6387, false);
		Assert.assertEquals("二百一十五点六四", f1);
		f1 = NumberChineseFormater.format(1024, false);
		Assert.assertEquals("一千零二十四", f1);
		f1 = NumberChineseFormater.format(100350089, false);
		Assert.assertEquals("一亿三十五万零八十九", f1);
		f1 = NumberChineseFormater.format(1200, false);
		Assert.assertEquals("一千二百", f1);
		f1 = NumberChineseFormater.format(12, false);
		Assert.assertEquals("一十二", f1);
		f1 = NumberChineseFormater.format(0.05, false);
		Assert.assertEquals("零点零五", f1);
	}