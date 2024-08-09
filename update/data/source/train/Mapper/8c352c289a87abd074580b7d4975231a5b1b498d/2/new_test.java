@Test
    public void testCountByExample() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
            EntityMapper entityMapper = new EntityMapper(commonMapper);

            Example example = new Example(Country.class);
            example.createCriteria().andGreaterThan("id", 100).andLessThanOrEqualTo("id", 150);

            int count = entityMapper.countByExample(example);
            Assert.assertEquals(50, count);

            example = new Example(Country.class);
            example.createCriteria().andLike("countryname", "A%");

            count = entityMapper.countByExample(example);
            Assert.assertEquals(12, count);
        } finally {
            sqlSession.close();
        }
    }