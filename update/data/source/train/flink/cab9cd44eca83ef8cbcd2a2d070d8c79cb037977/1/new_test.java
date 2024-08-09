@Test
	@SuppressWarnings("unchecked")
	public void testSnapshotState() throws Exception {

		// --------------------------------------------------------------------
		//   prepare fake states
		// --------------------------------------------------------------------

		final HashMap<KafkaTopicPartition, Long> state1 = new HashMap<>();
		state1.put(new KafkaTopicPartition("abc", 13), 16768L);
		state1.put(new KafkaTopicPartition("def", 7), 987654321L);

		final HashMap<KafkaTopicPartition, Long> state2 = new HashMap<>();
		state2.put(new KafkaTopicPartition("abc", 13), 16770L);
		state2.put(new KafkaTopicPartition("def", 7), 987654329L);

		final HashMap<KafkaTopicPartition, Long> state3 = new HashMap<>();
		state3.put(new KafkaTopicPartition("abc", 13), 16780L);
		state3.put(new KafkaTopicPartition("def", 7), 987654377L);

		// --------------------------------------------------------------------
		
		final AbstractFetcher<String, ?> fetcher = mock(AbstractFetcher.class);
		when(fetcher.snapshotCurrentState()).thenReturn(state1, state2, state3);
			
		final LinkedMap pendingOffsetsToCommit = new LinkedMap();
	
		FlinkKafkaConsumerBase<String> consumer = getConsumer(fetcher, pendingOffsetsToCommit, true);
		assertEquals(0, pendingOffsetsToCommit.size());

		OperatorStateStore backend = mock(OperatorStateStore.class);

		TestingListState<Serializable> listState = new TestingListState<>();

		when(backend.getSerializableListState(Matchers.any(String.class))).thenReturn(listState);

		StateInitializationContext initializationContext = mock(StateInitializationContext.class);

		when(initializationContext.getManagedOperatorStateStore()).thenReturn(backend);
		when(initializationContext.isRestored()).thenReturn(false, true, true, true);

		consumer.initializeState(initializationContext);

		// checkpoint 1
		consumer.snapshotState(new StateSnapshotContextSynchronousImpl(138, 138));

		HashMap<KafkaTopicPartition, Long> snapshot1 = new HashMap<>();

		for (Serializable serializable : listState.get()) {
			Tuple2<KafkaTopicPartition, Long> kafkaTopicPartitionLongTuple2 = (Tuple2<KafkaTopicPartition, Long>) serializable;
			snapshot1.put(kafkaTopicPartitionLongTuple2.f0, kafkaTopicPartitionLongTuple2.f1);
		}

		assertEquals(state1, snapshot1);
		assertEquals(1, pendingOffsetsToCommit.size());
		assertEquals(state1, pendingOffsetsToCommit.get(138L));

		// checkpoint 2
		consumer.snapshotState(new StateSnapshotContextSynchronousImpl(140, 140));

		HashMap<KafkaTopicPartition, Long> snapshot2 = new HashMap<>();

		for (Serializable serializable : listState.get()) {
			Tuple2<KafkaTopicPartition, Long> kafkaTopicPartitionLongTuple2 = (Tuple2<KafkaTopicPartition, Long>) serializable;
			snapshot2.put(kafkaTopicPartitionLongTuple2.f0, kafkaTopicPartitionLongTuple2.f1);
		}

		assertEquals(state2, snapshot2);
		assertEquals(2, pendingOffsetsToCommit.size());
		assertEquals(state2, pendingOffsetsToCommit.get(140L));
		
		// ack checkpoint 1
		consumer.notifyCheckpointComplete(138L);
		assertEquals(1, pendingOffsetsToCommit.size());
		assertTrue(pendingOffsetsToCommit.containsKey(140L));

		// checkpoint 3
		consumer.snapshotState(new StateSnapshotContextSynchronousImpl(141, 141));

		HashMap<KafkaTopicPartition, Long> snapshot3 = new HashMap<>();

		for (Serializable serializable : listState.get()) {
			Tuple2<KafkaTopicPartition, Long> kafkaTopicPartitionLongTuple2 = (Tuple2<KafkaTopicPartition, Long>) serializable;
			snapshot3.put(kafkaTopicPartitionLongTuple2.f0, kafkaTopicPartitionLongTuple2.f1);
		}

		assertEquals(state3, snapshot3);
		assertEquals(2, pendingOffsetsToCommit.size());
		assertEquals(state3, pendingOffsetsToCommit.get(141L));
		
		// ack checkpoint 3, subsumes number 2
		consumer.notifyCheckpointComplete(141L);
		assertEquals(0, pendingOffsetsToCommit.size());


		consumer.notifyCheckpointComplete(666); // invalid checkpoint
		assertEquals(0, pendingOffsetsToCommit.size());

		OperatorStateStore operatorStateStore = mock(OperatorStateStore.class);
		listState = new TestingListState<>();
		when(operatorStateStore.getOperatorState(Matchers.any(ListStateDescriptor.class))).thenReturn(listState);

		// create 500 snapshots
		for (int i = 100; i < 600; i++) {
			consumer.snapshotState(new StateSnapshotContextSynchronousImpl(i, i));
			listState.clear();
		}
		assertEquals(FlinkKafkaConsumerBase.MAX_NUM_PENDING_CHECKPOINTS, pendingOffsetsToCommit.size());

		// commit only the second last
		consumer.notifyCheckpointComplete(598);
		assertEquals(1, pendingOffsetsToCommit.size());

		// access invalid checkpoint
		consumer.notifyCheckpointComplete(590);

		// and the last
		consumer.notifyCheckpointComplete(599);
		assertEquals(0, pendingOffsetsToCommit.size());
	}