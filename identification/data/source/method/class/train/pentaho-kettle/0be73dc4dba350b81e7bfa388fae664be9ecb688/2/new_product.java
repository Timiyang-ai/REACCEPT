public boolean folderExists( String foldername ) {
    boolean retval = false;
    Folder dfolder = null;
    try {
      // Open destination folder
      dfolder = getRecursiveFolder( foldername );
      if ( dfolder.exists() ) {
        retval = true;
      }
    } catch ( Exception e ) {
      // Ignore errors
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