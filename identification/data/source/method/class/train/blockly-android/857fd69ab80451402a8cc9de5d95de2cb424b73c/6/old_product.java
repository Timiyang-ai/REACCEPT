public static Block fromXml(XmlPullParser parser, BlockFactory factory) throws XmlPullParserException, IOException,
            BlocklyParserException {
        // TODO(fenichel): What if there are multiple blocks with the same id?
        String type = parser.getAttributeValue(null, "type");   // prototype name
        String id = parser.getAttributeValue(null, "id");
        if (type == null || type.isEmpty()) {
            // If the id was empty the blockfactory will just generate one.
            Log.d(TAG, "Block was missing a type.");
            throw new BlocklyParserException();
        }

        Block resultBlock = factory.obtainBlock(type, id);
        if (resultBlock == null) {
            Log.d(TAG, "Tried to obtain a block of an unknown type " + type);
            throw new BlocklyParserException();
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
                    }
                    else if (tagname.equalsIgnoreCase("field")){
                        fieldName = parser.getAttributeValue(null, "name");
                    }
                    else if (tagname.equalsIgnoreCase("value")){
                        valueInput = resultBlock.getInputByName(
                                parser.getAttributeValue(null, "name"));
                        if (valueInput == null) {
                            Log.d(TAG, "The value input was null!");
                            throw new BlocklyParserException();
                        }
                    }
                    else if (tagname.equalsIgnoreCase("statement")){
                        statementInput = resultBlock.getInputByName(
                                parser.getAttributeValue(null, "name"));
                    }
                    else if (tagname.equalsIgnoreCase("mutation")){
                        // TODO(fenichel): Handle mutations.
                    }
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("block")) {
                        if (resultBlock == null) {
                            Log.d(TAG, "Created a null block. This should never happen.");
                            throw new BlocklyParserException();
                        }
                        return resultBlock;
                    }
                    else if (tagname.equalsIgnoreCase("field")){
                        Field toSet = resultBlock.getFieldByName(fieldName);
                        if (toSet != null) {
                            if (!toSet.setFromXmlText(text)) {
                                Log.d(TAG, "Failed to set a field's value from XML.");
                                throw new BlocklyParserException();
                            }
                        }
                    }
                    else if (tagname.equalsIgnoreCase("value")) {
                        if (valueInput != null && childBlock != null) {
                            if (valueInput.getConnection() == null
                                    || childBlock.getOutputConnection() == null) {
                                Log.d(TAG, "A connection was null.");
                                throw new BlocklyParserException();
                            }
                            valueInput.getConnection().connect(childBlock.getOutputConnection());
                            valueInput = null;
                            childBlock = null;
                        } else {
                            Log.d(TAG, "A value input or child block was null.");
                            throw new BlocklyParserException();
                        }
                    }
                    else if (tagname.equalsIgnoreCase("statement")) {
                        if (statementInput != null && childBlock != null) {
                            if (statementInput.getConnection() == null
                                    || childBlock.getPreviousConnection() == null) {
                                Log.d(TAG, "A connection was null.");
                                throw new BlocklyParserException();
                            }
                            statementInput.getConnection().connect(
                                    childBlock.getPreviousConnection());
                            valueInput = null;
                            childBlock = null;
                        } else {
                            Log.d(TAG, "A statement input or child block was null.");
                            throw new BlocklyParserException();
                        }
                    }
                    else if (tagname.equalsIgnoreCase("comment")){
                        resultBlock.setComment(text);
                    }
                    else if (tagname.equalsIgnoreCase("next")) {
                        if (resultBlock.getNextConnection() == null
                                || childBlock.getPreviousConnection() == null) {
                            Log.d(TAG, "A connection was null.");
                            throw new BlocklyParserException();
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
        Log.d(TAG, "Reached the end of Block.fromXml. This should never happen.");
        throw new BlocklyParserException();
    }