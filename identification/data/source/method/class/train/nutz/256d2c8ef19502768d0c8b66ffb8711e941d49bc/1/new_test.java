@Test
    public void cellTest(){
        Object dest = Json.fromJson(Streams.fileInr("org/nutz/json/person.txt"));
        assertEquals("dtri", MapList.cell(dest, "company.name"));
        assertEquals("Dao", MapList.cell(dest, "company.creator.name"));
    }