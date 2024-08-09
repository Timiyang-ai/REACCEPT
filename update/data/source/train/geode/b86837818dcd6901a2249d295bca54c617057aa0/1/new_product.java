@Override
  public void invalidate() {
    manager.destroySession(id);
    isValid = false;
  }