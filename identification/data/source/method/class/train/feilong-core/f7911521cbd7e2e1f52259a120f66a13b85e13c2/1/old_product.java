public static Locale toLocale(Object locale){
        if (null == locale){
            return null;
        }
        if (locale instanceof Locale){
            return (Locale) locale;
        }
        if (locale instanceof String){
            return LocaleUtils.toLocale((String) locale);
        }
        throw new UnsupportedOperationException("locale2 not support!");
    }