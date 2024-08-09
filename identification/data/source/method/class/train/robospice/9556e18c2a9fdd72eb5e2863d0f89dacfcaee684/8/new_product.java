public synchronized void shouldStop() {
        try {
            shouldStopAndJoin(DELAY_WAIT_FOR_RUNNER_TO_STOP);
        } catch (InterruptedException e) {
            Ln.e(e, "Exception when joining the runner that was stopping.");
        }
    }