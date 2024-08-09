public void setSelectName( String[] selectName ) {
    resizeSelectFields( selectName.length );
    for ( int i = 0; i < selectFields.length; i++ ) {
      selectFields[i].setName( selectName[i] );
    }
  }