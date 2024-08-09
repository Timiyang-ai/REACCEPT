public synchronized boolean isClosed() {
    return journalWriter == null;
  }