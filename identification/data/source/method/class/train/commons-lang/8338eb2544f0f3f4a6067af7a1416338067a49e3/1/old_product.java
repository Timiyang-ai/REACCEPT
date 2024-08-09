public static Map getEnumMap(Class enumClass) {
        Map map = new LinkedHashMap();
        Iterator itr = EnumSet.allOf(enumClass).iterator();
        while(itr.hasNext()) { Enum enm = (Enum) itr.next(); map.put( enm.name(), enm ); }
        return map;
    }