@Test
    public void testAddIdentifier() {
        System.out.println("addIdentifier");
        String type = "cpe";
        String value = "cpe:/a:apache:struts:2.1.2";
        String title = "Apache Struts 2.1.2";
        String url = "http://somewhere";
        Dependency instance = new Dependency();
        instance.addIdentifier(type, value, title, url);
        assertEquals(1,instance.getIdentifiers().size());
        Identifier i = instance.getIdentifiers().get(0);
        assertEquals(type,i.getType());
        assertEquals(value, i.getValue());
        assertEquals(title, i.getTitle());
        assertEquals(url, i.getUrl());
    }