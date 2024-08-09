public static String getCamelCase(String yangIdentifier) {

        String[] strArray = yangIdentifier.split(HYPHEN);
        String camelCase = strArray[0];
        for (int i = 1; i < strArray.length; i++) {
            camelCase = camelCase + strArray[i].substring(0, 1).toUpperCase() + strArray[i].substring(1);
        }
        return camelCase;
    }