public static Agent generateAgent(FuzzerGeneralConfig config, ServerCertificateStructure keypair)
            throws IllegalAgentException {
        switch (config.getAgent()) {
            case AFLAgent.optionName:
                return new AFLAgent(keypair);
            case PINAgent.optionName:
                return new PINAgent(config ,keypair);
            case BlindAgent.optionName:
                return new BlindAgent(keypair);
            default:
                throw new IllegalAgentException("Could not find Agent!");
        }
    }