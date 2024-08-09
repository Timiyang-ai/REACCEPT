public static Map<String, String> getPropertiesMap(){
        return new TreeMap<String, String>(toMap(System.getProperties()));
    }