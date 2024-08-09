public static Object loadFileContentInBinary( ValueMetaInterface metaA, Object dataA ) throws KettleValueException {
    if ( dataA == null ) {
      return null;
    }

    FileObject file = null;
    FileInputStream fis = null;
    try {
      file = KettleVFS.getFileObject( dataA.toString() );
      fis = (FileInputStream) ( (LocalFile) file ).getInputStream();
      int fileSize = (int) file.getContent().getSize();
      byte[] content = Const.createByteArray( fileSize );
      fis.read( content, 0, fileSize );
      return content;
    } catch ( Exception e ) {
      throw new KettleValueException( e );
    } finally {
      try {
        if ( fis != null ) {
          fis.close();
        }
        fis = null;
        if ( file != null ) {
          file.close();
        }
        file = null;
      } catch ( Exception e ) {
        // Ignore
      }
    }
  }