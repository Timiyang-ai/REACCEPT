public static <O> List<O> sort(List<O> list,String...propertyNames){
        if (null == list){
            return null;
        }
        Validate.notEmpty(propertyNames, "propertyNames can't be null/empty!");
        Validate.noNullElements(propertyNames, "propertyName:%s has empty value", propertyNames);

        Collections.sort(list, BeanComparatorUtil.chainedComparator(propertyNames));
        return list;
    }