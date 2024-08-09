  @Test
  public void prepareTest() throws Exception {
    // prepare half baked data
    HttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/");
    HttpPostRequestEncoder encoder = createEncoder(httpRequest, null);
    NettyMultipartRequest request =
        new NettyMultipartRequest(encoder.finalizeRequest(), new MockChannel(), NETTY_METRICS, Collections.emptySet(),
            Long.MAX_VALUE);
    assertTrue("Request channel is not open", request.isOpen());
    // insert random data
    HttpContent httpContent = new DefaultHttpContent(Unpooled.wrappedBuffer(TestUtils.getRandomBytes(10)));
    request.addContent(httpContent);
    // prepare should fail
    try {
      request.prepare();
      fail("Preparing request should have failed");
    } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException e) {
      assertEquals("Reference count is not as expected", 1, httpContent.refCnt());
    } finally {
      closeRequestAndValidate(request);
    }

    // more than one blob part
    HttpHeaders httpHeaders = new DefaultHttpHeaders();
    httpHeaders.set(RestUtils.Headers.BLOB_SIZE, 256);
    InMemoryFile[] files = new InMemoryFile[2];
    files[0] = new InMemoryFile(RestUtils.MultipartPost.BLOB_PART, ByteBuffer.wrap(TestUtils.getRandomBytes(256)));
    files[1] = new InMemoryFile(RestUtils.MultipartPost.BLOB_PART, ByteBuffer.wrap(TestUtils.getRandomBytes(256)));
    request = createRequest(httpHeaders, files);
    assertEquals("Request size does not match", 256, request.getSize());
    try {
      request.prepare();
      fail("Prepare should have failed because there was more than one " + RestUtils.MultipartPost.BLOB_PART);
    } catch (RestServiceException e) {
      assertEquals("Unexpected RestServiceErrorCode", RestServiceErrorCode.BadRequest, e.getErrorCode());
    } finally {
      closeRequestAndValidate(request);
    }

    // more than one part named "part-1"
    files = new InMemoryFile[2];
    files[0] = new InMemoryFile("Part-1", ByteBuffer.wrap(TestUtils.getRandomBytes(256)));
    files[1] = new InMemoryFile("Part-1", ByteBuffer.wrap(TestUtils.getRandomBytes(256)));
    request = createRequest(null, files);
    try {
      request.prepare();
      fail("Prepare should have failed because there was more than one part named Part-1");
    } catch (RestServiceException e) {
      assertEquals("Unexpected RestServiceErrorCode", RestServiceErrorCode.BadRequest, e.getErrorCode());
    } finally {
      closeRequestAndValidate(request);
    }

    // size of blob does not match the advertized size
    httpHeaders = new DefaultHttpHeaders();
    httpHeaders.set(RestUtils.Headers.BLOB_SIZE, 256);
    files = new InMemoryFile[1];
    files[0] = new InMemoryFile(RestUtils.MultipartPost.BLOB_PART, ByteBuffer.wrap(TestUtils.getRandomBytes(128)));
    request = createRequest(httpHeaders, files);
    try {
      request.prepare();
      fail("Prepare should have failed because the size advertised does not match the actual size");
    } catch (RestServiceException e) {
      assertEquals("Unexpected RestServiceErrorCode", RestServiceErrorCode.BadRequest, e.getErrorCode());
    } finally {
      closeRequestAndValidate(request);
    }

    // non fileupload (file attribute present)
    httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/");
    httpRequest.headers().set(RestUtils.Headers.BLOB_SIZE, 256);
    files = new InMemoryFile[1];
    files[0] = new InMemoryFile(RestUtils.MultipartPost.BLOB_PART, ByteBuffer.wrap(TestUtils.getRandomBytes(256)));
    encoder = createEncoder(httpRequest, files);
    encoder.addBodyAttribute("dummyKey", "dummyValue");
    request =
        new NettyMultipartRequest(encoder.finalizeRequest(), new MockChannel(), NETTY_METRICS, Collections.emptySet(),
            Long.MAX_VALUE);
    assertTrue("Request channel is not open", request.isOpen());
    while (!encoder.isEndOfInput()) {
      // Sending null for ctx because the encoder is OK with that.
      request.addContent(encoder.readChunk(PooledByteBufAllocator.DEFAULT));
    }
    try {
      request.prepare();
      fail("Prepare should have failed because there was non fileupload");
    } catch (RestServiceException e) {
      assertEquals("Unexpected RestServiceErrorCode", RestServiceErrorCode.BadRequest, e.getErrorCode());
    } finally {
      closeRequestAndValidate(request);
    }

    // size of blob is not set. Prepare should succeed.
    httpHeaders = new DefaultHttpHeaders();
    files = new InMemoryFile[1];
    files[0] = new InMemoryFile(RestUtils.MultipartPost.BLOB_PART, ByteBuffer.wrap(TestUtils.getRandomBytes(128)));
    request = createRequest(httpHeaders, files);
    try {
      request.prepare();
    } finally {
      closeRequestAndValidate(request);
    }
  }