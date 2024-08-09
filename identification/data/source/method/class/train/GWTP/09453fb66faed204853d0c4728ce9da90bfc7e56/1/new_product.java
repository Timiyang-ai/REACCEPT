public static ContentType valueOf(String value) {
        String type = WILDCARD;
        String subType = WILDCARD;
        Map<String, String> parameters = new HashMap<>();

        if (value != null) {
            String[] parts = value.split(PARAMETER_DELIMITER);
            String[] types = parts[0].split(TYPE_DELIMITER);

            type = types[0];

            if (types.length > 1) {
                subType = types[1];
            }

            // Element 0 is type/sub-type, others are parameters
            for (int i = 1; i < parts.length; ++i) {
                String[] keyValue = parts[i].split(KEY_VALUE_DELIMITER);

                if (keyValue.length == 2) {
                    parameters.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }
        }

        return new ContentType(type, subType, parameters);
    }