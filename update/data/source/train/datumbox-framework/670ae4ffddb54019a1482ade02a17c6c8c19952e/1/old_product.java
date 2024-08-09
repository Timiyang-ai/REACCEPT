public static <T> Collection<List<T>> combinations(List<T> elements, int elementCount) {
        Set<Set<T>> combinations = combinationsSet(elements, elementCount);

        Collection<List<T>> result = new ArrayList<>();
        for(Set<T> linkedset : combinations) {
            result.add(new ArrayList<>(linkedset));
        }

        return result;
    }