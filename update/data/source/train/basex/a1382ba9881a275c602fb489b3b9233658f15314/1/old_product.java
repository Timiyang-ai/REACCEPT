@Deterministic
  @Requires(Permission.NONE)
  public String sessionId() throws QueryException {
    return session().getId();
  }