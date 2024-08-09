public static void callExtensionPoint( final LogChannelInterface log, final String id, final Object object )
    throws KettleException {
    ExtensionPointInterface extensionPoint = ExtensionPointMap.getInstance().get( id );
    if ( extensionPoint != null ) {
      extensionPoint.callExtensionPoint( log, object );
    }
  }