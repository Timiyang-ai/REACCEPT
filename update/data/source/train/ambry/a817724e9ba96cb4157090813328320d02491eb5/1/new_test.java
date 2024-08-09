@Test
  public void processResponseTest() throws Exception {
    RestRequest restRequest = createRestRequest(RestMethod.GET, "/", null);
    //rest request being null
    TestUtils.assertException(IllegalArgumentException.class,
        () -> securityService.processResponse(null, new MockRestResponseChannel(), DEFAULT_INFO).get(), null);

    //restResponseChannel being null
    TestUtils.assertException(IllegalArgumentException.class,
        () -> securityService.processResponse(restRequest, null, DEFAULT_INFO).get(), null);

    //blob info being null
    TestUtils.assertException(IllegalArgumentException.class,
        () -> securityService.processResponse(restRequest, new MockRestResponseChannel(), null).get(), null);

    // without callbacks
    RestMethod[] methods = new RestMethod[]{RestMethod.POST, RestMethod.GET, RestMethod.HEAD};
    for (RestMethod restMethod : methods) {
      securityService.processResponse(createRestRequest(restMethod, "/", null), new MockRestResponseChannel(),
          DEFAULT_INFO).get();
    }

    // with callbacks
    // for unsupported methods
    methods = new RestMethod[]{RestMethod.DELETE};
    for (RestMethod restMethod : methods) {
      testExceptionCasesProcessResponse(restMethod, new MockRestResponseChannel(), DEFAULT_INFO,
          RestServiceErrorCode.InternalServerError);
    }

    // HEAD
    // normal
    testHeadBlobWithVariousRanges(DEFAULT_INFO);
    // with no owner id
    BlobInfo blobInfo =
        new BlobInfo(new BlobProperties(100, SERVICE_ID, null, "image/gif", false, Utils.Infinite_Time), null);
    testHeadBlobWithVariousRanges(blobInfo);
    // with no content type
    blobInfo = new BlobInfo(new BlobProperties(100, SERVICE_ID, OWNER_ID, null, false, Utils.Infinite_Time), null);
    testHeadBlobWithVariousRanges(blobInfo);
    // with a TTL
    blobInfo = new BlobInfo(new BlobProperties(100, SERVICE_ID, OWNER_ID, "image/gif", false, 10000), null);
    testHeadBlobWithVariousRanges(blobInfo);

    // GET BlobInfo
    testGetSubResource(RestUtils.SubResource.BlobInfo);
    // GET UserMetadata
    testGetSubResource(RestUtils.SubResource.UserMetadata);

    // POST
    testPostBlob();

    // GET Blob
    // less than chunk threshold size
    blobInfo = new BlobInfo(
        new BlobProperties(FRONTEND_CONFIG.frontendChunkedGetResponseThresholdInBytes - 1, SERVICE_ID, OWNER_ID,
            "image/gif", false, 10000), null);
    testGetBlobWithVariousRanges(blobInfo);
    // == chunk threshold size
    blobInfo = new BlobInfo(
        new BlobProperties(FRONTEND_CONFIG.frontendChunkedGetResponseThresholdInBytes, SERVICE_ID, OWNER_ID,
            "image/gif", false, 10000), null);
    testGetBlobWithVariousRanges(blobInfo);
    // more than chunk threshold size
    blobInfo = new BlobInfo(
        new BlobProperties(FRONTEND_CONFIG.frontendChunkedGetResponseThresholdInBytes * 2, SERVICE_ID, OWNER_ID,
            "image/gif", false, 10000), null);
    testGetBlobWithVariousRanges(blobInfo);
    // Get blob with content type null
    blobInfo = new BlobInfo(new BlobProperties(100, SERVICE_ID, OWNER_ID, null, true, 10000), null);
    testGetBlobWithVariousRanges(blobInfo);
    // Get blob for a private blob
    blobInfo =
        new BlobInfo(new BlobProperties(100, SERVICE_ID, OWNER_ID, "image/gif", false, Utils.Infinite_Time), null);
    testGetBlobWithVariousRanges(blobInfo);
    // not modified response
    // > creation time (in secs).
    testGetNotModifiedBlob(blobInfo, blobInfo.getBlobProperties().getCreationTimeInMs() + 1000);
    // == creation time
    testGetNotModifiedBlob(blobInfo, blobInfo.getBlobProperties().getCreationTimeInMs());
    // < creation time (in secs)
    testGetNotModifiedBlob(blobInfo, blobInfo.getBlobProperties().getCreationTimeInMs() - 1000);

    // Get blob for a public blob with content type as "text/html"
    blobInfo = new BlobInfo(new BlobProperties(100, SERVICE_ID, OWNER_ID, "text/html", true, 10000), null);
    testGetBlobWithVariousRanges(blobInfo);
    // not modified response
    // > creation time (in secs).
    testGetNotModifiedBlob(DEFAULT_INFO, DEFAULT_INFO.getBlobProperties().getCreationTimeInMs() + 1000);
    // == creation time
    testGetNotModifiedBlob(DEFAULT_INFO, DEFAULT_INFO.getBlobProperties().getCreationTimeInMs());
    // < creation time (in secs)
    testGetNotModifiedBlob(DEFAULT_INFO, DEFAULT_INFO.getBlobProperties().getCreationTimeInMs() - 1000);

    // bad rest response channel
    testExceptionCasesProcessResponse(RestMethod.HEAD, new BadRestResponseChannel(), blobInfo,
        RestServiceErrorCode.InternalServerError);
    testExceptionCasesProcessResponse(RestMethod.GET, new BadRestResponseChannel(), blobInfo,
        RestServiceErrorCode.InternalServerError);
    testExceptionCasesProcessResponse(RestMethod.POST, new BadRestResponseChannel(), blobInfo,
        RestServiceErrorCode.InternalServerError);

    // security service closed
    securityService.close();
    methods = new RestMethod[]{RestMethod.POST, RestMethod.GET, RestMethod.DELETE, RestMethod.HEAD};
    for (RestMethod restMethod : methods) {
      testExceptionCasesProcessResponse(restMethod, new MockRestResponseChannel(), blobInfo,
          RestServiceErrorCode.ServiceUnavailable);
    }
  }