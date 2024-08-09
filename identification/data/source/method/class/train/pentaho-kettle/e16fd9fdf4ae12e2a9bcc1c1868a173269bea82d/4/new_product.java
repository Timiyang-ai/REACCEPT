public void setSelectPrecision( int[] selectPrecision ) {
    if ( selectPrecision.length > selectFields.length ) {
      resizeSelectFields( selectPrecision.length );
    }
    for ( int i = 0; i < selectFields.length; i++ ) {
      if ( i < selectPrecision.length ) {
        selectFields[i].setPrecision( selectPrecision[i] );
      } else {
        selectFields[i].setPrecision( UNDEFINED );
      }
    }
  }