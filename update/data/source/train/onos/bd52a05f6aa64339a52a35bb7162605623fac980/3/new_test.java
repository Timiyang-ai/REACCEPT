@Test
    public void testAreaAddres() throws Exception {
        result1 = IsisUtil.areaAddress(Bytes.toArray(
                IsisUtil.areaAddressToBytes(areaAddress)));
        assertThat(result1, is(areaAddress));
    }