public static void callExtensionPoint( final LogChannelInterface log, final String id, final Object object )
    throws KettleException {
    for ( ExtensionPointInterface extensionPoint : ExtensionPointMap.getInstance().get( id ).values() ) {
      extensionPoint.callExtensionPoint( log, object );
    }
  }