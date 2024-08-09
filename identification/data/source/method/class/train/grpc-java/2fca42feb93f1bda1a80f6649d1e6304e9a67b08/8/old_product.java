@Nullable
  public Instrumented<SocketStats> getSocket(long id) {
    Instrumented<SocketStats> clientSocket = otherSockets.get(id);
    if (clientSocket != null) {
      return clientSocket;
    }
    return getServerSocket(id);
  }