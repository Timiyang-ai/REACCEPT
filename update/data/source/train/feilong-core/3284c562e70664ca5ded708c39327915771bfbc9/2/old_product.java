public static <O> List<O> removeDuplicate(Collection<O> objectCollection){
        return Validator.isNullOrEmpty(objectCollection) ? Collections.<O> emptyList()
                        : new ArrayList<O>(new LinkedHashSet<O>(objectCollection));
    }