public static Map<String, String> getPropertiesMapForLog(){
        Properties properties = System.getProperties();
        return new TreeMap<String, String>(ConvertUtil.toMap(properties));
    }