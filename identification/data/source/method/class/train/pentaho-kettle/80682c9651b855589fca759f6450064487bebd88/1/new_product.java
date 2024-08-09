@Deprecated
  public static Object loadFileContentInBinary( ValueMetaInterface metaA, Object dataA ) throws KettleValueException {
    Object content = null;
    try {
      content = loadFileContentInBinary( metaA, dataA, true );
    } catch ( KettleFileNotFoundException e ) {
      throw new KettleValueException();
    }
    return content;
  }