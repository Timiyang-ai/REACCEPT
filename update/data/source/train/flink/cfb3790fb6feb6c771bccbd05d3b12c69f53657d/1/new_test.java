@Test
	public void testCancelStackTraceSample() throws Exception {
		ExecutionVertex[] vertices = new ExecutionVertex[] {
				mockExecutionVertex(new ExecutionAttemptID(), ExecutionState.RUNNING, true),
		};

		Future<StackTraceSample> sampleFuture = coord.triggerStackTraceSample(
				vertices, 1, Time.milliseconds(100L), 0);

		assertFalse(sampleFuture.isDone());

		// Cancel
		coord.cancelStackTraceSample(0, null);

		// Verify completed
		assertTrue(sampleFuture.isDone());

		// Verify no more pending samples
		assertEquals(0, coord.getNumberOfPendingSamples());
	}