@Test
	public void testIsEvent() throws Exception {
		AbstractEvent[] events = {
			EndOfPartitionEvent.INSTANCE,
			EndOfSuperstepEvent.INSTANCE,
			new CheckpointBarrier(1678L, 4623784L, CheckpointOptions.forCheckpointWithDefaultLocation()),
			new TestTaskEvent(Math.random(), 12361231273L),
			new CancelCheckpointMarker(287087987329842L)
		};

		for (AbstractEvent evt : events) {
			for (AbstractEvent evt2 : events) {
				if (evt == evt2) {
					assertTrue(checkIsEvent(evt, evt2.getClass()));
				} else {
					assertFalse(checkIsEvent(evt, evt2.getClass()));
				}
			}
		}
	}