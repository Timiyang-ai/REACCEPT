@Test
    public void getParameterizedClassArgumentsTest() {
        ReflectionHelper.DeclaringClassInterfacePair dcip = ReflectionHelper.getClass(TestNoInterface.class, I.class);
        Class[] arguments = ReflectionHelper.getParameterizedClassArguments(dcip);
        final Class aClass = arguments[0];

        dcip = ReflectionHelper.getClass(TestInterface.class, I.class);
        arguments = ReflectionHelper.getParameterizedClassArguments(dcip);
        assertEquals(aClass, arguments[0]);
    }