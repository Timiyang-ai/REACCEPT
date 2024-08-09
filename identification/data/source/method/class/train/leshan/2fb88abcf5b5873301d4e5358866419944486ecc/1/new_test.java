    @Test
    public void create_instance() {
        BaseInstanceEnablerFactory instanceEnablerFactory = new BaseInstanceEnablerFactory() {

            @Override
            public LwM2mInstanceEnabler create() {
                return new DummyInstanceEnabler();
            }
        };

        List<Integer> alreadyUsedIds = Arrays.asList(2, 3, 5, 6);
        LwM2mInstanceEnabler instance = instanceEnablerFactory.create(model.getObjectModel(LwM2mId.ACCESS_CONTROL),
                null, alreadyUsedIds);
        assertNotNull("instance id is not set", instance.getId());
        assertFalse("new id must not be already used", alreadyUsedIds.contains(instance.getId()));
    }