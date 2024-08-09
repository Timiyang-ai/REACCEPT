	@Test
	public void getDeclaredMethod() throws Exception {
		Method noMethod = ClassUtil.getDeclaredMethod(TestSubClass.class, "noMethod");
		Assert.assertNull(noMethod);

		Method privateMethod = ClassUtil.getDeclaredMethod(TestSubClass.class, "privateMethod");
		Assert.assertNotNull(privateMethod);
		Method publicMethod = ClassUtil.getDeclaredMethod(TestSubClass.class, "publicMethod");
		Assert.assertNotNull(publicMethod);

		Method publicSubMethod = ClassUtil.getDeclaredMethod(TestSubClass.class, "publicSubMethod");
		Assert.assertNotNull(publicSubMethod);
		Method privateSubMethod = ClassUtil.getDeclaredMethod(TestSubClass.class, "privateSubMethod");
		Assert.assertNotNull(privateSubMethod);

	}