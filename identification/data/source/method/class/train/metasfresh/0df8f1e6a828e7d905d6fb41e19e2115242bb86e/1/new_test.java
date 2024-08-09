@Test
	public void testAddEvent()
	{
		final Instant eventTime = Instant.now();

		huTraceRepository.addEvent(HUTraceEvent.builder()
				.orgId(13)
				.eventTime(eventTime)
				.topLevelHuId(2)
				.productId(23)
				.qty(BigDecimal.TEN)
				.vhuStatus(X_M_HU.HUSTATUS_Active)
				.vhuId(12)
				.type(HUTraceType.TRANSFORM_LOAD)
				.build());

		final HUTraceEventQuery query = HUTraceEventQuery.builder()
				.vhuId(12)
				.recursionMode(RecursionMode.NONE)
				.build();

		final List<HUTraceEvent> result = huTraceRepository.query(query);
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getHuTraceEventId().isPresent(), is(true));
		assertThat(result.get(0).getVhuId(), is(12));
		assertThat(result.get(0).getOrgId(), is(13));

		// add an equal event, again
		huTraceRepository.addEvent(HUTraceEvent.builder()
				.orgId(13)
				.eventTime(eventTime)
				.topLevelHuId(2)
				.productId(23)
				.qty(BigDecimal.TEN)
				.vhuStatus(X_M_HU.HUSTATUS_Active)
				.vhuId(12)
				.type(HUTraceType.TRANSFORM_LOAD)
				.build());

		final List<HUTraceEvent> result2 = huTraceRepository.query(query);
		assertThat(result2.size(), is(1)); // still just one..
		assertThat(result.get(0), is(result2.get(0)));
	}