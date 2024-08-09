public List<SuppressionRule> parseSuppressionRules(InputStream inputStream) throws SuppressionParseException, SAXException {
        try (
                InputStream schemaStream12 = FileUtils.getResourceAsStream(SUPPRESSION_SCHEMA_1_2);
                InputStream schemaStream11 = FileUtils.getResourceAsStream(SUPPRESSION_SCHEMA_1_1);
                InputStream schemaStream10 = FileUtils.getResourceAsStream(SUPPRESSION_SCHEMA_1_0);
            ) {
            final SuppressionHandler handler = new SuppressionHandler();
            final SAXParser saxParser = XmlUtils.buildSecureSaxParser(schemaStream12, schemaStream11, schemaStream10);
            final XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setErrorHandler(new SuppressionErrorHandler());
            xmlReader.setContentHandler(handler);
            try (Reader reader = new InputStreamReader(inputStream, "UTF-8")) {
                final InputSource in = new InputSource(reader);
                xmlReader.parse(in);
                return handler.getSuppressionRules();
            }
        } catch (ParserConfigurationException | FileNotFoundException ex) {
            LOGGER.debug("", ex);
            throw new SuppressionParseException(ex);
        } catch (SAXException ex) {
            if (ex.getMessage().contains("Cannot find the declaration of element 'suppressions'.")) {
                throw ex;
            } else {
                LOGGER.debug("", ex);
                throw new SuppressionParseException(ex);
            }
        } catch (IOException ex) {
            LOGGER.debug("", ex);
            throw new SuppressionParseException(ex);
        }
    }