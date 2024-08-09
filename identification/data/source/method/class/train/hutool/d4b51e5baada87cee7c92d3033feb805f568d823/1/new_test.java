	@Test
	public void totalPage(){
		int totalPage = PageUtil.totalPage(20, 3);
		Assert.assertEquals(7, totalPage);
	}