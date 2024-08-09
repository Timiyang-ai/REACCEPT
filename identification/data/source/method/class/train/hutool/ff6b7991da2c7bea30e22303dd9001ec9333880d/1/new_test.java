	@Test
	public void getMethodsTest() {
		Method[] methods = ReflectUtil.getMethods(ExamInfoDict.class);
		Assert.assertTrue(ArrayUtil.isNotEmpty(methods));
	}