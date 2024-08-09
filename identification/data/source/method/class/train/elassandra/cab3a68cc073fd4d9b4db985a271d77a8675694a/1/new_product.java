@Override
    public QueryBuilder fromXContent(QueryParseContext parseContext) throws IOException {
        XContentParser parser = parseContext.parser();
        List<String> ids = new ArrayList<>();
        List<String> types = new ArrayList<>();
        float boost = AbstractQueryBuilder.DEFAULT_BOOST;
        String queryName = null;

        String currentFieldName = null;
        XContentParser.Token token;
        boolean idsProvided = false;
        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
            if (token == XContentParser.Token.FIELD_NAME) {
                currentFieldName = parser.currentName();
            } else if (token == XContentParser.Token.START_ARRAY) {
                if ("values".equals(currentFieldName)) {
                    idsProvided = true;
                    while ((token = parser.nextToken()) != XContentParser.Token.END_ARRAY) {
                        if ((token == XContentParser.Token.VALUE_STRING) ||
                                (token == XContentParser.Token.VALUE_NUMBER)) {
                            String id = parser.textOrNull();
                            if (id == null) {
                                throw new QueryParsingException(parseContext, "No value specified for term filter");
                            }
                            ids.add(id);
                        } else {
                            throw new QueryParsingException(parseContext, "Illegal value for id, expecting a string or number, got: "
                                    + token);
                        }
                    }
                } else if ("types".equals(currentFieldName) || "type".equals(currentFieldName)) {
                    while ((token = parser.nextToken()) != XContentParser.Token.END_ARRAY) {
                        String value = parser.textOrNull();
                        if (value == null) {
                            throw new QueryParsingException(parseContext, "No type specified for term filter");
                        }
                        types.add(value);
                    }
                } else {
                    throw new QueryParsingException(parseContext, "[ids] query does not support [" + currentFieldName + "]");
                }
            } else if (token.isValue()) {
                if ("type".equals(currentFieldName) || "_type".equals(currentFieldName)) {
                    types = ImmutableList.of(parser.text());
                } else if ("boost".equals(currentFieldName)) {
                    boost = parser.floatValue();
                } else if ("_name".equals(currentFieldName)) {
                    queryName = parser.text();
                } else {
                    throw new QueryParsingException(parseContext, "[ids] query does not support [" + currentFieldName + "]");
                }
            }
        }
        if (!idsProvided) {
            throw new QueryParsingException(parseContext, "[ids] query, no ids values provided");
        }

        IdsQueryBuilder query = new IdsQueryBuilder(types.toArray(new String[types.size()]));
        query.addIds(ids.toArray(new String[ids.size()]));
        query.boost(boost).queryName(queryName);
        return query;
    }