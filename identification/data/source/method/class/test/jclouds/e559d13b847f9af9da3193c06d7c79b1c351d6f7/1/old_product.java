@POST
   @Path("/media/action/insertMedia")
   @Consumes(VCloudDirectorMediaType.TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> insertMedia(@EndpointParam URI vAppURI, @BinderParam(BindToXMLPayload.class) MediaInsertOrEjectParams mediaParams);