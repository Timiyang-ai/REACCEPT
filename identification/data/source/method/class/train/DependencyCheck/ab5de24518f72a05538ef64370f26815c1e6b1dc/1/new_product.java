public Hints parseHints(InputStream inputStream) throws HintParseException, SAXException {
        try (
                InputStream schemaStream13 = FileUtils.getResourceAsStream(HINT_SCHEMA_1_3);
                InputStream schemaStream12 = FileUtils.getResourceAsStream(HINT_SCHEMA_1_2);
                InputStream schemaStream11 = FileUtils.getResourceAsStream(HINT_SCHEMA_1_1);
                ) {
            final HintHandler handler = new HintHandler();
            final SAXParser saxParser = XmlUtils.buildSecureSaxParser(schemaStream13, schemaStream12, schemaStream11);
            final XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setErrorHandler(new HintErrorHandler());
            xmlReader.setContentHandler(handler);
            try (Reader reader = new InputStreamReader(inputStream, "UTF-8")) {
                final InputSource in = new InputSource(reader);
                xmlReader.parse(in);
                final Hints hints = new Hints();
                hints.setHintRules(handler.getHintRules());
                hints.setVendorDuplicatingHintRules(handler.getVendorDuplicatingHintRules());
                return hints;
            }
        } catch (ParserConfigurationException | FileNotFoundException ex) {
            LOGGER.debug("", ex);
            throw new HintParseException(ex);
        } catch (SAXException ex) {
            if (ex.getMessage().contains("Cannot find the declaration of element 'hints'.")) {
                throw ex;
            } else {
                LOGGER.debug("", ex);
                throw new HintParseException(ex);
            }
        } catch (IOException ex) {
            LOGGER.debug("", ex);
            throw new HintParseException(ex);
        }
    }