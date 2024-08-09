	@Test
	public void getDeclaredField() {
		Field noField = ClassUtil.getDeclaredField(TestSubClass.class, "noField");
		Assert.assertNull(noField);

		// 获取不到父类字段
		Field field = ClassUtil.getDeclaredField(TestSubClass.class, "field");
		Assert.assertNull(field);

		Field subField = ClassUtil.getDeclaredField(TestSubClass.class, "subField");
		Assert.assertNotNull(subField);
	}