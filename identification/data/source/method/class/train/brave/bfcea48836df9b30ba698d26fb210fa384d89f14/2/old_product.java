@Deprecated
    public Builder spanReporter(zipkin.reporter.Reporter<zipkin2.Span> reporter) {
      if (reporter == null) throw new NullPointerException("reporter == null");
      this.reporter = new Reporter<zipkin2.Span>() {
        @Override public void report(zipkin2.Span span) {
          reporter.report(span);
        }

        @Override public String toString() {
          return reporter.toString();
        }
      };
      return this;
    }