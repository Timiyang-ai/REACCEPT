public boolean hasAuthority() {
    return !(mUri.getAuthority() instanceof NoAuthority);
  }