private UserDetails createUser(String username, String principal) throws AuthenticationException {
    UserEntity userEntity = users.getUserEntity(username);

    if (userEntity == null) {
      LOG.info("User not found: {} (from {})", username, principal);
      throw new UserNotFoundException(username, String.format("Cannot find user using Kerberos ticket (%s).", principal));
    } else {
      // Check to see if the user is allowed to authenticate using KERBEROS or LDAP
      List<UserAuthenticationEntity> authenticationEntities = userEntity.getAuthenticationEntities();
      boolean hasKerberos = false;

      for (UserAuthenticationEntity entity : authenticationEntities) {
        UserAuthenticationType authenticationType = entity.getAuthenticationType();

        switch (authenticationType) {
          case KERBEROS:
            String key = entity.getAuthenticationKey();
            if (StringUtils.isEmpty(key) || key.equals(username)) {
              LOG.trace("Found KERBEROS authentication method for {} where no principal was set. Fixing...", username);
              // Fix this entry so that it contains the relevant principal..
              try {
                users.addKerberosAuthentication(userEntity, principal);
                users.removeAuthentication(userEntity, entity.getUserAuthenticationId());
              } catch (AmbariException e) {
                // This should not lead to an error... if so, log it and ignore.
                LOG.warn(String.format("Failed to create KERBEROS authentication method entry for %s with principal %s: %s", username, principal, e.getLocalizedMessage()), e);
              }
              hasKerberos = true;
            } else if (principal.equalsIgnoreCase(entity.getAuthenticationKey())) {
              LOG.trace("Found KERBEROS authentication method for {} using principal {}", username, principal);
              hasKerberos = true;
            }
            break;
        }

        if (hasKerberos) {
          break;
        }
      }

      // TODO: Determine if KERBEROS users can be automatically added
      if (!hasKerberos) {
        try {
          users.addKerberosAuthentication(userEntity, principal);
          LOG.trace("Added KERBEROS authentication method for {} using principal {}", username, principal);
        } catch (AmbariException e) {
          LOG.error(String.format("Failed to add the KERBEROS authentication method for %s: %s", principal, e.getLocalizedMessage()), e);
        }
      }
    }

    return createUserDetails(userEntity);
  }