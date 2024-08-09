static Properties convertStringToProperties(String propsString, char sep) {
        final Properties properties = new Properties();
        if (propsString != null) {
            ParamTokenizer stoken = new ParamTokenizer(propsString, sep);
            while (stoken.hasMoreTokens()) {
                String token = stoken.nextTokenKeepEscapes();
                final ParamTokenizer nameTok = new ParamTokenizer(token, '=');
                String name = null, value = null;
                if (nameTok.hasMoreTokens())
                    name = nameTok.nextToken();
                if (nameTok.hasMoreTokens())
                    value = nameTok.nextToken();
                if (name == null)       // probably "::"
                    throw new IllegalArgumentException(
                        localStrings.getLocalString("PropertyMissingName",
                        "Invalid property syntax, missing property name",
                        propsString));
                if (value == null)
                    throw new IllegalArgumentException(
                        localStrings.getLocalString("PropertyMissingValue",
                        "Invalid property syntax, missing property value",
                        token));
                if (nameTok.hasMoreTokens())
                    throw new IllegalArgumentException(
                        localStrings.getLocalString("PropertyExtraEquals",
                        "Invalid property syntax, \"=\" in value", token));
                properties.setProperty(name, value);
            }
        }
        return properties;
    }