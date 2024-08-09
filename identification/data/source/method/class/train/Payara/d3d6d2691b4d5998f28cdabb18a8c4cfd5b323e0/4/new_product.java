static Properties convertStringToProperties(String propsString, char sep) {
        final Properties properties = new Properties();
        if (propsString != null) {
            ParamTokenizer stoken = new ParamTokenizer(propsString, sep);
            while (stoken.hasMoreTokens()) {
                String token = stoken.nextTokenKeepEscapes();
                final ParamTokenizer nameTok = new ParamTokenizer(token, '=');
                String name = null, value = null;
                if (nameTok.hasMoreTokens()){
                    name = nameTok.nextToken().trim();
                }
                if (nameTok.hasMoreTokens()) {
                    value = nameTok.nextToken();
                }
                if (name == null) {      // probably "::"
                    throw new IllegalArgumentException(
                        localStrings.getLocalString("PropertyMissingName",
                        "Invalid property syntax, missing property name",
                        propsString));
                }
                if (value == null) {
                    throw new IllegalArgumentException(
                        localStrings.getLocalString("PropertyMissingValue",
                        "Invalid property syntax, missing property value",
                        token));
                }
                if (nameTok.hasMoreTokens()) {
                    String secPart = token.split("=", 2)[1];
                    //Creates a string with any env var references removed, then checks if there is an equals in there
                    String outside = secPart.replaceAll("\\$\\{.+}", "");
                    if (outside.contains("=")){
                        throw new IllegalArgumentException(
                        localStrings.getLocalString("PropertyExtraEquals",
                        "Invalid property syntax, \"=\" in value", token));
                    } else {
                        value = secPart;
                    }
                    
                    
                }
                properties.setProperty(name, value);
            }
        }
        return properties;
    }