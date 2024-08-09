@Test
    public void testInsert() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("abel533");
            userInfo.setPassword("123456");
            userInfo.setUsertype("2");
            userInfo.setEmail("abel533@gmail.com");
            Collection collection = sqlSession.getConfiguration().getMappedStatements();
            for (Object o : collection) {
                if(o instanceof MappedStatement){
                    MappedStatement ms = (MappedStatement) o;
                    if (ms.getId().contains("UserInfoMapper.insert")) {
                        System.out.println(ms.getId());
                    }
                }
            }

            Assert.assertEquals(1, mapper.insert(userInfo));

            Assert.assertNotNull(userInfo.getId());
            Assert.assertTrue((int) userInfo.getId() >= 6);

            Assert.assertEquals(1,mapper.deleteByPrimaryKey(userInfo));
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }