@VisibleForTesting
  void authenticateLoginCredentials() throws Exception {
    KettleClientEnvironment.init();

    if ( client == null ) {
      ClientConfig clientConfig = new DefaultClientConfig();
      clientConfig.getFeatures().put( JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE );
      client = Client.create( clientConfig );
      client.addFilter( new HTTPBasicAuthFilter( username, Encr.decryptPasswordOptionallyEncrypted( password ) ) );
    }

    WebResource resource = client.resource( url + AUTHENTICATION + AdministerSecurityAction.NAME );
    String response = resource.get( String.class );

    if ( !response.equals( "true" ) ) {
      throw new Exception( Messages.getInstance().getString( "REPOSITORY_CLEANUP_UTIL.ERROR_0012.ACCESS_DENIED" ) );
    }
  }