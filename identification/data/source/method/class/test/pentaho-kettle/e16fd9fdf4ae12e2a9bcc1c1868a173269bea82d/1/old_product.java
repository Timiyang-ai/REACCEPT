public void setSelectLength( int[] selectLength ) {
    if ( selectLength.length > selectFields.length ) {
      resizeSelectFields( selectLength.length );
    }
    for ( int i = 0; i < selectFields.length; i++ ) {
      if ( i < selectLength.length ) {
        selectFields[i].setLength( selectLength[i] );
      } else {
        selectFields[i].setLength( -2 );
      }
    }
  }