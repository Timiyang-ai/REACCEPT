public <T> List<T> selectByExample(Example example) {
        return (List<T>) EntityHelper.maplist2BeanList(commonMapper.selectByExample(example.getEntityClass(), example), example.getEntityClass());
    }