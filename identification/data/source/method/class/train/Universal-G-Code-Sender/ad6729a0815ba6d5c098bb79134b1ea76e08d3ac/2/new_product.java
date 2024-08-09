static protected Capabilities getGrblStatusCapabilities(final double version, final String letter) {
        Capabilities ret = new Capabilities();

        if (version >= 1.0) {
            ret.REAL_TIME = true;
            ret.OVERRIDES = true;
        }

        else if (version >= 0.8) {
            if (version==0.8 && (letter != null) && letter.equals("c")) {
                ret.REAL_TIME = true;
            } else if (version >= 0.9) {
                ret.REAL_TIME = true;
            }
        }
        return ret;
    }