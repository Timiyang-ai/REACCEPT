@Test
	@Parameters
	public void shouldCloseSpanUponException(ResponseEntityProvider provider)
			throws IOException {
		Span span = this.tracer.createSpan("new trace");
		log.info("Started new span " + span);

		try {
			provider.get(this);
			Assert.fail("should throw an exception");
		}
		catch (RuntimeException e) {
			// SleuthAssertions.then(e).hasRootCauseInstanceOf(IOException.class);
		}

		then(ExceptionUtils.getLastException()).isNull();
		then(this.tracer.getCurrentSpan()).isEqualTo(span);
		this.tracer.close(span);
		then(ExceptionUtils.getLastException()).isNull();
		then(this.capture.toString()).doesNotContain("Tried to detach trace span but it is not the current span");
		then(new ListOfSpans(this.accumulator.getSpans())).hasRpcWithoutSeverSideDueToException();
	}