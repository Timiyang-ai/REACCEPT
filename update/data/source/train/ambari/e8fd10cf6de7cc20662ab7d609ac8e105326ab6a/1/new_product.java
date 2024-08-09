public synchronized void createUser(String userName, String password, UserType userType, Boolean active, Boolean
      admin) throws AmbariException {
    // if user type is not provided, assume LOCAL since the default
    // value of user_type in the users table is LOCAL
    if (userType == null) {
      throw new AmbariException("UserType not specified.");
    }

    User existingUser = getAnyUser(userName);
    if (existingUser != null) {
      throw new AmbariException("User " + existingUser.getUserName() + " already exists with type "
          + existingUser.getUserType());
    }

    PrincipalTypeEntity principalTypeEntity = principalTypeDAO.findById(PrincipalTypeEntity.USER_PRINCIPAL_TYPE);
    if (principalTypeEntity == null) {
      principalTypeEntity = new PrincipalTypeEntity();
      principalTypeEntity.setId(PrincipalTypeEntity.USER_PRINCIPAL_TYPE);
      principalTypeEntity.setName(PrincipalTypeEntity.USER_PRINCIPAL_TYPE_NAME);
      principalTypeDAO.create(principalTypeEntity);
    }
    PrincipalEntity principalEntity = new PrincipalEntity();
    principalEntity.setPrincipalType(principalTypeEntity);
    principalDAO.create(principalEntity);

    UserEntity userEntity = new UserEntity();
    userEntity.setUserName(UserName.fromString(userName));
    if (userType == UserType.LOCAL) {
      //passwords should be stored for local users only
      userEntity.setUserPassword(passwordEncoder.encode(password));
    }
    userEntity.setPrincipal(principalEntity);
    if (active != null) {
      userEntity.setActive(active);
    }

    userEntity.setUserType(userType);
    if (userType == UserType.LDAP) {
      userEntity.setLdapUser(true);
    }

    userDAO.create(userEntity);

    if (admin != null && admin) {
      grantAdminPrivilege(userEntity.getUserId());
    }

    // execute user initialization hook if required ()
    hookServiceProvider.get().execute(hookContextFactory.createUserHookContext(userName));
  }