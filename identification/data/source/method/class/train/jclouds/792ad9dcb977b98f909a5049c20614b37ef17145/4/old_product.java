public ListPublicIPAddressesOptions VLANId(long VLANId) {
      this.queryParameters.replaceValues("vlanid", ImmutableSet.of(VLANId + ""));
      return this;

   }