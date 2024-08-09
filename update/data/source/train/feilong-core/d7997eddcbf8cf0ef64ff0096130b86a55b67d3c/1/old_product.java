@SuppressWarnings("unchecked")
    public static <T> List<T> toList(T[] arrays){
        //如果直接使用 Arrays.asList(arrays)方法 返回的是Arrays类的内部类的对象ArrayList,没有实现AbstractList类的add方法,如果 strList.add("c");导致抛异常! 
        return null == arrays ? (List<T>) Collections.emptyList() : new ArrayList<T>(Arrays.asList(arrays));
    }