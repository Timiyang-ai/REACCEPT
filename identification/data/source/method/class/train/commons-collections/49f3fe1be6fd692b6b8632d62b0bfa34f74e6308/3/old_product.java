public static Object max(Object o1, Object o2, Comparator comparator) {
        if (comparator == null) comparator = NATURAL;
        int c = comparator.compare(o1, o2);
        return (c > 0) ? o1 : o2;        
    }