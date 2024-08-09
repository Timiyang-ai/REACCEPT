public static Map<String, String> readAllPropertiesToMap(String baseName,Locale locale){
        ResourceBundle resourceBundle = getResourceBundle(baseName, locale);
        return readAllPropertiesToMap(resourceBundle);
    }