public ListOSTypesOptions OSCategoryId(long OSCategoryId) {
      this.queryParameters.replaceValues("oscategoryid", ImmutableSet.of(OSCategoryId + ""));
      return this;
   }