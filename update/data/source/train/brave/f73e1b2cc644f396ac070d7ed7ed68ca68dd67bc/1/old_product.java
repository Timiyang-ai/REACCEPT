public void finishSpan() {
        long endTick = System.nanoTime();

        Span span = spanAndEndpoint().span();
        if (span == null) return;

        Long startTick;
        synchronized (span) {
            startTick = span.startTick;
        }
        final long duration;
        if (startTick != null) {
            duration = Math.max(1L, (endTick - startTick) / 1000L);
        } else {
            duration = Math.max(1L, clock().currentTimeMicroseconds() - span.getTimestamp());
        }
        internalFinishSpan(span, duration);
    }