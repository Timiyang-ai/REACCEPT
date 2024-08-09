	@Test
	public void getPublicMethod() {
		Method superPublicMethod = ClassUtil.getPublicMethod(TestSubClass.class, "publicMethod");
		Assert.assertNotNull(superPublicMethod);
		Method superPrivateMethod = ClassUtil.getPublicMethod(TestSubClass.class, "privateMethod");
		Assert.assertNull(superPrivateMethod);

		Method publicMethod = ClassUtil.getPublicMethod(TestSubClass.class, "publicSubMethod");
		Assert.assertNotNull(publicMethod);
		Method privateMethod = ClassUtil.getPublicMethod(TestSubClass.class, "privateSubMethod");
		Assert.assertNull(privateMethod);
	}