@Test
    public void testGetMetadata_String()
    {
        String mdString = "dc.contributor.author";
        DCValue[] dc = it.getMetadataByMetadataString(mdString);
        assertThat("testGetMetadata_String 0",dc,notNullValue());
        assertTrue("testGetMetadata_String 1",dc.length == 0);

        mdString = "dc.contributor.*";
        dc = it.getMetadataByMetadataString(mdString);
        assertThat("testGetMetadata_String 2",dc,notNullValue());
        assertTrue("testGetMetadata_String 3",dc.length == 0);

        mdString = "dc.contributor";
        dc = it.getMetadataByMetadataString(mdString);
        assertThat("testGetMetadata_String 4",dc,notNullValue());
        assertTrue("testGetMetadata_String 5",dc.length == 0);
    }