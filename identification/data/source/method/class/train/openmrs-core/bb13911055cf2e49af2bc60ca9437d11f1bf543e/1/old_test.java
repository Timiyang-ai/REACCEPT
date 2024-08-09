@Test
	@Verifies(value = "get orderable concepts by name and drug class", method = "getOrderables(String)")
	public void getOrderables_shouldGetOrderableConceptsByNameAndDrugClass() throws Exception {
		executeDataSet(simpleOrderEntryDatasetFilename);
		
		String query = "Ampi";
		
		List<Orderable<?>> result = Context.getOrderService().getOrderables(query);
		
		Assert.assertNotNull(result);
		
		Assert.assertEquals(2, result.size());
	}