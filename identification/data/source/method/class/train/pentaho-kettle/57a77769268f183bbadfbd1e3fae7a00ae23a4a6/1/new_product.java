public void setFieldValue( String fieldValue ) {
      Boolean isEmptyAndNullDiffer = ValueMetaBase.convertStringToBoolean(
        Const.NVL( System.getProperty( Const.KETTLE_EMPTY_STRING_DIFFERS_FROM_NULL, "N" ), "N" ) );

      this.fieldValue = fieldValue == null && isEmptyAndNullDiffer ? Const.EMPTY_STRING : fieldValue;
    }