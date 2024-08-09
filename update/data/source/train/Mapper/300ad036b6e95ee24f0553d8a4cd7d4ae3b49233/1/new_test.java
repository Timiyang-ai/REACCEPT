@Test
    public void testUpdateByPrimaryKeySelective() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
            UserInfo userInfo = mapper.selectByPrimaryKey(1);
            Assert.assertNotNull(userInfo);
            userInfo.setUsertype(null);
            userInfo.setEmail("abel533@gmail.com");
            //不会更新username
            Assert.assertEquals(1, mapper.updateByPrimaryKeySelective(userInfo));

            userInfo = mapper.selectByPrimaryKey(1);
            Assert.assertEquals("1", userInfo.getUsertype());
            Assert.assertEquals("abel533@gmail.com", userInfo.getEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }