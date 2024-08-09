@Deprecated
  public static String getFileEncoding( ValueMetaInterface metaA, Object dataA ) throws KettleValueException {
    String encoding = null;
    try {
      encoding = getFileEncoding( metaA, dataA, true );
    } catch ( KettleFileNotFoundException e ) {
      throw new KettleValueException();
    }
    return encoding;
  }