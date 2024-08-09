public void prepare()
      throws RestServiceException {
    if (!isOpen()) {
      nettyMetrics.multipartRequestAlreadyClosedError.inc();
      throw new RestServiceException("Request is closed", RestServiceErrorCode.RequestChannelClosed);
    } else if (!readyForRead) {
      // make sure data is held in memory.
      HttpDataFactory httpDataFactory = new DefaultHttpDataFactory(false);
      HttpPostMultipartRequestDecoder postRequestDecoder =
          new HttpPostMultipartRequestDecoder(httpDataFactory, request);
      try {
        HttpContent httpContent;
        while ((httpContent = rawRequestContents.poll()) != null) {
          try {
            postRequestDecoder.offer(httpContent);
          } finally {
            ReferenceCountUtil.release(httpContent);
          }
        }
        for (InterfaceHttpData part : postRequestDecoder.getBodyHttpDatas()) {
          processPart(part);
        }
        allArgsReadOnly = Collections.unmodifiableMap(allArgs);
        requestContents.add(LastHttpContent.EMPTY_LAST_CONTENT);
        readyForRead = true;
      } catch (HttpPostRequestDecoder.ErrorDataDecoderException e) {
        nettyMetrics.multipartRequestDecodeError.inc();
        throw new RestServiceException("There was an error decoding the request", e,
            RestServiceErrorCode.MalformedRequest);
      } finally {
        postRequestDecoder.destroy();
      }
    }
  }