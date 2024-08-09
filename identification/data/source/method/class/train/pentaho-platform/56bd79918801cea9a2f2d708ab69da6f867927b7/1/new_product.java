@GET
  @Path("/authentication-provider")
  @Produces({MediaType.APPLICATION_JSON})
  public AuthenticationProvider getAuthenticationProvider() throws Exception {
    try{
      IConfiguration config = this.systemConfig.getConfiguration("security");
      String provider = config.getProperties().getProperty("provider");
      return new AuthenticationProvider(provider);
    }
    catch (Throwable t) {
      logger.error(Messages.getInstance().getString("SystemResource.GENERAL_ERROR"), t); //$NON-NLS-1$
      throw new Exception(t);
    }
  }