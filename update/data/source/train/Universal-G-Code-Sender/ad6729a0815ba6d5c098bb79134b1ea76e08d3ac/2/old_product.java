static protected Capabilities getGrblStatusCapabilities(final double version, final String letter) {
        if (version >= 0.8) {
            if (version==0.8 && (letter != null) && letter.equals("c")) {
                return Capabilities.STATUS_C;
            } else if (version >= 0.9) {
                return Capabilities.STATUS_C;
            }
        }
        return null;
    }