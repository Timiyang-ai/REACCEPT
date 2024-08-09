	@Test
	public void getFieldTest() {
		// 能够获取到父类字段
		Field privateField = ReflectUtil.getField(TestSubClass.class, "privateField");
		Assert.assertNotNull(privateField);
	}