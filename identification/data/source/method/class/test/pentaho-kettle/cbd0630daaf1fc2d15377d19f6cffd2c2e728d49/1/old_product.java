public String[] getFields( Field[] fields ) throws KettleException {
    if ( fields != null ) {
      int nrFields = fields.length;
      String[] fieldsMapp = new String[nrFields];

      for ( int i = 0; i < nrFields; i++ ) {
        Field field = fields[i];

        if ( field.getRelationshipName() != null ) {
          fieldsMapp[i] = field.getRelationshipName();
        } else {
          fieldsMapp[i] = field.getName();
        }

      }
      return fieldsMapp;
    }
    return null;
  }