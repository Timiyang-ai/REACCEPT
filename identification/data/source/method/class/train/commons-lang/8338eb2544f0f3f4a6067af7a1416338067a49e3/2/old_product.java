public static Map<String, Enum<?>> getEnumMap(Class enumClass) {
        Map<String, Enum<?>> map = new LinkedHashMap<String, Enum<?>>();
        Iterator<? extends Enum<?>> itr = EnumSet.allOf(enumClass).iterator();
        while(itr.hasNext()) { Enum<?> enm = itr.next(); map.put( enm.name(), enm ); }
        return map;
    }