    @Test
    public void selectForVersionTest()
    {
        assertSame( StandardV2_3.RECORD_FORMATS, selectForVersion( StandardV2_3.STORE_VERSION ) );
        assertSame( StandardV3_0.RECORD_FORMATS, selectForVersion( StandardV3_0.STORE_VERSION ) );
        assertSame( StandardV3_2.RECORD_FORMATS, selectForVersion( StandardV3_2.STORE_VERSION ) );
        assertSame( StandardV3_4.RECORD_FORMATS, selectForVersion( StandardV3_4.STORE_VERSION ) );
        assertSame( HighLimitV3_0_0.RECORD_FORMATS, selectForVersion( HighLimitV3_0_0.STORE_VERSION ) );
        assertSame( HighLimitV3_1_0.RECORD_FORMATS, selectForVersion( HighLimitV3_1_0.STORE_VERSION ) );
        assertSame( HighLimit.RECORD_FORMATS, selectForVersion( HighLimit.STORE_VERSION ) );
    }