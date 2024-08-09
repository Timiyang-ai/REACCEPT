public Builder localServiceName(String localServiceName) {
      if (localServiceName == null || localServiceName.isEmpty()) {
        throw new IllegalArgumentException(localServiceName + " is not a valid serviceName");
      }
      this.localServiceName = localServiceName.toLowerCase(Locale.ROOT);
      return this;
    }