public static DependencyVersion parseVersion(String text) {
        if (text == null) {
            return null;
        }
        //'-' is a special case used within the CVE entries, just include it as the version.
        if ("-".equals(text)) {
            final DependencyVersion dv = new DependencyVersion();
            final List<String> list = new ArrayList<>();
            list.add(text);
            dv.setVersionParts(list);
            return dv;
        }
        String version = null;
        Matcher matcher = RX_VERSION.matcher(text);
        if (matcher.find()) {
            version = matcher.group();
        }
        //throw away the results if there are two things that look like version numbers
        if (matcher.find()) {
            return null;
        }
        if (version == null) {
            matcher = RX_SINGLE_VERSION.matcher(text);
            if (matcher.find()) {
                version = matcher.group();
            } else {
                return null;
            }
            //throw away the results if there are two things that look like version numbers
            if (matcher.find()) {
                return null;
            }
        }
        if (version != null && version.endsWith("-py2") && version.length() > 4) {
            version = version.substring(0, version.length() - 4);
        }
        return new DependencyVersion(version);
    }