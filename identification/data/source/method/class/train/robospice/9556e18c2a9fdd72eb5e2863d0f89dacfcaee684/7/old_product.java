public synchronized void shouldStop() {
        if (this.runner == null) {
            throw new IllegalStateException("Not started yet");
        }
        Ln.d("SpiceManager stopping.");
        dontNotifyAnyRequestListenersInternal();
        isUnbinding = false;
        unbindFromService(contextWeakReference.get());
        spiceServiceConnection = null;
        this.isStopped = true;
        this.runner.interrupt();
        this.runner = null;
        this.contextWeakReference.clear();
        Ln.d("SpiceManager stopped.");
    }