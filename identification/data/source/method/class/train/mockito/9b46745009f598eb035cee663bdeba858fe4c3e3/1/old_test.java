    @Test
    public void createArgumentsAreDifferentException_withoutJUnitOrOpenTest() throws Exception {
        AssertionError e = invokeFactoryThroughLoader(classLoaderWithoutJUnitOrOpenTest);

        assertThat(e).isExactlyInstanceOf(nonJunitArgumentsAreDifferent);
    }