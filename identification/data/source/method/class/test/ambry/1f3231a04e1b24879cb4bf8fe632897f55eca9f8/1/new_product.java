@Override
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
        HttpContent httpContent = rawRequestContents.poll();
        while (httpContent != null) {
          try {
            // if the request is also an instance of HttpContent, the HttpPostMultipartRequestDecoder does the offer
            // automatically at the time of construction. We should not add it again.
            if (httpContent != request) {
              postRequestDecoder.offer(httpContent);
            }
          } finally {
            ReferenceCountUtil.release(httpContent);
          }
          httpContent = rawRequestContents.poll();
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