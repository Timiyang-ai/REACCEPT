public void setSelectName( String[] selectName ) {
    for ( int i = 0; ( i < selectFields.length ) && ( i < selectName.length ); i++ ) {
      selectFields[i].setName( selectName[i] );
    }
  }