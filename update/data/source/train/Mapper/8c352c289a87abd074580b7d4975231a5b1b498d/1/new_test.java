@Test
    public void testSelectByExample() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
            EntityMapper entityMapper = new EntityMapper(commonMapper);

            Example example = new Example(Country.class);
            example.createCriteria().andEqualTo("countryname", "China");

            List<Country> countries = entityMapper.selectByExample(example);
            Assert.assertEquals(1, countries.size());
            Assert.assertEquals("CN", countries.get(0).getCountrycode());

            example = new Example(Country.class);
            example.createCriteria().andEqualTo("id", 100);

            countries = entityMapper.selectByExample(example);
            Assert.assertEquals(1, countries.size());
            Assert.assertEquals("MY", countries.get(0).getCountrycode());
        } finally {
            sqlSession.close();
        }
    }