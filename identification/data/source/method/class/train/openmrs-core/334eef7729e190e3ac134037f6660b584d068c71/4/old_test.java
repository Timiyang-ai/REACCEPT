@Test
	public void voidOrderGroup_shouldVoidOrderGroup() throws Exception {
		OrderGroup group = Context.getOrderService().getOrderGroup(2);
		String reason = "because";
		
		group = Context.getOrderService().voidOrderGroup(group, reason);
		
		Assert.assertNotNull(group);
		Assert.assertTrue(group.isVoided());
	}