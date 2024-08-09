    @SafeVarargs
    private final Profile profile(String name, Map.Entry<String, String>... properties) {
        Map<String, String> propertiesMap = new HashMap<>();
        Stream.of(properties).forEach(p -> propertiesMap.put(p.getKey(), p.getValue()));
        return Profile.builder().name(name).properties(propertiesMap).build();
    }