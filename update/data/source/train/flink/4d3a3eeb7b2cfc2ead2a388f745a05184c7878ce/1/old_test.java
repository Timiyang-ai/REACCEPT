@Test
	@SuppressWarnings("unchecked")
	public void testUnregisterAllTimeouts() throws Exception {
		// Prepare all instances.
		ScheduledExecutorService scheduledExecutorService = mock(ScheduledExecutorService.class);
		ScheduledFuture scheduledFuture = mock(ScheduledFuture.class);
		when(scheduledExecutorService.schedule(any(Runnable.class), anyLong(), any(TimeUnit.class)))
			.thenReturn(scheduledFuture);
		TimerService<AllocationID> timerService = new TimerService<>(scheduledExecutorService);
		TimeoutListener<AllocationID> listener = mock(TimeoutListener.class);

		timerService.start(listener);

		// Invoke register and unregister.
		timerService.registerTimeout(new AllocationID(), 10, TimeUnit.SECONDS);
		timerService.registerTimeout(new AllocationID(), 10, TimeUnit.SECONDS);

		timerService.unregisterAllTimeouts();

		// Verify.
		Map<?, ?> timeouts = (Map<?, ?>) Whitebox.getInternalState(timerService, "timeouts");
		assertTrue(timeouts.isEmpty());
		verify(scheduledFuture, times(2)).cancel(true);
	}