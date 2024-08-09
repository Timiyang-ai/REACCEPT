@Test
	public void getOrderFrequencies_shouldReturnAllOrderFrequenciesIncludingRetired() throws Exception {
		executeDataSet("org/openmrs/api/include/OrderServiceTest-getAllOrderFrequencies.xml");
		List<OrderFrequency> orderFrequencies = Context.getOrderService().getOrderFrequencies(true);
		Assert.assertEquals(3, orderFrequencies.size());
		Assert.assertEquals("28090760-7c38-11e3-baa7-0800200c9a66", orderFrequencies.get(0).getUuid());
		Assert.assertEquals("38090760-7c38-11e3-baa7-0800200c9a66", orderFrequencies.get(1).getUuid());
		Assert.assertEquals("48090760-7c38-11e3-baa7-0800200c9a66", orderFrequencies.get(2).getUuid());
	}