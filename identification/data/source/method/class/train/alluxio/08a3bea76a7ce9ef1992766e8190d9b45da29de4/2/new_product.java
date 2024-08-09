@Nullable
  public String getHost() {
    if (mUri.getAuthority() instanceof SingleMasterAuthority) {
      SingleMasterAuthority authority = (SingleMasterAuthority) mUri.getAuthority();
      return authority.getHost();
    }
    return null;
  }