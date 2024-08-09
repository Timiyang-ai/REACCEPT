public boolean folderExists( String foldername ) {
    boolean retval = false;
    Folder dfolder = null;
    try {
      // Open destination folder
      dfolder = this.store.getFolder( foldername );
      if ( dfolder.exists() ) {
        retval = true;
      }
    } catch ( Exception e ) {
    } finally {
      try {
        if ( dfolder != null ) {
          dfolder.close( false );
        }
      } catch ( Exception e ) { /* Ignore */
      }
    }
    return retval;
  }