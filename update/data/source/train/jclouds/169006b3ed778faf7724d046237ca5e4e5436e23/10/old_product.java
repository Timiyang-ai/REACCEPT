@GET
   @Consumes(VM)
   @JAXBResponseParser
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<Vm> getVm(@EndpointParam URI vmURI);