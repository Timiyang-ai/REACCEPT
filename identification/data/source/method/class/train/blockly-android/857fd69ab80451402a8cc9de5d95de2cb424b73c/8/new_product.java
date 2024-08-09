public static Block fromXml(XmlPullParser parser, BlockFactory factory)
            throws XmlPullParserException, IOException, BlocklyParserException {
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

        String collapsedString = parser.getAttributeValue(null, "collapsed");
        if (collapsedString != null) {
            resultBlock.setCollapsed(Boolean.parseBoolean(collapsedString));
        }

        String deletableString = parser.getAttributeValue(null, "deletable");
        if (deletableString != null) {
            resultBlock.setDeletable(Boolean.parseBoolean(deletableString));
        }

        String disabledString = parser.getAttributeValue(null, "disabled");
        if (disabledString != null) {
            resultBlock.setDisabled(Boolean.parseBoolean(disabledString));
        }

        String editableString = parser.getAttributeValue(null, "editable");
        if (editableString != null) {
            resultBlock.setEditable(Boolean.parseBoolean(editableString));
        }

        String inputsInlineString = parser.getAttributeValue(null, "inline");
        if (inputsInlineString != null) {
            resultBlock.setInputsInline(Boolean.parseBoolean(inputsInlineString));
        }

        String movableString = parser.getAttributeValue(null, "movable");
        if (movableString != null) {
            resultBlock.setMovable(Boolean.parseBoolean(movableString));
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
        Block childShadow = null;
        Input valueInput = null;
        Input statementInput = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagname = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagname.equalsIgnoreCase("block")) {
                        childBlock = fromXml(parser, factory);
                    } else if (tagname.equalsIgnoreCase("shadow")) {
                        childShadow = fromXml(parser, factory);
                    } else if (tagname.equalsIgnoreCase("field")) {
                        fieldName = parser.getAttributeValue(null, "name");
                    } else if (tagname.equalsIgnoreCase("value")) {
                        valueInput = resultBlock.getInputByName(
                                parser.getAttributeValue(null, "name"));
                        if (valueInput == null) {
                            throw new BlocklyParserException("The value input was null at line "
                                    + parser.getLineNumber() + "!");
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
                    Connection parentConnection = null;

                    if (tagname.equalsIgnoreCase("block")) {
                        if (resultBlock == null) {
                            throw new BlocklyParserException(
                                    "Created a null block. This should never happen.");
                        }
                        return resultBlock;
                    } else if (tagname.equalsIgnoreCase("shadow")) {
                        if (resultBlock == null) {
                            throw new BlocklyParserException(
                                    "Created a null block. This should never happen.");
                        }
                        resultBlock.mIsShadow = true;
                        return resultBlock;
                    }else if (tagname.equalsIgnoreCase("field")) {
                        Field toSet = resultBlock.getFieldByName(fieldName);
                        if (toSet != null) {
                            if (!toSet.setFromString(text)) {
                                throw new BlocklyParserException(
                                        "Failed to set a field's value from XML.");
                            }
                        }
                    } else if (tagname.equalsIgnoreCase("comment")) {
                        resultBlock.setComment(text);
                    } else if (tagname.equalsIgnoreCase("value")) {
                        if (valueInput != null) {
                            parentConnection = valueInput.getConnection();
                            if (parentConnection == null) {
                                throw new BlocklyParserException("The input connection was null.");
                            }
                        } else {
                            throw new BlocklyParserException(
                                    "A value input was null.");
                        }
                    } else if (tagname.equalsIgnoreCase("statement")) {
                        if (statementInput != null) {
                            parentConnection = statementInput.getConnection();
                            if (parentConnection == null) {
                                throw new BlocklyParserException(
                                        "The statement connection was null.");
                            }
                        } else {
                            throw new BlocklyParserException(
                                    "A statement input was null.");
                        }
                    } else if (tagname.equalsIgnoreCase("next")) {
                        parentConnection = resultBlock.getNextConnection();
                        if (parentConnection == null) {
                            throw new BlocklyParserException("A next connection was null");
                        }
                    }
                    // If we finished a parent connection (statement, value, or next)
                    if (parentConnection != null) {
                        // Connect its child if one exists
                        if (childBlock != null) {
                            Connection childConnection = childBlock.getPreviousConnection();
                            if (childConnection == null) {
                                childConnection = childBlock.getOutputConnection();
                            }
                            if (childConnection == null) {
                                throw new BlocklyParserException(
                                        "The child block's connection was null.");
                            }
                            if (parentConnection.isConnected()) {
                                throw new BlocklyParserException("Duplicated " + tagname
                                        + " in block.");
                            }
                            parentConnection.connect(childConnection);
                        }
                        // Then connect its shadow if one exists
                        if (childShadow != null) {
                            Connection shadowConnection = childShadow.getPreviousConnection();
                            if (shadowConnection == null) {
                                shadowConnection = childShadow.getOutputConnection();
                            }
                            if (shadowConnection == null) {
                                throw new BlocklyParserException(
                                        "The shadow block connection was null.");
                            }
                            if (parentConnection.getShadowConnection() != null) {
                                throw new BlocklyParserException("Duplicated " + tagname
                                        + " in block.");
                            }
                            parentConnection.setShadowConnection(shadowConnection);
                            if (!parentConnection.isConnected()) {
                                // If there was no standard block connect the shadow
                                parentConnection.connect(shadowConnection);
                            }
                        }
                        // And clear out all the references for this tag
                        childBlock = null;
                        childShadow = null;
                        valueInput = null;
                        statementInput = null;
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