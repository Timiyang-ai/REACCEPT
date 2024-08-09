static protected Capabilities getGrblStatusCapabilities(final double version, final Character letter) {
        Capabilities ret = new Capabilities();

        // Check if real time commands are enabled.
        if (version==0.8 && (letter != null) && (letter >= 'c')) {
            ret.REAL_TIME = true;
        } else if (version >= 0.9) {
            ret.REAL_TIME = true;
        }

        // Check for V1.x features
        if (version >= 1.1) {
            ret.REAL_TIME = true;

            // GRBL 1.1
            ret.V1_FORMAT = true;
            ret.OVERRIDES = true;
        }

        return ret;
    }