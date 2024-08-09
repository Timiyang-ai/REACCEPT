public static String encodePath(String path)
    {
        if (path==null || path.length()==0)
            return path;
        
        StringBuilder buf = encodePath(null,path,0);
        return buf==null?path:buf.toString();
    }