@Test
	public void testAddEvent()
	{
		final Instant eventTime = Instant.now();

		huTraceRepository.addEvent(HUTraceEvent.builder()
				.eventTime(eventTime)
				.topLevelHuId(2)
				.vhuId(12).type(HUTraceType.TRANSFORMATION).build());

		final HUTraceSpecification query = HUTraceSpecification.builder()
				.vhuId(12)
				.recursionMode(RecursionMode.NONE)
				.build();

		final List<HUTraceEvent> result = huTraceRepository.query(query);
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getVhuId(), is(12));

		// add an equal event, again
		huTraceRepository.addEvent(HUTraceEvent.builder()
				.eventTime(eventTime)
				.topLevelHuId(2)
				.vhuId(12).type(HUTraceType.TRANSFORMATION).build());

		final List<HUTraceEvent> result2 = huTraceRepository.query(query);
		assertThat(result2.size(), is(1)); // still just one..
	}