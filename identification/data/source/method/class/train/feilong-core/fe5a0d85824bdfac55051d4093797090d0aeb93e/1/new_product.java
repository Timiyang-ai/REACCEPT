public static DynaBean newDynaBean(Map<?, ?> valueMap){
        Validate.notNull(valueMap, "valueMap can't be null!");

        //---------------------------------------------------------------
        LazyDynaBean lazyDynaBean = new LazyDynaBean();
        for (Map.Entry<?, ?> entry : valueMap.entrySet()){
            String key = ConvertUtil.toString(entry.getKey());

            Validate.notBlank(key, "entry.getKey() can't be blank!");
            lazyDynaBean.set(key, entry.getValue());
        }
        return lazyDynaBean;
    }