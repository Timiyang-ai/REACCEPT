	@Test
	public void groupTest() {
		List<String> list = CollUtil.newArrayList("1", "2", "3", "4", "5", "6");
		List<List<String>> group = CollectionUtil.group(list, null);
		Assert.assertTrue(group.size() > 0);
		
		List<List<String>> group2 = CollectionUtil.group(list, new Hash<String>() {
			@Override
			public int hash(String t) {
				//按照奇数偶数分类
				return Integer.parseInt(t) % 2;
			}
			
		});
		Assert.assertEquals(CollUtil.newArrayList("2", "4", "6"), group2.get(0));
		Assert.assertEquals(CollUtil.newArrayList("1", "3", "5"), group2.get(1));
	}