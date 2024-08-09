public static String canonicalPath(String path)
    {
        if (path == null || path.isEmpty() || !path.contains("."))
            return path;

        if(path.startsWith("/.."))
            return null;

        List<String> directories = new ArrayList<>();
        Collections.addAll(directories, __PATH_SPLIT.split(path));
        
        for(ListIterator<String> iterator = directories.listIterator(); iterator.hasNext();)
        {
            switch (iterator.next()) {
                case "./":
                case ".":
                    if (iterator.hasNext() && directories.get(iterator.nextIndex()).equals("/"))
                        break;

                    iterator.remove();
                    break;
                case "../":
                case "..":
                    if(iterator.previousIndex() == 0)
                        return null;

                    iterator.remove();
                    if(iterator.previous().equals("/") && iterator.nextIndex() == 0)
                        return null;

                    iterator.remove();
                    break;
            }
        }

        return String.join("", directories);
    }