private void shouldCloseSpanUponException(ResponseEntityProvider provider)
			throws IOException, InterruptedException {
		Span span = this.tracer.createSpan("new trace");

		try {
			provider.get(this);
			Assertions.fail("should throw an exception");
		}
		catch (RuntimeException e) {
		}

		assertThat(ExceptionUtils.getLastException()).isNull();

		then(this.tracer.getCurrentSpan()).isEqualTo(span);
		this.tracer.close(span);
		then(ExceptionUtils.getLastException()).isNull();
		// hystrix commands should finish at this point
		Thread.sleep(200);
		then(this.outputCapture.toString()).doesNotContain("Tried to detach trace span but it is not the current span");
	}