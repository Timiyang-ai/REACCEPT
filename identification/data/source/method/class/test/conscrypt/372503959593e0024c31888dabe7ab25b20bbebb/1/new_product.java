static boolean isLiteralIpAddress(String hostname) {
        /* This is here for backwards compatibility for pre-Honeycomb devices. */
        Pattern ipPattern = AddressUtils.ipPattern;
        if (ipPattern == null) {
            AddressUtils.ipPattern = ipPattern = Pattern.compile(IP_PATTERN);
        }
        return ipPattern.matcher(hostname).matches();
    }