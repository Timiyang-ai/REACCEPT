public void finishSpan(long duration) {
        Span span = spanAndEndpoint().span();
        if (span == null) return;

        synchronized (span) {
            span.setDuration(duration);
            spanCollector().collect(span);
        }

        spanAndEndpoint().state().setCurrentLocalSpan(null);
    }