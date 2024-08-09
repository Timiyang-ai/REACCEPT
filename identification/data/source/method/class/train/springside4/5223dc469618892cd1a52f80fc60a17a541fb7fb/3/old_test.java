	@Test
	public void invokeMethod() {
		TestBean bean = new TestBean();
		// 使用函数名+参数类型的匹配, 支持传参数
		assertThat(ReflectionUtil.invokeMethod(bean, "privateMethod", new Object[] { "calvin" }))
				.isEqualTo("hello calvin");

		// 使用函数名+参数类型的匹配
		assertThat(ReflectionUtil.invokeMethod(bean, "privateMethod", new Object[] { "calvin" },
				new Class[] { String.class })).isEqualTo("hello calvin");

		// 仅匹配函数名
		assertThat(ReflectionUtil.invokeMethodByName(bean, "privateMethod", new Object[] { "calvin" }))
				.isEqualTo("hello calvin");

		// 各种类型
		assertThat(ReflectionUtil.invokeMethod(bean, "intType", new Object[] { 1 }, new Class[] { int.class }))
				.isEqualTo(1);

		assertThat(ReflectionUtil.invokeMethod(bean, "integerType", new Object[] { 1 }, new Class[] { Integer.class }))
				.isEqualTo(1);

		assertThat(ReflectionUtil.invokeMethod(bean, "listType", new Object[] { ListUtil.newArrayList("1", "2") },
				new Class[] { List.class })).isEqualTo(2);

		assertThat(ReflectionUtil.invokeMethod(bean, "intType", 1)).isEqualTo(1);

		assertThat(ReflectionUtil.invokeMethod(bean, "integerType", 1)).isEqualTo(1);

		assertThat(ReflectionUtil.invokeMethod(bean, "listType", ListUtil.newArrayList("1", "2"))).isEqualTo(2);

		// 函数名错
		try {
			ReflectionUtil.invokeMethod(bean, "notExistMethod", new Object[] { "calvin" },
					new Class[] { String.class });
			failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
		} catch (IllegalArgumentException e) {

		}

		// 参数类型错
		try {
			ReflectionUtil.invokeMethod(bean, "privateMethod", new Object[] { "calvin" },
					new Class[] { Integer.class });
			failBecauseExceptionWasNotThrown(RuntimeException.class);
		} catch (RuntimeException e) {

		}

		// 函数名错
		try {
			ReflectionUtil.invokeMethodByName(bean, "notExistMethod", new Object[] { "calvin" });
			failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
		} catch (IllegalArgumentException e) {

		}
	}