public ListPublicIPAddressesOptions VLANId(String VLANId) {
      this.queryParameters.replaceValues("vlanid", ImmutableSet.of(VLANId + ""));
      return this;

   }