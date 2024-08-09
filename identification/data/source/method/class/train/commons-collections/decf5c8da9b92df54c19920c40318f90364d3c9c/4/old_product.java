public static Map getMap( Map map, Object key, Map defaultValue ) {
        Map answer = getMap( map, key );
        if ( answer == null ) {
            answer = defaultValue;
        }
        return answer;
    }