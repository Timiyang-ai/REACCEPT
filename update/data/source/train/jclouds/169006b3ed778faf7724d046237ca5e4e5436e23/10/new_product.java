@GET
   @Consumes(VM)
   @JAXBResponseParser
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   ListenableFuture<Vm> get(@EndpointParam(parser = VmURNToHref.class) String vmUrn);