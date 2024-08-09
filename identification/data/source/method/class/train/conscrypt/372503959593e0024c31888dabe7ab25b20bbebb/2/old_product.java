public static boolean isValidSniHostname(String sniHostname) {
        if (sniHostname == null) {
            return false;
        }

        // Must be a FQDN.
        if (sniHostname.indexOf('.') == -1) {
            return false;
        }

        if (Platform.isLiteralIpAddress(sniHostname)) {
            return false;
        }

        return true;
    }