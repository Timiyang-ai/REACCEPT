public void finishSpan() {
        Span span = spanAndEndpoint().span();
        if (span == null) return;

        long duration = Math.max(1L, clock().currentTimeMicroseconds() - span.getTimestamp());
        internalFinishSpan(span, duration);
    }