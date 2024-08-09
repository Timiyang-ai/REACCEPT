    @Test
    public void promoteTrunkToProduction() throws StoreException, ProctorPromoter.TestPromotionException {
        Mockito.doNothing().when(proctorPromoter).promote(
                Mockito.eq(TEST_NAME),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.eq(USERNAME),
                Mockito.eq(PASSWORD),
                Mockito.eq(AUTHOR),
                Mockito.eq(METADATA)
        );

        proctorPromoter.promoteTrunkToProduction(TEST_NAME, TRUNK_REVISION, PROD_REVISION, USERNAME, PASSWORD, AUTHOR, METADATA);
        Mockito.verify(proctorPromoter, Mockito.times(1)).promote(
                TEST_NAME,
                Environment.WORKING,
                TRUNK_REVISION,
                Environment.PRODUCTION,
                PROD_REVISION,
                USERNAME,
                PASSWORD,
                AUTHOR,
                METADATA
        );
    }