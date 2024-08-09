public boolean isSmdRevoked(String smdId, DateTime now) {
    DateTime revoked = revokes.get(checkNotNull(smdId, "smdId"));
    return revoked != null && isBeforeOrAt(revoked, now);
  }