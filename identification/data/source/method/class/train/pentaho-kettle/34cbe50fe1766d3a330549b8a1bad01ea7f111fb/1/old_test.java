  @Test
  public void createLdapProtocol() throws Exception {
    String ldapVariable = "${ldap_protocol_variable}";
    String ldap = "LDAP";
    String host = "localhost";

    LdapProtocolFactory ldapProtocolFactory = new LdapProtocolFactory( Mockito.mock( LogChannelInterface.class ) );
    VariableSpace variableSpace = Mockito.mock( VariableSpace.class );
    LdapMeta meta = Mockito.mock( LdapMeta.class );
    Mockito.doReturn( ldapVariable ).when( meta ).getProtocol();
    Mockito.doReturn( ldap ).when( variableSpace ).environmentSubstitute( ldapVariable );
    Mockito.doReturn( host ).when( meta ).getHost();
    Mockito.doReturn( host ).when( variableSpace ).environmentSubstitute( host );

    ldapProtocolFactory.createLdapProtocol( variableSpace, meta, Collections.emptyList() );
    Mockito.verify( variableSpace, Mockito.times( 1 ) ).environmentSubstitute( ldapVariable );

  }