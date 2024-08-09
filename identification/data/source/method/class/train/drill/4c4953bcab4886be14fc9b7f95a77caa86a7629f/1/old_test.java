  @Test
  public void test_getByte_on_TINYINT_getsIt() throws InvalidAccessException {
    final SqlAccessor uut1 =
        new TypeConvertingSqlAccessor( new TinyIntStubAccessor( (byte) 127 ) );
    assertThat( uut1.getByte( 0 ), equalTo( (byte) 127 ) );
    final SqlAccessor uut2 =
        new TypeConvertingSqlAccessor( new TinyIntStubAccessor( (byte) -128 ) );
    assertThat( uut2.getByte( 0 ), equalTo( (byte) -128 ) );
  }