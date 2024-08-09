	@Test
	public void test_composeNotNulls()
	{
		final Evaluatee e1 = dummyEvaluatee("e1");
		final Evaluatee e2 = dummyEvaluatee("e2");
		final Evaluatee e3 = dummyEvaluatee("e3");

		test_composeNotNulls(Arrays.asList(e1), e1);
		test_composeNotNulls(Arrays.asList(e1), null, e1, null);
		test_composeNotNulls(Arrays.asList(e1, e2, e3), e1, e2, e3);
		test_composeNotNulls(Arrays.asList(e1, e2, e3), null, e1, null, e2, null, e3, null, null, null, null);
	}