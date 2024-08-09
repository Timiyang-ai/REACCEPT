@Test(groups = "live")
   public void testSimpleUpload() throws IOException {
      long contentLength = 512L;
      ByteSource byteSource = TestUtils.randomByteSource().slice(0, contentLength);
      ByteSourcePayload byteSourcePayload = Payloads.newByteSourcePayload(byteSource);

      PayloadEnclosing payload = new PayloadEnclosingImpl(byteSourcePayload);
      payload.getPayload().getContentMetadata().setContentLength(contentLength);

      this.testPayload = payload;

      InsertObjectOptions options = new InsertObjectOptions().name(UPLOAD_OBJECT_NAME);

      GoogleCloudStorageObject gcsObject = api().simpleUpload(BUCKET_NAME, "text/plain",
               payload.getPayload().getContentMetadata().getContentLength(), payload.getPayload(), options);

      assertNotNull(gcsObject);
      assertEquals(gcsObject.bucket(), BUCKET_NAME);
      assertEquals(gcsObject.name(), UPLOAD_OBJECT_NAME);
   }