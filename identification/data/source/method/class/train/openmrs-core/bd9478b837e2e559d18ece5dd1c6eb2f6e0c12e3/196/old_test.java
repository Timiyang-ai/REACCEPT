	@Test
	public void getObservations_shouldCompareDatesUsingLteAndGte() throws ParseException {
		executeDataSet(INITIAL_OBS_XML);
		
		ObsService os = Context.getObsService();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		// Test 1, No bounderies
		Date sd = df.parse("2006-02-01");
		Date ed = df.parse("2006-02-20");
		List<Obs> obs = os.getObservations(null, null, null, null, null, null, null, null, null, sd, ed, false);
		assertEquals(9, obs.size());
		
		// Test 2, From boundary
		sd = df.parse("2006-02-13");
		ed = df.parse("2006-02-20");
		obs = os.getObservations(null, null, null, null, null, null, null, null, null, sd, ed, false);
		assertEquals(4, obs.size());
		
		// Test 3, To boundary
		sd = df.parse("2006-02-01");
		ed = df.parse("2006-02-15");
		obs = os.getObservations(null, null, null, null, null, null, null, null, null, sd, ed, false);
		assertEquals(8, obs.size());
		
		// Test 4, Both Boundaries
		sd = df.parse("2006-02-11");
		ed = new SimpleDateFormat("yyyy-MM-dd-hh-mm").parse("2006-02-11-11-59");
		obs = os.getObservations(null, null, null, null, null, null, null, null, null, sd, ed, false);
		assertEquals(1, obs.size());
		
		// Test 5, Outside before
		sd = df.parse("2006-02-01");
		ed = df.parse("2006-02-08");
		obs = os.getObservations(null, null, null, null, null, null, null, null, null, sd, ed, false);
		assertEquals(0, obs.size());
		
		// Test 6, Outside After
		sd = df.parse("2006-02-17");
		ed = df.parse("2006-02-20");
		obs = os.getObservations(null, null, null, null, null, null, null, null, null, sd, ed, false);
		assertEquals(0, obs.size());
	}