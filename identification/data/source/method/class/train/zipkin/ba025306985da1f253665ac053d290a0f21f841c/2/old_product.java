public Builder ipv6(byte[] ipv6) {
      if (ipv6 != null) {
        checkArgument(ipv6.length == 16, "ipv6 addresses are 16 bytes: " + ipv6.length);
        this.ipv6 = ipv6;
      }
      return this;
    }