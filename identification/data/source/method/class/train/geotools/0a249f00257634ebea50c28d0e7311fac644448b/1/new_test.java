@Ignore
    @Test
    public void testValidate() throws IOException, SAXException, ParserConfigurationException {
        Parser p = new Parser(new WMTSConfiguration());
        p.setValidating(true);
        try (InputStream is = getClass().getResourceAsStream("./nasa.getcapa.xml")) {
            p.parse(is);
        }
        if (!p.getValidationErrors().isEmpty()) {
            for (Iterator e = p.getValidationErrors().iterator(); e.hasNext(); ) {
                SAXParseException ex = (SAXParseException) e.next();
                LOGGER.log(
                        Level.SEVERE,
                        ex.getLineNumber() + "," + ex.getColumnNumber() + " -" + ex.toString());
            }
            fail("Document did not validate.");
        }
    }