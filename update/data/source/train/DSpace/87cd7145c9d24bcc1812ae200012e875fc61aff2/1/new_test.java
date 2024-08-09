@Test
    public void testSetMetadata()
    {
        String name = "name";
        String sdesc = "short description";
        String itext = "introductory text";
        String copy = "copyright declaration";
        String sidebar = "side bar text";

        c.setMetadata("name", name);
        c.setMetadata("short_description", sdesc);
        c.setMetadata("introductory_text", itext);
        c.setMetadata("copyright_text", copy);
        c.setMetadata("side_bar_text", sidebar);

        assertThat("testSetMetadata 0",c.getMetadata("name"), equalTo(name));
        assertThat("testSetMetadata 1",c.getMetadata("short_description"), equalTo(sdesc));
        assertThat("testSetMetadata 2",c.getMetadata("introductory_text"), equalTo(itext));
        assertThat("testSetMetadata 4",c.getMetadata("copyright_text"), equalTo(copy));
        assertThat("testSetMetadata 5",c.getMetadata("side_bar_text"), equalTo(sidebar));
    }