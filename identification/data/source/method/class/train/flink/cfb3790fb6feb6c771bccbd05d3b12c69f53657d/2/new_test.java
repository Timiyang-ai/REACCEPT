@Test
	public void testShutDown() throws Exception {
		ExecutionVertex[] vertices = new ExecutionVertex[] {
				mockExecutionVertex(new ExecutionAttemptID(), ExecutionState.RUNNING, true),
		};

		List<Future<StackTraceSample>> sampleFutures = new ArrayList<>();

		// Trigger
		sampleFutures.add(coord.triggerStackTraceSample(
				vertices, 1, Time.milliseconds(100L), 0));

		sampleFutures.add(coord.triggerStackTraceSample(
				vertices, 1, Time.milliseconds(100L), 0));

		for (Future<StackTraceSample> future : sampleFutures) {
			assertFalse(future.isDone());
		}

		// Shut down
		coord.shutDown();

		// Verify all completed
		for (Future<StackTraceSample> future : sampleFutures) {
			assertTrue(future.isDone());
		}

		// Verify new trigger returns failed future
		Future<StackTraceSample> future = coord.triggerStackTraceSample(
				vertices, 1, Time.milliseconds(100L), 0);

		assertTrue(future.isDone());

		try {
			future.get();
			fail("Expected exception.");
		} catch (ExecutionException e) {
			// we expected an exception here :-)
		}

	}