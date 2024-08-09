@Test
    public void testObjectWithValueAndAddProperty() throws Exception {
        String id = objectWithValueAndAddProperty();
        if (id == null) {
            return;
        }
        PolyglotEngine.Value apply = findGlobalSymbol(id);

        TruffleObject truffleObject = (TruffleObject) apply.execute().get();
        assertIsObjectOfLanguage(truffleObject);
        ObjectWithValueInterface object = JavaInterop.asJavaObject(ObjectWithValueInterface.class, truffleObject);
        object.add(20.0);
        object.add(22.0);

        Assert.assertEquals(42.0, object.value(), 0.1);
    }