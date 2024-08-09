    @Test
    public void withKerberosConfiguration() {
        // given
        String kerberosConfigFileLocation = CWD.getAbsolutePath() + "/src/test/resources/kerberos/test-kerb5.conf";

        // when
        KerberosConfigurationBuilder.setKerberosConfigFile(kerberosConfigFileLocation);

        // then

    }