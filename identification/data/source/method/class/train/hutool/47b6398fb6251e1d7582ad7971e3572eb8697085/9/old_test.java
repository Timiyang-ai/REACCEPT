	@Test
	public void copyPropertiesTest() {
		SubPerson p1 = new SubPerson();
		p1.setSlow(true);
		
		SubPerson p2 = new SubPerson();
		BeanUtil.copyProperties(p1, p2);
		Assert.assertTrue(p2.isSlow());
	}