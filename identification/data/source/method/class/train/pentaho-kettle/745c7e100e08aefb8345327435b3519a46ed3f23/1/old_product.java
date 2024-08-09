public static boolean checkUserInfo( IUser user ) {
    return !isBlank( user.getLogin() ) && !isBlank( user.getName() );
  }