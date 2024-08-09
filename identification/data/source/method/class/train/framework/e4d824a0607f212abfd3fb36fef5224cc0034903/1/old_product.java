public static String propertyIdToHumanFriendly(Object propertyId) {
        String string = propertyId.toString();
        if (string.isEmpty()) {
            return "";
        }

        // For nested properties, only use the last part
        int dotLocation = string.lastIndexOf('.');
        if (dotLocation > 0 && dotLocation < string.length() - 1) {
            string = string.substring(dotLocation + 1);
        }

        return camelCaseToHumanFriendly(string);
    }