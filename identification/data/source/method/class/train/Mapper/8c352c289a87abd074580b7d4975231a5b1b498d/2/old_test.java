@Test
    public void testCountByExample() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CommonMapper entityMapper = sqlSession.getMapper(CommonMapper.class);
            EntityMapper baseMapper = new EntityMapper(entityMapper);

            Example example = new Example(Country.class);
            example.createCriteria().andGreaterThan("id", 100).andLessThanOrEqualTo("id", 150);

            int count = baseMapper.countByExample(example);
            Assert.assertEquals(50, count);

            example = new Example(Country.class);
            example.createCriteria().andLike("countryname","A%");

            count = baseMapper.countByExample(example);
            Assert.assertEquals(12, count);
        } finally {
            sqlSession.close();
        }
    }