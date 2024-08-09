public String[] getFields( String objectName ) throws KettleException {
    return getFields( getObjectFields( objectName ) );
  }