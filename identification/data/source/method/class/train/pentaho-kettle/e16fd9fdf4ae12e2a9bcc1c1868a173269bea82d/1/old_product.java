public void setSelectPrecision( int[] selectPrecision ) {
    for ( int i = 0; ( i < selectFields.length ) && ( i < selectPrecision.length ); i++ ) {
      selectFields[i].setPrecision( selectPrecision[i] );
    }
  }