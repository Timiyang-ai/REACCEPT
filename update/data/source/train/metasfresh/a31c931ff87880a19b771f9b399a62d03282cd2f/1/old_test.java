@Test
	public void testAddEvent()
	{
		final Instant eventTime = Instant.now();

		huTraceRepository.addEvent(HUTraceEvent.builder()
				.eventTime(eventTime)
				.huId(12).type(HUTraceType.TRANSFORMATION).build());

		final HUTraceSpecification query = HUTraceSpecification.builder()
				.huId(12)
				.recursionMode(RecursionMode.NONE)
				.build();

		final List<HUTraceEvent> result = huTraceRepository.query(query);
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getHuId(), is(12));

		// add an equal event, again
		huTraceRepository.addEvent(HUTraceEvent.builder()
				.eventTime(eventTime)
				.huId(12).type(HUTraceType.TRANSFORMATION).build());

		final List<HUTraceEvent> result2 = huTraceRepository.query(query);
		assertThat(result2.size(), is(1)); // still just one..
	}