@Named("Object:simpleUpload")
   @POST
   @QueryParams(keys = "uploadType", values = "media")
   @Consumes(APPLICATION_JSON)
   @Path("/upload/storage/v1/b/{bucket}/o")
   @MapBinder(UploadBinder.class)
   GCSObject simpleUpload(@PathParam("bucket") String bucketName, @HeaderParam("Content-Type") String contentType,
            @HeaderParam("Content-Length") Long contentLength, @PayloadParam("payload") Payload payload,
            InsertObjectOptions Options);