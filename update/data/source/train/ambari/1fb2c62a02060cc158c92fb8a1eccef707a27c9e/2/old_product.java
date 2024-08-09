private UserDetails createUser(String username) {
    // Iterate over the ordered user types... when an account for the username/type combination is
    // found, build the related AmbariUserAuthentication instance and return it.  Only the first
    // match matters... this may be an issue and cause some ambiguity in the event multiple user
    // types are specified in the configuration and multiple accounts for the same username, but
    // different types (LOCAL vs LDAP, etc...).
    for (UserType userType : userTypeOrder) {
      org.apache.ambari.server.security.authorization.User user = users.getUser(username, userType);

      if (user != null) {
        Collection<AmbariGrantedAuthority> userAuthorities = users.getUserAuthorities(user.getUserName(), user.getUserType());
        return new User(username, "", userAuthorities);
      }
    }

    String message = String.format("Failed find user account for user with username of %s during Kerberos authentication.", username);
    LOG.warn(message);
    throw new UsernameNotFoundException(message);
  }