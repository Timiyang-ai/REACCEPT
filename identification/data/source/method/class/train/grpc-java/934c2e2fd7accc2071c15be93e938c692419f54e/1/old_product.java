Channel wrapChannel(Channel channel) {
    return ClientInterceptors.intercept(channel, binaryLogShim);
  }