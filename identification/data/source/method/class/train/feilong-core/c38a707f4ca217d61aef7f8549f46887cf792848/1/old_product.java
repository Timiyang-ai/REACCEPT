@SuppressWarnings("unchecked")
    public static <T> Iterator<T> toIterator(Object object){
        if (null == object){
            return null;
        }
        // object 不是空
        // 数组
        if (object.getClass().isArray()){
            return ArrayUtil.toIterator(object);
        }
        // Iterator
        else if (object instanceof Iterator){
            return (Iterator<T>) object;
        }
        // Collection
        else if (object instanceof Collection){
            return ((Collection<T>) object).iterator();
        }
        // Enumeration
        else if (object instanceof Enumeration){
            Enumeration<T> enumeration = (Enumeration<T>) object;
            return new EnumerationIterator<T>(enumeration);
        }
        // map
        else if (object instanceof Map){
            Set<T> keySet = ((Map<T, ?>) object).keySet();
            return keySet.iterator();
        }
        // 逗号分隔的字符串
        else if (object instanceof String){
            String[] strings = object.toString().split(",");
            return ArrayUtil.toIterator(strings);
        }else{
            throw new IllegalArgumentException("param object:[" + object + "] don't support convert to Iterator.");
        }
    }