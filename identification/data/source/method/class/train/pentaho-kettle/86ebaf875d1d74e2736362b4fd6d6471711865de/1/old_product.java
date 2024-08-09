private void writeToFile( FileObject fileObject, String backupFileName ) throws IOException, KettleException {
    OutputStream outputStream = null;
    PrintStream out = null;
    try {
      outputStream = initOutputStreamUsingKettleVFS( fileObject );

      out = new PrintStream( outputStream );

      out.print( XMLHandler.getXMLHeader( Const.XML_ENCODING ) );
      out.println( "<" + XML_TAG + ">" );

      Collection<SharedObjectInterface> collection = objectsMap.values();
      for ( SharedObjectInterface sharedObject : collection ) {
        String xmlContent = sharedObject.getXML();
        out.println( xmlContent );
      }
      out.println( "</" + XML_TAG + ">" );
    } catch ( Exception e ) {
      // restore file if something wrong
      boolean isRestored = false;
      if ( backupFileName != null ) {
        restoreFileFromBackup( backupFileName );
        isRestored = true;
      }
      throw new KettleException(
          BaseMessages.getString( PKG, "SharedOjects.WriteToFile.ErrorWritingFile", isRestored ), e );
    } finally {
      if ( out != null ) {
        out.flush();
      }
      if ( out != null ) {
        out.close();
      }
      if ( out != null ) {
        outputStream.close();
      }
    }
  }