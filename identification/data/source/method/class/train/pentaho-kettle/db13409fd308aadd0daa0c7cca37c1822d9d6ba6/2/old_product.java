public static String getFileEncoding( ValueMetaInterface metaA, Object dataA ) throws KettleValueException {
    if ( dataA == null ) {
      return null;
    }
    try {
      return CharsetToolkit.guessEncodingName( new File( metaA.getString( dataA ) ) );
    } catch ( Exception e ) {
      throw new KettleValueException( e );
    }
  }