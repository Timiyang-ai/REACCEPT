public static String encodePath(String path)
    {
        if (path==null || path.length()==0)
            return path;
        
        StringBuilder buf = encodePath(null,path);
        return buf==null?path:buf.toString();
    }