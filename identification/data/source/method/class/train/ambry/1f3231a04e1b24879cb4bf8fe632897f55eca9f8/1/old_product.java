public void prepare()
      throws RestServiceException {
    if (!isOpen()) {
      nettyMetrics.multipartRequestAlreadyClosedError.inc();
      throw new RestServiceException("Request is closed", RestServiceErrorCode.RequestChannelClosed);
    } else if (!readyForRead) {
      // make sure data does *not* go to disk. It should be held in memory.
      HttpDataFactory httpDataFactory = new DefaultHttpDataFactory(false);
      HttpPostMultipartRequestDecoder postRequestDecoder =
          new HttpPostMultipartRequestDecoder(httpDataFactory, request);
      try {
        HttpContent httpContent = rawRequestContents.peek();
        while (httpContent != null) {
          postRequestDecoder.offer(httpContent);
          ReferenceCountUtil.release(rawRequestContents.poll());
          httpContent = rawRequestContents.peek();
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