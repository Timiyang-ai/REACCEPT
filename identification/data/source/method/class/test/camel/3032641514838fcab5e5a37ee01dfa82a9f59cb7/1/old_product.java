public static void withKerberosConfiguration(Configuration configuration, String kerberosConfigFileLocation) {
        setKerberosConfigFile(kerberosConfigFileLocation);
        configuration.set(AUTHENTICATION_MODE, "kerberos");

    }