static boolean isValidSniHostname(String sniHostname) {
        if (sniHostname == null) {
            return false;
        }

        // Must be a FQDN.
        return sniHostname.indexOf('.') != -1 && !Platform.isLiteralIpAddress(sniHostname);

    }