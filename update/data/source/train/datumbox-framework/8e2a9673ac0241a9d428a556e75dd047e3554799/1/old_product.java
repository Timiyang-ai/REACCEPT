public static <T> Collection<List<T>> permutations(Collection<T> elements) {
        Collection<List<T>> permutations = Collections2.permutations(elements);
        
        List<List<T>> result = new ArrayList<>();
        for(List<T> internalList : permutations) {
            result.add(new ArrayList<>(internalList));
        }
        
        return result;
    }