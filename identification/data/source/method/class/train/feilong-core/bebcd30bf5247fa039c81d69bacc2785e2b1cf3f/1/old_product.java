@SafeVarargs
    public static <O> List<O> sort(List<O> list,Comparator<O>...comparators){
        if (null == list){
            return emptyList();
        }

        if (isNullOrEmpty(comparators)){
            return list;
        }
        Collections.sort(list, toComparator(comparators));
        return list;
    }