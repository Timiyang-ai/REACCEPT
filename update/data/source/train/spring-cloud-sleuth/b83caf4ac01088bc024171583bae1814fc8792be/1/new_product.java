private void shouldCloseSpanUponException(ResponseEntityProvider provider)
			throws IOException, InterruptedException {
		Span span = this.tracer.nextSpan().name("new trace");

		try (Tracer.SpanInScope ws = this.tracer.withSpanInScope(span.start())) {
			provider.get(this);
			Assertions.fail("should throw an exception");
		}
		catch (RuntimeException e) {
		}
		finally {
			span.finish();
		}

		// hystrix commands should finish at this point
		Thread.sleep(200);
		List<zipkin2.Span> spans = this.reporter.getSpans();
		then(spans).hasSize(2);
		then(spans.stream()
				.filter(span1 -> span1.kind() == zipkin2.Span.Kind.CLIENT)
				.findFirst()
				.get().tags()).containsKey("error");
	}