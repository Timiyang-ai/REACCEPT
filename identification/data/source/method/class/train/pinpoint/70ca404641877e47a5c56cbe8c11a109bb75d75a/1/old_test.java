@Test
    public void testIsProfilableClass() throws IOException {

        ProfilerConfig profilerConfig = new ProfilerConfig();
        profilerConfig.setProfilableClass("com.nhn.pinpoint.testweb.controller.*,com.nhn.pinpoint.testweb.MyClass");

        Assert.assertTrue(profilerConfig.isProfilableClass("com/nhn/pinpoint/testweb/MyClass"));
        Assert.assertTrue(profilerConfig.isProfilableClass("com/nhn/pinpoint/testweb/controller/MyController"));
        Assert.assertTrue(profilerConfig.isProfilableClass("com/nhn/pinpoint/testweb/controller/customcontroller/MyCustomController"));

        Assert.assertFalse(profilerConfig.isProfilableClass("com/nhn/pinpoint/testweb/MyUnknownClass"));
        Assert.assertFalse(profilerConfig.isProfilableClass("com/nhn/pinpoint/testweb/controller2/MyController"));
    }