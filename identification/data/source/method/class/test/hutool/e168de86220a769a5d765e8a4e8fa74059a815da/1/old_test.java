	@Test
	public void getPropertyTest() {
		SubPerson person = new SubPerson();
		person.setAge(14);
		person.setOpenid("11213232");
		person.setName("测试A11");
		person.setSubName("sub名字");
		
		Object name = BeanUtil.getProperty(person, "name");
		Assert.assertEquals("测试A11", name);
		Object subName = BeanUtil.getProperty(person, "subName");
		Assert.assertEquals("sub名字", subName);
	}