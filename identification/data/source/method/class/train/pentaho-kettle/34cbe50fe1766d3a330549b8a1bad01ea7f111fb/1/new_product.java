public LdapProtocol createLdapProtocol( VariableSpace variableSpace, LdapMeta meta,
    Collection<String> binaryAttributes ) throws KettleException {
    String connectionType = variableSpace.environmentSubstitute( meta.getProtocol() );

    synchronized ( protocols ) {
      for ( Class<? extends LdapProtocol> protocol : protocols ) {
        if ( getName( protocol ).equals( connectionType ) ) {
          try {
            return protocol.getConstructor(
              LogChannelInterface.class,
              VariableSpace.class,
              LdapMeta.class,
              Collection.class ).newInstance( log, variableSpace, meta, binaryAttributes );
          } catch ( Exception e ) {
            throw new KettleException( e );
          }
        }
      }
    }
    return null;
  }