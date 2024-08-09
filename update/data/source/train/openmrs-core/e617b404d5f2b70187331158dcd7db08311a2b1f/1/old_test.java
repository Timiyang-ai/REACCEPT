@Test
	@Verifies(value = "should return null if no object found with given uuid", method = "getOrderTypeByUuid(String)")
	public void getOrderTypeByUuid_shouldReturnNullIfNoObjectFoundWithGivenUuid() throws Exception {
		Assert.assertNull(Context.getOrderService().getOrderTypeByUuid("some invalid uuid"));
	}