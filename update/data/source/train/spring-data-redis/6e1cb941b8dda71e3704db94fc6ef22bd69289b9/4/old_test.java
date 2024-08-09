@Test
    public void testGeoAdd(){
        doReturn(1l).when(nativeConnection).geoAdd(fooBytes, 1.23232, 34.2342434, barBytes);
        actual.add(connection.geoAdd(foo, 1.23232, 34.2342434, bar));
        verifyResults(Arrays.asList(new Object[] { 1l }));
    }