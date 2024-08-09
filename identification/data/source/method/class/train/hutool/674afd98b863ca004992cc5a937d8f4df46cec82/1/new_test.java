	@Test
	public void transToStartEndTest(){
		int[] startEnd1 = PageUtil.transToStartEnd(1, 10);
		Assert.assertEquals(0, startEnd1[0]);
		Assert.assertEquals(10, startEnd1[1]);
		
		int[] startEnd2 = PageUtil.transToStartEnd(2, 10);
		Assert.assertEquals(10, startEnd2[0]);
		Assert.assertEquals(20, startEnd2[1]);
	}