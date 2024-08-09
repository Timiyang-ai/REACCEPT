public static boolean filter(String path, Collection<String> includes, Collection<String> excludes) {
        includes = (includes == null ? Collections.<String> emptyList() : includes);
        excludes = (excludes == null ? Collections.<String> emptyList() : excludes);

        if (includes.isEmpty() && excludes.isEmpty()) {
            return true;
        }

        if (Regex.simpleMatch(excludes, path)) {
            return false;
        }

        boolean exactIncludeMatch = false; // true if the current position was specifically mentioned
        boolean pathIsPrefixOfAnInclude = false; // true if potentially a sub scope can be included
        if (includes.isEmpty()) {
            // implied match anything
            exactIncludeMatch = true;
        }
        else {
            for (String include : includes) {
                // check for prefix matches as well to see if we need to zero in, something like: obj1.arr1.* or *.field
                // note, this does not work well with middle matches, like obj1.*.obj3
                if (include.charAt(0) == '*') {
                    if (Regex.simpleMatch(include, path)) {
                        exactIncludeMatch = true;
                        break;
                    }
                    pathIsPrefixOfAnInclude = true;
                    continue;
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

        if (!(pathIsPrefixOfAnInclude || exactIncludeMatch)) {
            // skip subkeys, not interesting.
            return false;
        }

        else if (exactIncludeMatch) {
            return true;
        }
        return false;
    }