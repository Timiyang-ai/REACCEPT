@Override
  public void clear() {
    Util.assertMainThread();
    if (status == Status.CLEARED) {
      return;
    }
    cancel();
    // Resource must be released before canNotifyStatusChanged is called.
    if (resource != null) {
      releaseResource(resource);
    }
    if (canNotifyStatusChanged()) {
      target.onLoadCleared(getPlaceholderDrawable());
    }
    // Must be after cancel().
    status = Status.CLEARED;
  }