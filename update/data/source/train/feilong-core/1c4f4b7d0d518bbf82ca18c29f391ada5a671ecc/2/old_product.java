public static <O> List<O> removeDuplicate(Collection<O> objectCollection){
        if (Validator.isNullOrEmpty(objectCollection)){
            return Collections.emptyList();
        }
        return new ArrayList<O>(new LinkedHashSet<O>(objectCollection));
    }