@Test
	public void testIsEvent() throws Exception {
		AbstractEvent[] events = {
			EndOfPartitionEvent.INSTANCE,
			EndOfSuperstepEvent.INSTANCE,
			new CheckpointBarrier(1678L, 4623784L, CheckpointOptions.forCheckpointWithDefaultLocation()),
			new TestTaskEvent(Math.random(), 12361231273L),
			new CancelCheckpointMarker(287087987329842L)
		};

		Class[] expectedClasses = Arrays.stream(events)
			.map(AbstractEvent::getClass)
			.toArray(Class[]::new);

		for (AbstractEvent evt : events) {
			for (Class<?> expectedClass: expectedClasses) {
				if (expectedClass.equals(TestTaskEvent.class)) {
					try {
						checkIsEvent(evt, expectedClass);
						fail("This should fail");
					}
					catch (UnsupportedOperationException ex) {
						// expected
					}
				}
				else if (evt.getClass().equals(expectedClass)) {
					assertTrue(checkIsEvent(evt, expectedClass));
				} else {
					assertFalse(checkIsEvent(evt, expectedClass));
				}
			}
		}
	}