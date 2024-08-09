public static void callExtensionPoint( final LogChannelInterface log, final String id, final Object object )
    throws KettleException {
    ExtensionPointMap.getInstance().callExtensionPoint( log, id, object );
  }