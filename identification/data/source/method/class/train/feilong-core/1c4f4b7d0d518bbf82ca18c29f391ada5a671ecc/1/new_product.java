@SuppressWarnings("unchecked")
    public static <O> List<O> removeDuplicate(Collection<O> objectCollection){
        return Validator.isNullOrEmpty(objectCollection) ? (List<O>) Collections.emptyList()
                        : new ArrayList<O>(new LinkedHashSet<O>(objectCollection));
    }