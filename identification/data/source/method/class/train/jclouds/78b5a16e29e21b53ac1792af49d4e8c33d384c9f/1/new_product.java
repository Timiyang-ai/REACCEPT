@POST
   @Path("/media/action/insertMedia")
   @Produces(MEDIA_PARAMS)
   @Consumes(TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> insertMedia(@EndpointParam URI vmURI,
                                      @BinderParam(BindToXMLPayload.class) MediaInsertOrEjectParams mediaParams);