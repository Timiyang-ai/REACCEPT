    @Test
    public void create() {
        assertNotNull(instanceCreator.create(ClassWithDefaultConstructor.class));
        assertNotNull(instanceCreator.create(ClassWithExplicitNoArgsConstructor.class));
        assertNull(instanceCreator.create(ClassWithPrivateConstructor.class));
        assertNull(instanceCreator.create(ClassWithImplicitConstructorArg.class));
        assertNull(instanceCreator.create(ClassWithExplicitConstructorArg.class));
    }