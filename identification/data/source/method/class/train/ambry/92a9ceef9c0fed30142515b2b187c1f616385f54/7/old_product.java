@Override
  public int write(ByteBuffer src)
      throws ClosedChannelException {
    if (!src.hasArray()) {
      throw new IllegalArgumentException(
          "NettyResponseChannel does not work with ByteBuffers that are not backed by " + "byte arrrays");
    }
    return addToResponseBody(src);
  }