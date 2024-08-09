public Builder spanReporter(Reporter<zipkin2.Span> reporter) {
      if (reporter == null) throw new NullPointerException("spanReporter == null");
      this.reporter = reporter;
      return this;
    }