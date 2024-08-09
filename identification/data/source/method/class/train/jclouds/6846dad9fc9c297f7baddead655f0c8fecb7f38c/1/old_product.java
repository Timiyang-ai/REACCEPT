@PUT
   @Path("/networkConnectionSection")
   @Produces(NETWORK_CONFIG_SECTION)
   @Consumes(TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyNetworkConnectionSection(@EndpointParam URI vmURI,
                                                         @BinderParam(BindToXMLPayload.class) NetworkConnectionSection section);