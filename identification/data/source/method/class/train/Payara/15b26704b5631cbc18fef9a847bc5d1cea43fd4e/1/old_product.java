public static String sanitizePaths(String pathsString) {
        if (!ok(pathsString))
            return pathsString;

        try {
            String[] paths = pathsString.split(File.pathSeparator);
            StringBuilder sb = new StringBuilder();
            Set<String> pathsSet = new HashSet<String>();
            List<String> pathsList = new LinkedList<String>();

            for (int i = 0; i < paths.length; i++) {
                String path = paths[i];

                // ignore empty path elements.  E.g. "c:/foo;;;;;;;" should become "C:/foo"
                // not "c:/foo;thisdir;thisdir;thisdir etc"
                if (!ok(path))
                    continue;

                // pathsSet is only here for removing duplicates.  We need the
                // List to maintain the original order!
                path = SmartFile.sanitize(path);

                if (pathsSet.add(path))
                    pathsList.add(path);
            }

            boolean firstElement = true;
            for (String path : pathsList) {
                if (firstElement)
                    firstElement = false;
                else
                    sb.append(File.pathSeparator);

                sb.append(path);
            }
            return sb.toString();
        }
        catch (Exception e) {
            return pathsString;
        }
    }