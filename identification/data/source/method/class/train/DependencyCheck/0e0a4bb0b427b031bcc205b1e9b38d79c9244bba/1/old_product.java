public Hints parseHints(InputStream inputStream) throws HintParseException, SAXException {
        InputStream schemaStream = null;
        try {
            schemaStream = this.getClass().getClassLoader().getResourceAsStream(HINT_SCHEMA);
            final HintHandler handler = new HintHandler();
            final SAXParser saxParser = XmlUtils.buildSecureSaxParser(schemaStream);
            final XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setErrorHandler(new HintErrorHandler());
            xmlReader.setContentHandler(handler);

            final Reader reader = new InputStreamReader(inputStream, "UTF-8");
            final InputSource in = new InputSource(reader);

            xmlReader.parse(in);
            final Hints hints = new Hints();
            hints.setHintRules(handler.getHintRules());
            hints.setVendorDuplicatingHintRules(handler.getVendorDuplicatingHintRules());
            return hints;
        } catch (ParserConfigurationException ex) {
            LOGGER.debug("", ex);
            throw new HintParseException(ex);
        } catch (SAXException ex) {
            if (ex.getMessage().contains("Cannot find the declaration of element 'hints'.")) {
                throw ex;
            } else {
                LOGGER.debug("", ex);
                throw new HintParseException(ex);
            }
        } catch (FileNotFoundException ex) {
            LOGGER.debug("", ex);
            throw new HintParseException(ex);
        } catch (IOException ex) {
            LOGGER.debug("", ex);
            throw new HintParseException(ex);
        } finally {
            if (schemaStream != null) {
                try {
                    schemaStream.close();
                } catch (IOException ex) {
                    LOGGER.debug("Error closing hint file stream", ex);
                }
            }
        }
    }