	@Test
	public void getFieldValuesTest() {
		Dict v1 = Dict.create().set("id", 12).set("name", "张三").set("age", 23);
		Dict v2 = Dict.create().set("age", 13).set("id", 15).set("name", "李四");
		ArrayList<Dict> list = CollectionUtil.newArrayList(v1, v2);

		List<Object> fieldValues = CollectionUtil.getFieldValues(list, "name");
		Assert.assertEquals("张三", fieldValues.get(0));
		Assert.assertEquals("李四", fieldValues.get(1));
	}