protected static boolean isMissingKey( final String string ) {
    return string == null || ( string.trim().startsWith( "!" ) && string.trim().endsWith( "!" )
      && !string.trim().equals( "!" ) );
  }