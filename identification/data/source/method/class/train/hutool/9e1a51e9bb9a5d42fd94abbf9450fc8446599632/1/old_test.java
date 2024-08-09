	@Test
	public void getMethodTest() {
		Method method = ReflectUtil.getMethod(ExamInfoDict.class, "getId");
		Assert.assertEquals("getId", method.getName());
	}