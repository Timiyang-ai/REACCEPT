public static void withKerberosConfiguration(Configuration configuration, HdfsConfiguration endpointConfig) {
        setKerberosConfigFile(endpointConfig.getKerberosConfigFileLocation());
        configuration.set(AUTHENTICATION_MODE, "kerberos");

    }