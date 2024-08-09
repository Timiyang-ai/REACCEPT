@SuppressWarnings("unchecked")
    public static <T> List<T> toList(final Enumeration<T> enumeration){
        return Validator.isNullOrEmpty(enumeration) ? (List<T>) Collections.emptyList() : Collections.list(enumeration);
    }