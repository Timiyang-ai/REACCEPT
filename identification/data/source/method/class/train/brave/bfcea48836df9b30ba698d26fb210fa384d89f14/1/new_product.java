public Builder spanReporter(Reporter<zipkin2.Span> spanReporter) {
      if (spanReporter == null) throw new NullPointerException("spanReporter == null");
      this.spanReporter = spanReporter;
      return this;
    }