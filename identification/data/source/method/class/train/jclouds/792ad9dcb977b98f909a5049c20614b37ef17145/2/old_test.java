   public void testOSCategoryId() {
      ListOSTypesOptions options = new ListOSTypesOptions().OSCategoryId("6");
      assertEquals(ImmutableList.of("6"), options.buildQueryParameters().get("oscategoryid"));
   }