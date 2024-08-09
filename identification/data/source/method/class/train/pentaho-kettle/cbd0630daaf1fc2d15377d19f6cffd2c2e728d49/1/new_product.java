public String[] getFields( Field[] fields, boolean excludeNonUpdatableFields ) throws KettleException {
    if ( fields != null ) {
      ArrayList<String> fieldsList = new ArrayList<String>( fields.length );
      for ( Field field : fields ) {
        //Add the name of the field - always
        fieldsList.add( field.getName() );
        //Get the referenced to the field object and for this object get all its field to find possible idLookup fields
        if ( isReferenceField( field ) ) {
          String referenceTo = field.getReferenceTo()[0];
          Field[] referenceObjectFields = this.getObjectFields( referenceTo, excludeNonUpdatableFields );

          for ( Field f : referenceObjectFields ) {
            if ( f.isIdLookup() && !isIdField( f ) ) {
              fieldsList.add( String.format( "%s:%s/%s", referenceTo, f.getName(), field.getRelationshipName() ) );
            }
          }
        }
      }
      return fieldsList.toArray( new String[fieldsList.size()] );
    }
    return null;
  }