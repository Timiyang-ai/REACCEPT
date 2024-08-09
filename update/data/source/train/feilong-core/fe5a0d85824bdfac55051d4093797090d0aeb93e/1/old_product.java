public static DynaBean newDynaBean(Map<String, ?> valueMap){
        Validate.notNull(valueMap, "valueMap can't be null!");

        //---------------------------------------------------------------

        LazyDynaBean lazyDynaBean = new LazyDynaBean();
        for (Map.Entry<String, ?> entry : valueMap.entrySet()){
            Validate.notBlank(entry.getKey(), "entry.getKey() can't be blank!");
            lazyDynaBean.set(entry.getKey(), entry.getValue());
        }
        return lazyDynaBean;
    }