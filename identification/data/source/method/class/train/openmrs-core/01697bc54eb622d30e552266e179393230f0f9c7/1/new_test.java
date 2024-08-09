@Test(expected = IllegalArgumentException.class)
	@Verifies(value = "shoudl fail if null passed in", method = "getOrderables(String)")
	public void getOrderables_shouldFailIfNullPassedIn() throws Exception {
		executeDataSet(simpleOrderEntryDatasetFilename);
		
		String query = null;
		Context.getOrderService().getOrderables(query);
	}