public void setSelectLength( int[] selectLength ) {
    for ( int i = 0; ( i < selectFields.length ) && ( i < selectLength.length ); i++ ) {
      selectFields[i].setLength( selectLength[i] );
    }
  }