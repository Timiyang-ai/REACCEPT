  @Test
  public void handshake() throws Exception {
    SocketChannel mockChannel = mock(SocketChannel.class);
    when(mockChannel.read(any(ByteBuffer.class))).thenReturn(100, 100, 100, 0);
    Socket mockSocket = mock(Socket.class);
    when(mockChannel.socket()).thenReturn(mockSocket);
    when(mockSocket.isClosed()).thenReturn(false);

    // initial read of handshake status followed by read of handshake status after task execution
    when(mockEngine.getHandshakeStatus()).thenReturn(NEED_UNWRAP, NEED_WRAP);

    // interleaved wraps/unwraps/task-execution
    when(mockEngine.unwrap(any(ByteBuffer.class), any(ByteBuffer.class))).thenReturn(
        new SSLEngineResult(OK, NEED_WRAP, 100, 100),
        new SSLEngineResult(BUFFER_OVERFLOW, NEED_UNWRAP, 0, 0),
        new SSLEngineResult(OK, NEED_TASK, 100, 0));

    when(mockEngine.getDelegatedTask()).thenReturn(() -> {
    }, (Runnable) null);

    when(mockEngine.wrap(any(ByteBuffer.class), any(ByteBuffer.class))).thenReturn(
        new SSLEngineResult(OK, NEED_UNWRAP, 100, 100),
        new SSLEngineResult(BUFFER_OVERFLOW, NEED_WRAP, 0, 0),
        new SSLEngineResult(CLOSED, FINISHED, 100, 0));

    spyNioSslEngine.handshake(mockChannel, 10000, ByteBuffer.allocate(netBufferSize));
    verify(mockEngine, atLeast(2)).getHandshakeStatus();
    verify(mockEngine, times(3)).wrap(any(ByteBuffer.class), any(ByteBuffer.class));
    verify(mockEngine, times(3)).unwrap(any(ByteBuffer.class), any(ByteBuffer.class));
    verify(spyNioSslEngine, times(2)).expandWriteBuffer(any(BufferPool.BufferType.class),
        any(ByteBuffer.class), any(Integer.class));
    verify(spyNioSslEngine, times(1)).handleBlockingTasks();
    verify(mockChannel, times(3)).read(any(ByteBuffer.class));
  }