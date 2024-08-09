@Test
    public void testSelectByExample() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CommonMapper entityMapper = sqlSession.getMapper(CommonMapper.class);
            EntityMapper baseMapper = new EntityMapper(entityMapper);

            Example example = new Example(Country.class);
            example.createCriteria().andEqualTo("countryname","China");

            List<Country> countries = baseMapper.selectByExample(example);
            Assert.assertEquals(1,countries.size());
            Assert.assertEquals("CN",countries.get(0).getCountrycode());

            example = new Example(Country.class);
            example.createCriteria().andEqualTo("id",100);

            countries = baseMapper.selectByExample(example);
            Assert.assertEquals(1,countries.size());
            Assert.assertEquals("MY",countries.get(0).getCountrycode());
        } finally {
            sqlSession.close();
        }
    }