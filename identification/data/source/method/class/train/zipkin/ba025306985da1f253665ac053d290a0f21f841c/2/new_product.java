public Builder ipv6(byte[] ipv6) {
      if (ipv6 == null) return this;
      checkArgument(ipv6.length == 16, "ipv6 addresses are 16 bytes: " + ipv6.length);
      for (int i = 0; i < 10; i++) { // Embedded IPv4 addresses start with unset 80 bits
        if (ipv6[i] != 0) {
          this.ipv6 = ipv6;
          return this;
        }
      }

      ByteBuffer buf = ByteBuffer.wrap(ipv6, 10, 6);
      short flag = buf.getShort();
      if (flag == 0 || flag == -1) { // IPv4-Compatible or IPv4-Mapped
        int ipv4 = buf.getInt();
        if (flag == 0 && ipv4 == 1) {
          this.ipv6 = ipv6; // ::1 is localhost, not an embedded compat address
        } else {
          this.ipv4 = ipv4;
        }
      } else {
        this.ipv6 = ipv6;
      }
      return this;
    }