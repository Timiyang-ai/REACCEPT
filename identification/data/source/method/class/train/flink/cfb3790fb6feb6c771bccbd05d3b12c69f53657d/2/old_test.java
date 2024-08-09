@Test
	public void testShutDown() throws Exception {
		ExecutionVertex[] vertices = new ExecutionVertex[] {
				mockExecutionVertex(new ExecutionAttemptID(), ExecutionState.RUNNING, true),
		};

		List<Future<StackTraceSample>> sampleFutures = new ArrayList<>();

		// Trigger
		sampleFutures.add(coord.triggerStackTraceSample(
				vertices, 1, new FiniteDuration(100, TimeUnit.MILLISECONDS), 0));

		sampleFutures.add(coord.triggerStackTraceSample(
				vertices, 1, new FiniteDuration(100, TimeUnit.MILLISECONDS), 0));

		for (Future<StackTraceSample> future : sampleFutures) {
			assertFalse(future.isCompleted());
		}

		// Shut down
		coord.shutDown();

		// Verify all completed
		for (Future<StackTraceSample> future : sampleFutures) {
			assertTrue(future.isCompleted());
		}

		// Verify new trigger returns failed future
		Future<StackTraceSample> future = coord.triggerStackTraceSample(
				vertices, 1, new FiniteDuration(100, TimeUnit.MILLISECONDS), 0);

		assertTrue(future.isCompleted());
		assertTrue(future.failed().isCompleted());
	}