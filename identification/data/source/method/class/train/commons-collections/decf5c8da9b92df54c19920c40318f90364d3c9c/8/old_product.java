public static Map getMap( Map map, Object key ) {
        if ( map != null ) {
            Object answer = map.get( key );
            if ( answer != null && answer instanceof Map ) {
                return (Map) answer;
            }
        }
        return null;
    }