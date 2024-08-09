public static <T> Collection<List<T>> permutations(Collection<T> elements) {
        Collection<List<T>> result = new ArrayList<>();
        if (elements.isEmpty()) {
            result.add(new LinkedList<>());
            return result;
        }
        
        List<T> rest = new LinkedList<>(elements);
        T head = rest.remove(0);
        for (List<T> permutations : permutations(rest)) {
            List<List<T>> subLists = new ArrayList<>();
            for (int i = 0; i <= permutations.size(); i++) {
                List<T> subList = new ArrayList<>();
                subList.addAll(permutations);
                subList.add(i, head);
                subLists.add(subList);
            }
            result.addAll(subLists);
        }
        return result;
    }