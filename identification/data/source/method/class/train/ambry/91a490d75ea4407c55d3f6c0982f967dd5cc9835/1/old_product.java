protected void setRequest(NettyRequest request) {
    if (this.request == null) {
      this.request = request;
    } else {
      throw new IllegalStateException(
          "Request has already been set inside NettyResponseChannel for channel {} " + ctx.channel());
    }
  }