   public void testVLANId() {
      ListPublicIPAddressesOptions options = new ListPublicIPAddressesOptions().VLANId("6");
      assertEquals(ImmutableList.of("6"), options.buildQueryParameters().get("vlanid"));
   }