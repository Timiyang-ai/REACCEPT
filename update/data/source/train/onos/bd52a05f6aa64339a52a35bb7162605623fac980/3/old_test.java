@Test
    public void testAreaAddres() throws Exception {
        result1 = IsisUtil.areaAddres(Bytes.toArray(
                IsisUtil.areaAddressToBytes(areaAddres)));
        assertThat(result1, is(areaAddres));
    }