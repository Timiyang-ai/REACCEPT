@Test
    public void read() throws IOException, NoPropertySetStreamException, MarkUnsupportedException {
        /* Read the POI filesystem's property set streams: */
        for (POIFile pf : Util.readPropertySets(file)) {
            final InputStream in = new ByteArrayInputStream(pf.getBytes());
            try {
                PropertySetFactory.create(in);
            } finally {
                in.close();
            }
        }
    }