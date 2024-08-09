@Override
    public void adjustTLSContext(ExtendedMasterSecretExtensionMessage message) {
        if (context.getTalkingConnectionEndType() == ConnectionEndType.SERVER
                || context.getConfig().isEnforceSettings()) {
            context.setIsExtendedMasterSecretExtension(true);
        }
    }