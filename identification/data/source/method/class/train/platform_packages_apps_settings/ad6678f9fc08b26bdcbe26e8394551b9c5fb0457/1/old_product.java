public static Pair<Boolean, String> getPathData(Uri uri) {
        final String path = uri.getPath();
        final String[] split = path.split("/", 3);

        // Split should be: [{}, SLICE_TYPE, KEY].
        // Example: "/action/wifi" -> [{}, "action", "wifi"]
        //          "/action/longer/path" -> [{}, "action", "longer/path"]
        if (split.length != 3) {
            throw new IllegalArgumentException("Uri (" + uri + ") has incomplete path: " + path);
        }

        final boolean isInline = TextUtils.equals(SettingsSlicesContract.PATH_SETTING_ACTION,
                split[1]);

        return new Pair<>(isInline, split[2]);
    }