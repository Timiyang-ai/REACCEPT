    @Test
    public void checkContract() throws Exception {
        callMain("-ch", basePath + "contract1.unicon");
        System.out.println(output);
        assertEquals(0, errors.size());
    }