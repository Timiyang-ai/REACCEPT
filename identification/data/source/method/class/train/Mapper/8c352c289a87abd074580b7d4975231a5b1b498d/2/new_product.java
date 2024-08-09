public <T> int countByExample(Example example) {
        if (example == null) {
            throw new NullPointerException("example参数不能为空!");
        }
        return commonMapper.countByExample(example.getEntityClass(), example);
    }