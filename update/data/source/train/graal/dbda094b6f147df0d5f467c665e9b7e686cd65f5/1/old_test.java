@Test
    public void testObjectWithValueAndAddProperty() throws Exception {
        String id = objectWithValueAndAddProperty();
        if (id == null) {
            return;
        }
        PolyglotEngine.Value apply = findGlobalSymbol(id);

        ObjectWithValueInterface object = JavaInterop.asJavaObject(ObjectWithValueInterface.class, (TruffleObject) apply.execute().get());
        object.add(20.0);
        object.add(22.0);

        Assert.assertEquals(42.0, object.value(), 0.1);
    }