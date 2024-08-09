@SuppressWarnings("unchecked")
    public static <T> Iterator<T> toIterator(Object object){
        if (null == object){
            return null;
        }

        // 逗号分隔的字符串
        if (object instanceof String){
            return toIterator(ConvertUtil.toStrings(object));
        }
        // 数组
        else if (object.getClass().isArray()){
            return org.apache.commons.collections4.IteratorUtils.arrayIterator(object);
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
        throw new IllegalArgumentException("param object:[" + object + "] don't support convert to Iterator.");
    }