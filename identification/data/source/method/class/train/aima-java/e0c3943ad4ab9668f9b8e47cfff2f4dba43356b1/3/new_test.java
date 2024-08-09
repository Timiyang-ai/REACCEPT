	@Test
	public void test_indexOf() {
		RandVar X = new RandVar("X", new BooleanDomain());
		RandVar Y = new RandVar("Y", new ArbitraryTokenDomain("A", "B", "C"));
		RandVar Z = new RandVar("Z", new BooleanDomain());

		// An ordered X,Y,Z enumeration of values should look like:
		// 00: true, A, true
		// 01: true, A, false
		// 02: true, B, true
		// 03: true, B, false
		// 04: true, C, true
		// 05: true, C, false
		// 06: false, A, true
		// 07: false, A, false
		// 08: false, B, true
		// 09: false, B, false
		// 10: false, C, true
		// 11: false, C, false
		RandomVariable[] vars = new RandomVariable[] { X, Y, Z };
		Map<RandomVariable, Object> event = new LinkedHashMap<RandomVariable, Object>();

		event.put(X, Boolean.TRUE);
		event.put(Y, "A");
		event.put(Z, Boolean.TRUE);
		Assert.assertEquals(0, ProbUtil.indexOf(vars, event));
		event.put(Z, Boolean.FALSE);
		Assert.assertEquals(1, ProbUtil.indexOf(vars, event));
		event.put(Y, "B");
		event.put(Z, Boolean.TRUE);
		Assert.assertEquals(2, ProbUtil.indexOf(vars, event));
		event.put(Z, Boolean.FALSE);
		Assert.assertEquals(3, ProbUtil.indexOf(vars, event));
		event.put(Y, "C");
		event.put(Z, Boolean.TRUE);
		Assert.assertEquals(4, ProbUtil.indexOf(vars, event));
		event.put(Z, Boolean.FALSE);
		Assert.assertEquals(5, ProbUtil.indexOf(vars, event));
		//
		event.put(X, Boolean.FALSE);
		event.put(Y, "A");
		event.put(Z, Boolean.TRUE);
		Assert.assertEquals(6, ProbUtil.indexOf(vars, event));
		event.put(Z, Boolean.FALSE);
		Assert.assertEquals(7, ProbUtil.indexOf(vars, event));
		event.put(Y, "B");
		event.put(Z, Boolean.TRUE);
		Assert.assertEquals(8, ProbUtil.indexOf(vars, event));
		event.put(Z, Boolean.FALSE);
		Assert.assertEquals(9, ProbUtil.indexOf(vars, event));
		event.put(Y, "C");
		event.put(Z, Boolean.TRUE);
		Assert.assertEquals(10, ProbUtil.indexOf(vars, event));
		event.put(Z, Boolean.FALSE);
		Assert.assertEquals(11, ProbUtil.indexOf(vars, event));
	}