@Test
    public void read() throws IOException, NoPropertySetStreamException, MarkUnsupportedException {
        /* Read the POI filesystem's property set streams: */
        for (POIFile pf : Util.readPropertySets(file)) {
            try (InputStream in = new ByteArrayInputStream(pf.getBytes())) {
                PropertySetFactory.create(in);
            }
        }
    }