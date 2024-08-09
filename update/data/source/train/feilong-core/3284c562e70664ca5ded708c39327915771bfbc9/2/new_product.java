public static <O> List<O> removeDuplicate(Collection<O> objectCollection){
        return isNullOrEmpty(objectCollection) ? Collections.<O> emptyList() : toList(new LinkedHashSet<O>(objectCollection));
    }