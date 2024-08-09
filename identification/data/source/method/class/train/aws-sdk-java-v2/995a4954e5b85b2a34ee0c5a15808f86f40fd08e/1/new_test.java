    private Map<String, Profile> profiles(Profile... profiles) {
        Map<String, Profile> result = new HashMap<>();
        Stream.of(profiles).forEach(p -> result.put(p.name(), p));
        return result;
    }