@Ignore
	// re-enable test when we allow orders to be persisted when not activated and signed
	@Test
	public void saveOrder_shouldNotAllowYouToChangeTheOrderNumberOfASavedOrder() throws Exception {
		Order existing = service.getOrder(1);
		existing.setOrderNumber("New Number");
		try {
			service.saveOrder(existing);
			Assert.fail("the previous line should have thrown an exception");
		}
		catch (APIException ex) {
			// test this way rather than @Test(expected...) so we can verify it's the right APIException
			Assert.assertTrue(ex.getMessage().contains("orderNumber"));
		}
	}