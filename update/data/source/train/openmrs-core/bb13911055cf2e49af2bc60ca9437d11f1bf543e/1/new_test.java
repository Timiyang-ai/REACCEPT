@Test
	@Verifies(value = "get orderable concepts by name and drug class", method = "getOrderables(String)")
	public void getOrderables_shouldGetOrderableConceptsByNameAndDrugClass() throws Exception {
		executeDataSet(simpleOrderEntryDatasetFilename);
		
		String query = "Ampi";
		
		List<Orderable<?>> result = Context.getOrderService().getOrderables(query);
		
		Assert.assertNotNull(result);
		
		Assert.assertEquals(3, result.size());
		
		Boolean isExpected = result.get(0).getClass().equals(GenericDrug.class);
		Assert.assertTrue(isExpected);
		isExpected = result.get(1).getClass().equals(GenericDrug.class);
		Assert.assertTrue(isExpected);
		isExpected = result.get(2).getClass().equals(Drug.class);
		Assert.assertTrue(isExpected);
	}