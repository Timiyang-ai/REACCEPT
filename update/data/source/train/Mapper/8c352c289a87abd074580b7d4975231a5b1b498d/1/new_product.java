public <T> List<T> selectByExample(Example example) {
        if (example == null) {
            throw new NullPointerException("example参数不能为空!");
        }
        return (List<T>) EntityHelper.maplist2BeanList(commonMapper.selectByExample(example.getEntityClass(), example), example.getEntityClass());
    }