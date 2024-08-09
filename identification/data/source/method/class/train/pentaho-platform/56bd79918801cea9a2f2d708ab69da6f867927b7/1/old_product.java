@GET
  @Path("/authentication-provider")
  @Produces({MediaType.APPLICATION_JSON})
  public AuthenticationProvider getAuthenticationProvider() throws Exception {
    try{
      File configFile = new File(PentahoSystem.getApplicationContext().getSolutionPath("system/pentaho-spring-beans.xml"));
      // File configFile = new File("/home/pminutillo/IntelliJProjects/pentaho-platform/extensions/test-res/solution1-no-config/system/pentaho-spring-beans.xml");
      PentahoSpringBeansConfig config = new PentahoSpringBeansConfig(configFile);

      return new AuthenticationProvider(config.getAuthenticationProvider().toString());
    }
    catch (Throwable t) {
      logger.error(Messages.getInstance().getString("SystemResource.GENERAL_ERROR"), t); //$NON-NLS-1$
      throw new Exception(t);
    }
  }