@Test
	public void testAsPactModule() throws Exception {
		final Operator input1 = new Source(PersistenceType.HDFS, "1");
		final Operator input2 = new Source(PersistenceType.HDFS, "2");
		final Operator input3 = new Source(PersistenceType.HDFS, "3");
		final CompositeOperator fixture = new CompositeOperatorImpl(1, input1, input2, input3);
		final EvaluationContext context = new EvaluationContext();

		final PactModule result = fixture.asPactModule(context);

		assertNotNull(result);
		final List<Level<Contract>> reachableNodes = GraphLevelPartitioner.getLevels(
			result.getAllOutputs(), ContractNavigator.INSTANCE);
		assertEquals(3, reachableNodes.get(0).getLevelNodes().size());
		assertEquals(1, reachableNodes.get(1).getLevelNodes().size());
		assertEquals(1, reachableNodes.get(2).getLevelNodes().size());
		assertEquals(1, reachableNodes.get(3).getLevelNodes().size());

		for (int index = 0; index < 3; index++)
			assertTrue(DataSourceContract.class.isInstance(reachableNodes.get(0).getLevelNodes()
				.get(index)));
		assertSame(ElementaryOperatorImpl.Implementation.class, reachableNodes.get(1)
			.getLevelNodes().get(0).getStubClass());
		assertSame(ElementaryOperatorImpl.Implementation.class, reachableNodes.get(2)
			.getLevelNodes().get(0).getStubClass());
		assertTrue(DataSinkContract.class.isInstance(reachableNodes.get(3).getLevelNodes().get(0)));
	}