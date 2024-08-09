@Override
  public void cancel(final Status reason) {
    checkNotNull(reason, "reason");
    boolean delegateToRealStream = true;
    ClientStreamListener listenerToClose = null;
    synchronized (this) {
      // If realStream != null, then either setStream() or cancel() has been called
      if (realStream == null) {
        realStream = NoopClientStream.INSTANCE;
        delegateToRealStream = false;

        // If listener == null, then start() will later call listener with 'error'
        listenerToClose = listener;
        error = reason;
      }
    }
    if (delegateToRealStream) {
      delayOrExecute(new Runnable() {
        @Override
        public void run() {
          realStream.cancel(reason);
        }
      });
    } else {
      if (listenerToClose != null) {
        listenerToClose.closed(reason, new Metadata());
      }
      drainPendingCalls();
    }
  }