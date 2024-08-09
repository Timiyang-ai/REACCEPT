public void setSelectRename( String[] selectRename ) {
    if ( selectRename.length > selectFields.length ) {
      resizeSelectFields( selectRename.length );
    }
    for ( int i = 0; i < selectFields.length; i++ ) {
      if ( i < selectRename.length ) {
        selectFields[i].setRename( selectRename[i] );
      } else {
        selectFields[i].setRename( null );
      }
    }
  }