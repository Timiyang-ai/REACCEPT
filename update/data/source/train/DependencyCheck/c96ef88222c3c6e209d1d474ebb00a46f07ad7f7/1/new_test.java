@Test
    public void testGenerateReport() {
        try {
            String templateName = "XmlReport";

            File f = new File("target/test-reports");
            if (!f.exists()) {
                f.mkdir();
            }
            File writeTo = new File("target/test-reports/Report.xml");
            File suppressionFile = BaseTest.getResourceAsFile(this, "incorrectSuppressions.xml");

            Settings.setString(Settings.KEYS.SUPPRESSION_FILE, suppressionFile.getAbsolutePath());

            //File struts = new File(this.getClass().getClassLoader().getResource("struts2-core-2.1.2.jar").getPath());
            File struts = BaseTest.getResourceAsFile(this, "struts2-core-2.1.2.jar");
            //File axis = new File(this.getClass().getClassLoader().getResource("axis2-adb-1.4.1.jar").getPath());
            File axis = BaseTest.getResourceAsFile(this, "axis2-adb-1.4.1.jar");
            //File jetty = new File(this.getClass().getClassLoader().getResource("org.mortbay.jetty.jar").getPath());
            File jetty = BaseTest.getResourceAsFile(this, "org.mortbay.jetty.jar");

            Settings.setBoolean(Settings.KEYS.AUTO_UPDATE, false);
            Engine engine = new Engine();

            engine.scan(struts);
            engine.scan(axis);
            engine.scan(jetty);
            engine.analyzeDependencies();
            engine.writeReports("Test Report", "org.owasp", "dependency-check-core", "1.4.7", writeTo, "XML");

            engine.cleanup();

            InputStream xsdStream = ReportGenerator.class.getClassLoader().getResourceAsStream("schema/dependency-check.1.5.xsd");
            StreamSource xsdSource = new StreamSource(xsdStream);
            StreamSource xmlSource = new StreamSource(writeTo);
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(xsdSource);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
        } catch (InvalidSettingException ex) {
            fail(ex.getMessage());
        } catch (DatabaseException | ExceptionCollection | ReportException | SAXException | IOException ex) {
            fail(ex.getMessage());
        }
    }