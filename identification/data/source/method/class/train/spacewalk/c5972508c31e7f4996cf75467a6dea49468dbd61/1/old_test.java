    public static Profile createTestProfile(User user, Channel channel)
        throws Exception {

        Profile p = new Profile();
        p.setInfo("Test information for a test Profile.");
        p.setName("RHN-JAVA" + TestUtils.randomString());
        p.setDescription("This is only a test.");
        p.setBaseChannel(channel);
        p.setOrg(user.getOrg());
        p.setProfileType(ProfileFactory.TYPE_NORMAL);

        assertNull(p.getId());
        TestUtils.saveAndFlush(p);
        assertNotNull(p.getId());

        return p;
    }