public <T> int countByExample(Example example) {
        return commonMapper.countByExample(example.getEntityClass(), example);
    }