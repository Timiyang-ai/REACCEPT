public static synchronized String getWhereStringForFilter(Filter filter,
            StatementHelper sh) {
        for (FilterTranslator ft : filterTranslators) {
            if (ft.translatesFilter(filter)) {
                return ft.getWhereStringForFilter(filter, sh);
            }
        }
        return "";
    }