	@Test
	public void test_iterateOverTable_fixedValues() {
		RandVar aRV = new RandVar("A", new BooleanDomain());
		RandVar bRV = new RandVar("B", new BooleanDomain());
		RandVar cRV = new RandVar("C", new BooleanDomain());
		ProbabilityTable ptABC = new ProbabilityTable(new double[] {
				// A = true, B = true, C = true
				1.0,
				// A = true, B = true, C = false
				10.0,
				// A = true, B = false, C = true
				100.0,
				// A = true, B = false, C = false
				1000.0,
				// A = false, B = true, C = true
				10000.0,
				// A = false, B = true, C = false
				100000.0,
				// A = false, B = false, C = true
				1000000.0,
				// A = false, B = false, C = false
				10000000.0 }, aRV, bRV, cRV);

		final List<Double> answer = new ArrayList<Double>();
		ProbabilityTable.Iterator pti = new ProbabilityTable.Iterator() {

			@Override
			public void iterate(Map<RandomVariable, Object> possibleAssignment,
					double probability) {
				answer.add(probability);
			}
		};

		answer.clear();
		ptABC.iterateOverTable(pti, new AssignmentProposition(aRV, true));
		Assert.assertEquals(1111.0, sumOf(answer), DELTA_THRESHOLD);

		answer.clear();
		ptABC.iterateOverTable(pti, new AssignmentProposition(aRV, false));
		Assert.assertEquals(11110000.0, sumOf(answer), DELTA_THRESHOLD);

		answer.clear();
		ptABC.iterateOverTable(pti, new AssignmentProposition(bRV, true));
		Assert.assertEquals(110011.0, sumOf(answer), DELTA_THRESHOLD);

		answer.clear();
		ptABC.iterateOverTable(pti, new AssignmentProposition(bRV, false));
		Assert.assertEquals(11001100.0, sumOf(answer), DELTA_THRESHOLD);

		answer.clear();
		ptABC.iterateOverTable(pti, new AssignmentProposition(cRV, true));
		Assert.assertEquals(1010101.0, sumOf(answer), DELTA_THRESHOLD);

		answer.clear();
		ptABC.iterateOverTable(pti, new AssignmentProposition(cRV, false));
		Assert.assertEquals(10101010.0, sumOf(answer), DELTA_THRESHOLD);

		answer.clear();
		ptABC.iterateOverTable(pti, new AssignmentProposition(bRV, true),
				new AssignmentProposition(cRV, true));
		Assert.assertEquals(10001.0, sumOf(answer), DELTA_THRESHOLD);
	}