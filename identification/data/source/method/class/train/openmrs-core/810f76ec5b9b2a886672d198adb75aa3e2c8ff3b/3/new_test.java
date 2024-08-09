	@Test
	public void toObject_shouldReturnResultObjectForSingleResults() {
		Result firstResult = new Result(new Date(), "some value", new Encounter(123));
		
		Assert.assertEquals(123, ((Encounter) firstResult.toObject()).getId().intValue());
	}