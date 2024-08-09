@Synchronized
    int getRanking(String reader) {
        List<String> sorted = getRelativeSizes().entrySet()
                                   .stream()
                                   .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                                   .map(Entry::getKey)
                                   .collect(Collectors.toList());
        return sorted.indexOf(reader);
    }