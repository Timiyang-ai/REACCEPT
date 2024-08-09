	@Test
	public void parseTest() {
		User user = new User();
		user.setId(1);
		user.setName("test");
		
		Entity entity = Entity.create("testTable").parseBean(user);
		Assert.assertEquals(Integer.valueOf(1), entity.getInt("id"));
		Assert.assertEquals("test", entity.getStr("name"));
	}