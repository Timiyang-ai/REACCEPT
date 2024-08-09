static boolean isValidSniHostname(String sniHostname) {
        if (sniHostname == null) {
            return false;
        }

        // Must be a FQDN that does not have a trailing dot.
        return sniHostname.indexOf('.') != -1 && !Platform.isLiteralIpAddress(sniHostname)
                && !sniHostname.endsWith(".") && sniHostname.indexOf('\0') == -1;
    }