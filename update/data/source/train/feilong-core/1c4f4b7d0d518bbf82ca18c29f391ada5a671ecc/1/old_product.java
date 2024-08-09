public static <T> List<T> toList(final Enumeration<T> enumeration){
        if (Validator.isNullOrEmpty(enumeration)){
            return Collections.emptyList();
        }
        return Collections.list(enumeration);
    }