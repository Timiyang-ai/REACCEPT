public Builder remoteEndpoint(@Nullable Endpoint remoteEndpoint) {
      if (EMPTY_ENDPOINT.equals(remoteEndpoint)) remoteEndpoint = null;
      this.remoteEndpoint = remoteEndpoint;
      return this;
    }