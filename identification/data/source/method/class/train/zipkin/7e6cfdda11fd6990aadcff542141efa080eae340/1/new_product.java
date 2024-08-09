public Builder localEndpoint(@Nullable Endpoint localEndpoint) {
      if (EMPTY_ENDPOINT.equals(localEndpoint)) localEndpoint = null;
      this.localEndpoint = localEndpoint;
      return this;
    }