public boolean resolveExpression(FeedMetadata metadata, NifiProperty property) {
        String value = property.getValue();
        StringBuffer sb = null;

        if (StringUtils.isNotBlank(value)) {
            Pattern variablePattern = Pattern.compile("\\$\\{(.*?)\\}");
            Matcher matchVariablePattern = variablePattern.matcher(value);

            while (matchVariablePattern.find()) {
                if (sb == null) {
                    sb = new StringBuffer();
                }
                String group = matchVariablePattern.group();
                int groupCount = matchVariablePattern.groupCount();
                if (groupCount == 1) {

                    String variable = matchVariablePattern.group(1);
                    //lookup the variable
                    //first look at configuration properties
                    String resolvedValue = getConfigurationPropertyValue(property, variable);
                    if (resolvedValue != null) {
                        matchVariablePattern.appendReplacement(sb, Matcher.quoteReplacement(resolvedValue));
                    } else {
                        try {
                            resolvedValue = getMetadataPropertyValue(metadata, variable);
                            matchVariablePattern.appendReplacement(sb, Matcher.quoteReplacement(resolvedValue));

                        } catch (Exception e) {
                        }
                    }
                }
            }
            if (sb != null) {
                matchVariablePattern.appendTail(sb);
                property.setValue(StringUtils.trim(sb.toString()));
            }
        }
        return sb != null;

    }