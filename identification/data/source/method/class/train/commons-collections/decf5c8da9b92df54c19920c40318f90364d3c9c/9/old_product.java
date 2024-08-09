public static Object getObject( Map map, Object key ) {
        if ( map != null ) {
            return map.get( key );
        }
        return null;
    }