public static String checkAuthority(String authority) {
    URI uri = authorityToUri(authority);
    checkArgument(uri.getHost() != null, "No host in authority '%s'", authority);
    checkArgument(uri.getUserInfo() == null,
        "Userinfo must not be present on authority: '%s'", authority);
    return authority;
  }