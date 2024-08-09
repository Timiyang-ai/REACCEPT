public void setDestinationFolder( String foldername, boolean createFolder ) throws KettleException {
    try {
      // Open destination folder
      this.destinationIMAPFolder = store.getFolder( foldername );
      if ( !this.destinationIMAPFolder.exists() ) {
        if ( createFolder ) {
          // Create folder
          this.destinationIMAPFolder.create( Folder.HOLDS_MESSAGES );
        } else {
          throw new KettleException( BaseMessages.getString(
            PKG, "MailConnection.Error.FolderNotFound", foldername ) );
        }
      }
    } catch ( Exception e ) {
      throw new KettleException( e );
    }
  }