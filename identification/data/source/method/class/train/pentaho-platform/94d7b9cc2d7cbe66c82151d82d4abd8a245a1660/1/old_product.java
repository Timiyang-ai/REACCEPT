@Override
  public IActionInvokeStatus invokeAction( final IAction actionBean,
                                           final String actionUser,
                                           final Map<String, Serializable> params ) throws Exception {
    ActionUtil.prepareMap( params );
    // call getStreamProvider, in addition to creating the provider, this method also adds values to the map that
    // serialize the stream provider and make it possible to deserialize and recreate it for remote execution.
    getStreamProvider( params );
    return invokeActionImpl( actionBean, actionUser, params );
  }