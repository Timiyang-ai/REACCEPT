@SuppressWarnings("unchecked")
    public static <T> List<T> toList(T...arrays){
        return Validator.isNullOrEmpty(arrays) ? (List<T>) Collections.emptyList() : new ArrayList<T>(Arrays.asList(arrays));
    }