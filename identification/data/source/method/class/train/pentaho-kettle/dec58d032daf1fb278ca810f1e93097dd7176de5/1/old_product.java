protected static boolean isMissingKey( final String string ) {
    return StringUtils.isBlank( string ) || ( string.trim().startsWith( "!" ) && string.trim().endsWith( "!" )
      && !string.trim().equals( "!" ) );
  }