	@Test
	public void toJsonStrTest() {
		UserA a1 = new UserA();
		a1.setA("aaaa");
		a1.setDate(DateUtil.date());
		a1.setName("AAAAName");
		UserA a2 = new UserA();
		a2.setA("aaaa222");
		a2.setDate(DateUtil.date());
		a2.setName("AAAA222Name");

		ArrayList<UserA> list = CollectionUtil.newArrayList(a1, a2);
		HashMap<String, Object> map = CollectionUtil.newHashMap();
		map.put("total", 13);
		map.put("rows", list);

		String str = JSONUtil.toJsonStr(map);
		Assert.assertNotNull(str);
	}