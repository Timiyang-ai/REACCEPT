public boolean handshake(SocketChannel socketChannel, int timeout,
      ByteBuffer peerNetData)
      throws IOException, InterruptedException {

    if (peerNetData.capacity() < engine.getSession().getPacketBufferSize()) {
      if (logger.isDebugEnabled()) {
        logger.debug("Allocating new buffer for SSL handshake");
      }
      this.handshakeBuffer =
          bufferPool.acquireReceiveBuffer(engine.getSession().getPacketBufferSize());
    } else {
      this.handshakeBuffer = peerNetData;
    }
    this.handshakeBuffer.clear();

    ByteBuffer myAppData = ByteBuffer.wrap(new byte[0]);

    if (logger.isDebugEnabled()) {
      logger.debug("Starting TLS handshake with {}.  Timeout is {}ms", socketChannel.socket(),
          timeout);
    }

    long timeoutNanos = -1;
    if (timeout > 0) {
      timeoutNanos = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeout);
    }

    // Begin handshake
    engine.beginHandshake();
    SSLEngineResult.HandshakeStatus status = engine.getHandshakeStatus();
    SSLEngineResult engineResult = null;

    // Process handshaking message
    while (status != FINISHED &&
        status != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
      if (socketChannel.socket().isClosed()) {
        logger.info("Handshake terminated because socket is closed");
        throw new SocketException("handshake terminated - socket is closed");
      }

      if (timeoutNanos > 0) {
        if (timeoutNanos < System.nanoTime()) {
          logger.info("TLS handshake is timing out");
          throw new SocketTimeoutException("handshake timed out");
        }
      }

      switch (status) {
        case NEED_UNWRAP:
          // Receive handshaking data from peer
          int dataRead = socketChannel.read(handshakeBuffer);

          // Process incoming handshaking data
          handshakeBuffer.flip();
          engineResult = engine.unwrap(handshakeBuffer, peerAppData);
          handshakeBuffer.compact();
          status = engineResult.getHandshakeStatus();

          // if we're not finished, there's nothing to process and no data was read let's hang out
          // for a little
          if (peerAppData.remaining() == 0 && dataRead == 0 && status == NEED_UNWRAP) {
            Thread.sleep(10);
          }

          if (engineResult.getStatus() == BUFFER_OVERFLOW) {
            peerAppData =
                expandWriteBuffer(TRACKED_RECEIVER, peerAppData, peerAppData.capacity() * 2);
          }
          break;

        case NEED_WRAP:
          // Empty the local network packet buffer.
          myNetData.clear();

          // Generate handshaking data
          engineResult = engine.wrap(myAppData, myNetData);
          status = engineResult.getHandshakeStatus();

          // Check status
          switch (engineResult.getStatus()) {
            case BUFFER_OVERFLOW:
              myNetData =
                  expandWriteBuffer(TRACKED_SENDER, myNetData,
                      myNetData.capacity() * 2);
              break;
            case OK:
              myNetData.flip();
              // Send the handshaking data to peer
              while (myNetData.hasRemaining()) {
                socketChannel.write(myNetData);
              }
              break;
            case CLOSED:
              break;
            default:
              logger.info("handshake terminated with illegal state due to {}", status);
              throw new IllegalStateException(
                  "Unknown SSLEngineResult status: " + engineResult.getStatus());
          }
          break;
        case NEED_TASK:
          // Handle blocking tasks
          handleBlockingTasks();
          status = engine.getHandshakeStatus();
          break;
        default:
          logger.info("handshake terminated with illegal state due to {}", status);
          throw new IllegalStateException("Unknown SSL Handshake state: " + status);
      }
      Thread.sleep(10);
    }
    if (status != FINISHED) {
      logger.info("handshake terminated with exception due to {}", status);
      throw new SSLHandshakeException("SSL Handshake terminated with status " + status);
    }
    if (logger.isDebugEnabled()) {
      if (engineResult != null) {
        logger.debug("TLS handshake successful.  result={} and handshakeResult={}",
            engineResult.getStatus(), engine.getHandshakeStatus());
      } else {
        logger.debug("TLS handshake successful.  handshakeResult={}",
            engine.getHandshakeStatus());
      }
    }
    return true;
  }