public void finishSpan(long duration) {
        Span span = state().getCurrentClientSpan();
        if (span == null) return;

        synchronized (span) {
            span.setDuration(duration);
            spanCollector().collect(span);
        }

        state().setCurrentClientSpan(null);
        state().setCurrentClientServiceName(null);
    }