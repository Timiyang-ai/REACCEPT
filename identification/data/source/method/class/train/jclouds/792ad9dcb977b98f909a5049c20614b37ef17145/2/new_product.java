public ListOSTypesOptions OSCategoryId(String OSCategoryId) {
      this.queryParameters.replaceValues("oscategoryid", ImmutableSet.of(OSCategoryId + ""));
      return this;
   }