@Test
	public void testOmit() {
		Map<String, Object> params = createMap();
		
		
		Map<String,Object> omitted = RequestUtil.omit(params, "A");
	
		Assert.assertTrue(omitted.keySet().containsAll(Lists.newArrayList("B","C","D")));
		Assert.assertTrue(omitted.values().containsAll(Lists.newArrayList(2,3,4)));
		
		omitted = RequestUtil.omit(params, "F");
		Assert.assertTrue(omitted.keySet().containsAll(Lists.newArrayList("A","B","C","D")));
		Assert.assertTrue(omitted.values().containsAll(Lists.newArrayList(1,2,3,4)));
	}