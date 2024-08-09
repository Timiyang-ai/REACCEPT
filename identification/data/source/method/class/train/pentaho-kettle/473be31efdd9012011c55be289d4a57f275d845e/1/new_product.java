public void setDestinationFolder( String foldername, boolean createFolder ) throws KettleException {
    try {
      String[] folderparts = foldername.split( "/" );
      Folder f = this.getStore().getDefaultFolder();
      // Open destination folder
      for ( int i = 0; i < folderparts.length; i++ ) {
        f = f.getFolder( folderparts[i] );
        if ( !f.exists() ) {
          if ( createFolder ) {
            // Create folder
            f.create( Folder.HOLDS_MESSAGES );
          } else {
            throw new KettleException( BaseMessages.getString(PKG, "MailConnection.Error.FolderNotFound", foldername ) );
          }
        }
      }
      this.destinationIMAPFolder = f;
    } catch ( Exception e ) {
      throw new KettleException( e );
    }
  }