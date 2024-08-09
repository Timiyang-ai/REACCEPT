@Deprecated
    @SuppressWarnings({ "unchecked" })
    public static <T> Iterator<T> toIterator(Object arrays){
        if (null == arrays){
            return null;
        }
        List<T> list = null;
        try{
            // 如果我们幸运的话,它是一个对象数组,我们可以遍历并with no copying
            Object[] objArrays = (Object[]) arrays;
            list = (List<T>) toList(objArrays);
        }catch (ClassCastException e){
            LOGGER.debug("arrays can not cast to Object[],maybe primitive type,values is:{},{}", arrays, e.getMessage());
            // Rats -- 它是一个基本类型数组
            int length = Array.getLength(arrays);
            list = new ArrayList<T>(length);
            for (int i = 0; i < length; ++i){
                Object object = Array.get(arrays, i);
                list.add((T) object);
            }
        }
        return list.iterator();
    }