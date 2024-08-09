	@Test
	public void getClassPathTest() {
		String classPath = ClassUtil.getClassPath();
		Assert.assertNotNull(classPath);
	}