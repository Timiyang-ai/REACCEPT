static protected Capabilities getGrblStatusCapabilities(final double version, final Character letter) {
        Capabilities ret = new Capabilities();
        ret.addCapability(CapabilitiesConstants.JOGGING);

        // Check if real time commands are enabled.
        if (version==0.8 && (letter != null) && (letter >= 'c')) {
            ret.addCapability(GrblCapabilitiesConstants.REAL_TIME);
        } else if (version >= 0.9) {
            ret.addCapability(GrblCapabilitiesConstants.REAL_TIME);
        }

        // Check for V1.x features
        if (version >= 1.1) {
            ret.addCapability(GrblCapabilitiesConstants.REAL_TIME);

            // GRBL 1.1
            ret.addCapability(GrblCapabilitiesConstants.V1_FORMAT);
            ret.addCapability(CapabilitiesConstants.OVERRIDES);
            ret.addCapability(GrblCapabilitiesConstants.HARDWARE_JOGGING);
            ret.addCapability(CapabilitiesConstants.CONTINUOUS_JOGGING);
        }

        return ret;
    }