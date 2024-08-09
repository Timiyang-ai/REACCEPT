protected void setRequest(NettyRequest request) {
    if (request != null) {
      if (this.request == null) {
        this.request = request;
      } else {
        throw new IllegalStateException(
            "Request has already been set inside NettyResponseChannel for channel {} " + ctx.channel());
      }
    } else {
      throw new IllegalArgumentException("RestRequest provided is null");
    }
  }