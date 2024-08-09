@Deprecated
    public Builder reporter(zipkin.reporter.Reporter<zipkin.Span> reporter) {
      delegate.reporter(reporter);
      return this;
    }