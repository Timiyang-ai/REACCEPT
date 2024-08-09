public boolean isSmdRevoked(String smdId, DateTime now) {
    DateTime revoked = revokes.get(checkNotNull(smdId, "smdId"));
    if (revoked == null) {
      return false;
    }
    return isBeforeOrAt(revoked, now);
  }