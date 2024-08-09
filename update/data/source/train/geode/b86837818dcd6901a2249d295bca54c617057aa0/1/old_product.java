@Override
  public void invalidate() {
    nativeSession.invalidate();
    manager.destroySession(id);
    isValid = false;
  }