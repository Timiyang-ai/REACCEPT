public static Agent generateAgent(FuzzerGeneralConfig config, ServerCertificateStructure keypair, TLSServer server)
            throws IllegalAgentException {
        switch (config.getAgent()) {
            case AFLAgent.optionName:
                return new AFLAgent(keypair, server);
            case PINAgent.optionName:
                return new PINAgent(config, keypair, server);
            case BlindAgent.optionName:
                return new BlindAgent(keypair, server);
            default:
                throw new IllegalAgentException("Could not find Agent!");
        }
    }