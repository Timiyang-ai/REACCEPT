@Synchronized
    int getRanking(String reader) {
        List<String> sorted = distanceToTail.entrySet()
                                   .stream()
                                   .sorted((o1, o2) -> -Long.compare(o1.getValue(), o2.getValue()))
                                   .map(e -> e.getKey())
                                   .collect(Collectors.toList());
        return sorted.indexOf(reader);
    }