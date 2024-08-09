@Nullable
  public InternalInstrumented<SocketStats> getSocket(long id) {
    InternalInstrumented<SocketStats> clientSocket = otherSockets.get(id);
    if (clientSocket != null) {
      return clientSocket;
    }
    return getServerSocket(id);
  }