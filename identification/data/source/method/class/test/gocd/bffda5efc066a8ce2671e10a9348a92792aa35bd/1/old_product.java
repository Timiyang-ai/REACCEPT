public static String quoteArgument(String argument) {
        if (QUOTED_STRING.matcher(argument).matches() || !UNESCAPED_SPACE_OR_QUOTES.matcher(argument).find()) {
            // assume the argument is well-formed if it's already quoted or if there are no unescaped spaces or quotes
            return argument;
        }

        return String.format("\"%s\"", UNESCAPED_DOUBLE_QUOTE.matcher(argument).replaceAll(Matcher.quoteReplacement("\\") + "$1"));
    }