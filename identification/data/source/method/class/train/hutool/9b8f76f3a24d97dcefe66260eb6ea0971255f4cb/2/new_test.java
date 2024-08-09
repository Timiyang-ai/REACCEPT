	@Test
	public void beanToMapTest() {
		SubPerson person = new SubPerson();
		person.setAge(14);
		person.setOpenid("11213232");
		person.setName("测试A11");
		person.setSubName("sub名字");
		
		Map<String, Object> map = BeanUtil.beanToMap(person);
		Assert.assertEquals(map.get("name"), "测试A11");
		Assert.assertEquals(map.get("age"), 14);
		Assert.assertEquals("11213232", map.get("openid"));
	}