public static List<String> parseQuery(String query)
    {
        if (query == null) {
            return null;
        }

        //* enable to debug */ System.err.println("Parsing command queue:\n" + query);
        /*
         * Here begins the struggle between honoring comment starters and
         * honoring single quotes and honoring semicolons as statement separators.
         *
         * For example, whole-line comments are eliminated early -- assumed
         * never to be part of text literals, even though a text literal could
         * have been started on a prior line and could optionally be ended
         * with a quote on the current line and optionally followed by a
         * statement-ending semicolon all within the supposed comment line.
         */
        query = AnyWholeLineComments.matcher(query).replaceAll("");

        /*
         * replace all escaped single quotes with the #(SQL_PARSER_ESCAPE_SINGLE_QUOTE) tag
         */
        query = EscapedSingleQuote.matcher(query).replaceAll("#(SQL_PARSER_ESCAPE_SINGLE_QUOTE)");

        /*
         * Move all single quoted strings into the string fragments list, and do in place
         * replacements with numbered instances of the #(SQL_PARSER_STRING_FRAGMENT#[n]) tag
         *
         * WARNING: ENG-7594 This will find a quote (perhaps an informal
         * apostrophe) in an end-of-line comment and take it as the start
         * of a quoted string, hiding everything between it and the next
         * quote as literal text, including any semicolons or comment
         * starters in between.
         * Properly preserving semicolons and recognizing all comment
         * boundaries is tricky, especially in a way that preserves
         * quoted literals that contain "--", even literals that may be
         * started and/or terminated on a different line from the "--".
         * I (--paul) would find it comforting to rely on some interface
         * to HSQL parser technology for this,
         * The other possibility is to use SQLLexer.splitStatements
         * if it has already solved this problem.
         * And yet we don't yet know how compatible either of those is with
         * our intended free-form syntax for "exec" commands -- that may be
         * a bit TOO free form and may require tightening up before we can
         * find any reasonable solution.
         */
        Matcher stringFragmentMatcher = SingleQuotedString.matcher(query);
        ArrayList<String> stringFragments = new ArrayList<>();
        int i = 0;
        while (stringFragmentMatcher.find()) {
            stringFragments.add(stringFragmentMatcher.group());
            query = stringFragmentMatcher.replaceFirst("#(SQL_PARSER_STRING_FRAGMENT#" + i + ")");
            stringFragmentMatcher = SingleQuotedString.matcher(query);
            i++;
        }

        // Strip out inline comments
        // At this point, all the quoted strings have been pulled out of the
        // code mostly because they may contain semicolons.
        // They will not be restored until after the split.
        // So any user's quoted string containing ';' will be safe here.
        // OTOH, this next line MAY eliminate blocks of code after any
        // end-on-line comment that contains an unbalanced quote until
        // the following quote. ENG-7594
        // The reason for eliminating the comments here and now is to make sure that
        // comment text containing a semicolon does not cause an erroneous statement
        // split mid-comment.
        query = EndOfLineComment.matcher(query).replaceAll("");

        String[] sqlFragments = query.split("\\s*;+\\s*");

        ArrayList<String> queries = new ArrayList<>();
        for (String fragment : sqlFragments) {
            if (fragment.isEmpty()) {
                continue;
            }
            if (fragment.indexOf("#(SQL_PARSER_STRING_FRAGMENT#") > -1) {
                int k = 0;
                for (String strFrag : stringFragments) {
                    fragment = fragment.replace("#(SQL_PARSER_STRING_FRAGMENT#" + k + ")", strFrag);
                    k++;
                }
            }
            fragment = fragment.replace("#(SQL_PARSER_ESCAPE_SINGLE_QUOTE)", "''");
            queries.add(fragment);
        }
        return queries;
    }