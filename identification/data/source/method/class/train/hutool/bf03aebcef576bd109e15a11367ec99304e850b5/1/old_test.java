	@Test
	public void groupByFieldTest() {
		List<TestBeans> list = CollUtil.newArrayList(new TestBeans("张三", 12), new TestBeans("李四", 13), new TestBeans("王五", 12));
		List<List<TestBeans>> groupByField = CollUtil.groupByField(list, "age");
		Assert.assertEquals("张三", groupByField.get(0).get(0).getName());
		Assert.assertEquals("王五", groupByField.get(0).get(1).getName());
		
		Assert.assertEquals("李四", groupByField.get(1).get(0).getName());
	}