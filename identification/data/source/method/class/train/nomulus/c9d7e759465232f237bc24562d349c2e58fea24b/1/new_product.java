boolean wereRegistrarsModified() {
    Cursor cursor = ofy().load().key(Cursor.createGlobalKey(SYNC_REGISTRAR_SHEET)).now();
    DateTime lastUpdateTime = (cursor == null) ? START_OF_TIME : cursor.getCursorTime();
    for (Registrar registrar : Registrar.loadAllCached()) {
      if (DateTimeUtils.isAtOrAfter(registrar.getLastUpdateTime(), lastUpdateTime)) {
        return true;
      }
    }
    return false;
  }