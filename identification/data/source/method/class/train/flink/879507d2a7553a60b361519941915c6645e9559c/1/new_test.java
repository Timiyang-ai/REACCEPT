@Test
	public void testRestore() throws Exception {
		final List<KafkaTopicPartition> partitions = new ArrayList<>(PARTITION_STATE.keySet());

		final DummyFlinkKafkaConsumer<String> consumerFunction =
			new DummyFlinkKafkaConsumer<>(TOPICS, partitions, FlinkKafkaConsumerBase.PARTITION_DISCOVERY_DISABLED);

		StreamSource<String, DummyFlinkKafkaConsumer<String>> consumerOperator =
				new StreamSource<>(consumerFunction);

		final AbstractStreamOperatorTestHarness<String> testHarness =
				new AbstractStreamOperatorTestHarness<>(consumerOperator, 1, 1, 0);

		testHarness.setTimeCharacteristic(TimeCharacteristic.ProcessingTime);

		testHarness.setup();

		// restore state from binary snapshot file
		testHarness.initializeState(
			OperatorSnapshotUtil.getResourceFilename(
				"kafka-consumer-migration-test-flink" + testMigrateVersion + "-snapshot"));

		testHarness.open();

		// assert that there are partitions and is identical to expected list
		assertTrue(consumerFunction.getSubscribedPartitionsToStartOffsets() != null);
		assertTrue(!consumerFunction.getSubscribedPartitionsToStartOffsets().isEmpty());

		// on restore, subscribedPartitionsToStartOffsets should be identical to the restored state
		assertEquals(PARTITION_STATE, consumerFunction.getSubscribedPartitionsToStartOffsets());

		// assert that state is correctly restored from legacy checkpoint
		assertTrue(consumerFunction.getRestoredState() != null);
		assertEquals(PARTITION_STATE, consumerFunction.getRestoredState());

		consumerOperator.close();
		consumerOperator.cancel();
	}