@Test
	public void fromJson() throws Exception {
		// Bean
		String beanString = "{\"name\":\"A\"}";
		TestBean bean = JsonMapper.INSTANCE.fromJson(beanString, TestBean.class);
		System.out.println("Bean:" + bean);

		// Map
		String mapString = "{\"name\":\"A\",\"age\":2}";
		Map<String, Object> map = JsonMapper.INSTANCE.fromJson(mapString, HashMap.class);
		System.out.println("Map:");
		for (Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		// List<String>
		String listString = "[\"A\",\"B\",\"C\"]";
		List<String> stringList = JsonMapper.INSTANCE.fromJson(listString, List.class);
		System.out.println("String List:");
		for (String element : stringList) {
			System.out.println(element);
		}

		// List<Bean>
		String beanListString = "[{\"name\":\"A\"},{\"name\":\"B\"}]";
		List<TestBean> beanList = JsonMapper.INSTANCE.fromJson(beanListString,
				JsonMapper.INSTANCE.buildCollectionType(List.class, TestBean.class));
		System.out.println("Bean List:");
		for (TestBean element : beanList) {
			System.out.println(element);
		}
	}