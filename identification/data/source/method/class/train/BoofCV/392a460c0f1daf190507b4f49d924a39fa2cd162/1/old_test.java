@Test
	public void findErrorEvaluator() {
		GrowQueue_I8 syndromes = array(64, 192, 93, 231, 52, 92, 228, 49, 83, 245);
		GrowQueue_I8 errorLocator = array(3,1);

		GrowQueue_I8 found = new GrowQueue_I8();
		GrowQueue_I8 expected = array(0,64);

		ReidSolomonCodes alg = new ReidSolomonCodes(8,primitive8);
		alg.findErrorEvaluator(syndromes,errorLocator,found);

		assertEquals(found.size,expected.size);
		for (int j = 0; j < found.size; j++) {
			assertEquals(found.get(j),expected.get(j));
		}
	}