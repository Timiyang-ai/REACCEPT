@GET
   @Consumes(VAPP)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<VApp> getVApp(@EndpointParam URI vAppURI);