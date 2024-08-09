public static boolean checkUserInfo( IUser user ) {
    return !StringUtils.isBlank( user.getLogin() ) && !StringUtils.isBlank( user.getName() );
  }