private String expand(String str) {
        if (str == null)
            return null;
        
        final StringBuffer sb = new StringBuffer();
        final Matcher m = VAR_PATTERN.matcher(str);
        while (m.find()) {
            assert m.group(1) != null ^ m.group(2) != null;
            final String var = m.group(1) != null ? m.group(1) : m.group(2);
            m.appendReplacement(sb, Matcher.quoteReplacement(getVarValue(var)));
        }
        m.appendTail(sb);
        str = sb.toString();
        
        // str = expandCommandLinePath(str);
        str = str.replace('/', FILE_SEPARATOR_CHAR);
        return str;
    }