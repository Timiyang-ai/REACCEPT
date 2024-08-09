@Test
	public void testCancelStackTraceSample() throws Exception {
		ExecutionVertex[] vertices = new ExecutionVertex[] {
				mockExecutionVertex(new ExecutionAttemptID(), ExecutionState.RUNNING, true),
		};

		Future<StackTraceSample> sampleFuture = coord.triggerStackTraceSample(
				vertices, 1, new FiniteDuration(100, TimeUnit.MILLISECONDS), 0);

		assertFalse(sampleFuture.isCompleted());

		// Cancel
		coord.cancelStackTraceSample(0, null);

		// Verify completed
		assertTrue(sampleFuture.isCompleted());

		// Verify no more pending samples
		assertEquals(0, coord.getNumberOfPendingSamples());
	}