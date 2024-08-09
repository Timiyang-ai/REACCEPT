@Test
	public void voidOrderGroup_shouldVoidOrdersInGroup() throws Exception {
		OrderGroup group = Context.getOrderService().getOrderGroup(2);
		String reason = "because";
		
		group = Context.getOrderService().voidOrderGroup(group, reason);
		Order order = (Order) group.getMembers().toArray()[0];
		
		// check if group is voided
		Assert.assertNotNull(group);
		Assert.assertTrue(group.isVoided());
		
		// check if group members are voided
		Assert.assertNotNull(order);
		Assert.assertTrue(order.isVoided());
	}