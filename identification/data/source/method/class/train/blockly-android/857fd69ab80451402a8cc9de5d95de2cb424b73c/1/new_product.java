public static Block fromXml(XmlPullParser parser, BlockFactory factory)
            throws XmlPullParserException, IOException, BlocklyParserException {
        // TODO(fenichel): What if there are multiple blocks with the same id?
        String type = parser.getAttributeValue(null, "type");   // prototype name
        String id = parser.getAttributeValue(null, "id");
        if (type == null || type.isEmpty()) {
            // If the id was empty the blockfactory will just generate one.
            throw new BlocklyParserException("Block was missing a type.");
        }

        Block resultBlock = factory.obtainBlock(type, id);
        if (resultBlock == null) {
            throw new BlocklyParserException("Tried to obtain a block of an unknown type " + type);
        }

        // Set position.  Only if this is a top level block.
        String x = parser.getAttributeValue(null, "x");
        String y = parser.getAttributeValue(null, "y");
        if (x != null && y != null) {
            resultBlock.setPosition(Integer.parseInt(x), Integer.parseInt(y));
        }

        int eventType = parser.next();
        String text = "";
        String fieldName = "";
        Block childBlock = null;
        Input valueInput = null;
        Input statementInput = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagname = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagname.equalsIgnoreCase("block")) {
                        childBlock = fromXml(parser, factory);
                    } else if (tagname.equalsIgnoreCase("field")) {
                        fieldName = parser.getAttributeValue(null, "name");
                    } else if (tagname.equalsIgnoreCase("value")) {
                        valueInput = resultBlock.getInputByName(
                                parser.getAttributeValue(null, "name"));
                        if (valueInput == null) {
                            throw new BlocklyParserException("The value input was null!");
                        }
                    } else if (tagname.equalsIgnoreCase("statement")) {
                        statementInput = resultBlock.getInputByName(
                                parser.getAttributeValue(null, "name"));
                    } else if (tagname.equalsIgnoreCase("mutation")) {
                        // TODO(fenichel): Handle mutations.
                    }
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("block")) {
                        if (resultBlock == null) {
                            throw new BlocklyParserException(
                                    "Created a null block. This should never happen.");
                        }
                        return resultBlock;
                    } else if (tagname.equalsIgnoreCase("field")) {
                        Field toSet = resultBlock.getFieldByName(fieldName);
                        if (toSet != null) {
                            if (!toSet.setFromString(text)) {
                                throw new BlocklyParserException(
                                        "Failed to set a field's value from XML.");
                            }
                        }
                    } else if (tagname.equalsIgnoreCase("value")) {
                        if (valueInput != null && childBlock != null) {
                            if (valueInput.getConnection() == null
                                    || childBlock.getOutputConnection() == null) {
                                throw new BlocklyParserException("A connection was null.");
                            }
                            if (valueInput.getConnection().isConnected()) {
                                throw new BlocklyParserException(
                                        "Multiple values were provided for the same input.");
                            }
                            valueInput.getConnection().connect(childBlock.getOutputConnection());
                            valueInput = null;
                            childBlock = null;
                        } else {
                            throw new BlocklyParserException(
                                    "A value input or child block was null.");
                        }
                    } else if (tagname.equalsIgnoreCase("statement")) {
                        if (statementInput != null && childBlock != null) {
                            if (statementInput.getConnection() == null
                                    || childBlock.getPreviousConnection() == null) {
                                throw new BlocklyParserException("A connection was null.");
                            }
                            if (statementInput.getConnection().isConnected()) {
                                throw new BlocklyParserException(
                                        "Multiple statements were provided for the same input.");
                            }
                            statementInput.getConnection().connect(
                                    childBlock.getPreviousConnection());
                            valueInput = null;
                            childBlock = null;
                        } else {
                            throw new BlocklyParserException(
                                    "A statement input or child block was null.");
                        }
                    } else if (tagname.equalsIgnoreCase("comment")) {
                        resultBlock.setComment(text);
                    } else if (tagname.equalsIgnoreCase("next")) {
                        if (resultBlock.getNextConnection() == null
                                || childBlock.getPreviousConnection() == null) {
                            throw new BlocklyParserException("A connection was null.");
                        }
                        if (resultBlock.getNextConnection().isConnected()) {
                            throw new BlocklyParserException(
                                    "Multiple next blocks were provided for the same block.");
                        }
                        resultBlock.getNextConnection().connect(childBlock.getPreviousConnection());
                    }
                    break;

                default:
                    break;
            }
            eventType = parser.next();
        }
        // Should never reach here, since this is called from a workspace fromXml function.
        throw new BlocklyParserException(
                "Reached the end of Block.fromXml. This should never happen.");
    }