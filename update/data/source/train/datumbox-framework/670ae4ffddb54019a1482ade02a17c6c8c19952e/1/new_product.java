public static <T> Set<Set<T>> combinations(Set<T> elements, int subsetSize) {
        Set<Set<T>> resultingCombinations = new HashSet<> ();
        int totalSize=elements.size();
        if (subsetSize == 0) {
            resultingCombinations.add(new HashSet<>());
        }
        else if (subsetSize <= totalSize) {
            Set<T> remainingElements = elements;

            Iterator<T> it = remainingElements.iterator();
            T X = it.next();
            it.remove();

            resultingCombinations.addAll(combinations(remainingElements, subsetSize));

            for (Set<T> combination : combinations(remainingElements, subsetSize-1)) {
                combination.add(X);
                resultingCombinations.add(combination);
            }

            remainingElements.add(X);
        }
        return resultingCombinations;
    }