public static Result filter(String path, Collection<NumberedInclude> includes, Collection<String> excludes, boolean allowPartialMatches) {
        includes = (includes == null ? Collections.<NumberedInclude> emptyList() : includes);
        excludes = (excludes == null ? Collections.<String> emptyList() : excludes);

        if (includes.isEmpty() && excludes.isEmpty()) {
            return INCLUDED;
        }

        if (Regex.simpleMatch(excludes, path)) {
            return EXCLUDED;
        }

        boolean exactIncludeMatch = false; // true if the current position was specifically mentioned
        boolean pathIsPrefixOfAnInclude = false; // true if potentially a sub scope can be included

        NumberedInclude matchedInclude = null;

        if (includes.isEmpty()) {
            // implied match anything
            exactIncludeMatch = true;
        }
        else {
            for (NumberedInclude filter : includes) {
                matchedInclude = filter;
                String include = filter.filter;

                // check for prefix matches as well to see if we need to zero in, something like: obj1.arr1.* or *.field
                // note, this does not work well with middle matches, like obj1.*.obj3
                if (include.charAt(0) == '*') {
                    if (Regex.simpleMatch(include, path)) {
                        exactIncludeMatch = true;
                        break;
                    }
//                    pathIsPrefixOfAnInclude = true;
//                    continue;
                }
                if (include.startsWith(path)) {
                    if (include.length() == path.length()) {
                        exactIncludeMatch = true;
                        break;
                    }
                    else if (include.length() > path.length() && include.charAt(path.length()) == '.') {
                        // include might may match deeper paths. Dive deeper.
                        pathIsPrefixOfAnInclude = true;
                        continue;
                    }
                }
                if (Regex.simpleMatch(include, path)) {
                    exactIncludeMatch = true;
                    break;
                }
            }
        }

        // if match or part of the path (based on the passed param)
        if (exactIncludeMatch || (allowPartialMatches && pathIsPrefixOfAnInclude)) {
            return (matchedInclude != null ? new Result(true, matchedInclude.depth) : INCLUDED);
        }

        return EXCLUDED;
    }