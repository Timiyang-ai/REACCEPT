public static ContentType valueOf(String value) {
        String[] parts = PARAMETER_SPLIT_PATTERN.split(value);
        String[] types = TYPE_SPLIT_PATTERN.split(parts[0]);

        String type = types[0];
        String subType = MEDIA_TYPE_WILDCARD;
        Map<String, String> parameters = new HashMap<String, String>();

        if (types.length > 0) {
            subType = types[0];
        }

        // Element 0 is type/sub-type
        for (int i = 1; i < parts.length; ++i) {
            String[] keyValue = KEY_VALUE_SPLIT_PATTERN.split(parts[i]);

            if (keyValue.length == 2) {
                parameters.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }

        return new ContentType(type, subType, parameters);
    }