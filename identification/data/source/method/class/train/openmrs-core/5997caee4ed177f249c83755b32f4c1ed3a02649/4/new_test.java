	@Test
	public void get_shouldGetEmptyResultForIndexesOutOfRange() throws ParseException {
		Result parentResult = new Result();
		Result secondResult = new Result(null, "some value", new Encounter(123));
		Result firstResult = new Result(Context.getDateFormat().parse("12/08/2008"), "some other value", new Encounter(124));
		
		parentResult.add(firstResult);
		parentResult.add(secondResult);
		
		// 3 is greater than the number of entries in the parentResult
		Assert.assertEquals(new EmptyResult(), parentResult.get(3));
	}