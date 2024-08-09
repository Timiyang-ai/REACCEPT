@PUT
   @Produces(VAPP)
   @Consumes(TASK)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<Task> modifyVApp(@EndpointParam URI vAppURI,
                                     @BinderParam(BindToXMLPayload.class) VApp vApp);