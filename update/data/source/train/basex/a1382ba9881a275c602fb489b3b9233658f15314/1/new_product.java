@Deterministic
  @Requires(Permission.NONE)
  public Str id() throws QueryException {
    return Str.get(session().getId());
  }