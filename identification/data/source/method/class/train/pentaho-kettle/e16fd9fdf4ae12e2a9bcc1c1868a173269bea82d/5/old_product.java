public void setSelectRename( String[] selectRename ) {
    for ( int i = 0; ( i < selectFields.length ) && ( i < selectRename.length ); i++ ) {
      selectFields[i].setRename( selectRename[i] );
    }
  }