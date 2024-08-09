public static int getNumberOfColumns(FormEntryPrompt formEntryPrompt, Context context) {
        int numColumns = 1;
        String appearance = getSanitizedAppearanceHint(formEntryPrompt);
        if (appearance.contains(COLUMNS_N) || appearance.contains(COMPACT_N)) {
            try {
                String columnsAppearance = appearance.contains(COLUMNS_N) ? COLUMNS_N : COMPACT_N;
                try {
                    appearance =
                            appearance.substring(appearance.indexOf(columnsAppearance));
                    int idx = appearance.indexOf(columnsAppearance);
                    if (idx != -1) {
                        String substringFromNumColumns = appearance.substring(idx + columnsAppearance.length());
                        numColumns = Integer.parseInt(substringFromNumColumns.substring(0, substringFromNumColumns.contains(" ")
                                ? substringFromNumColumns.indexOf(' ')
                                : substringFromNumColumns.length()));

                        if (numColumns < 1) {
                            numColumns = 1;
                        }
                    }
                } catch (Exception e) {
                    Timber.e(EXCEPTION_PARSING_COLUMNS);
                }
            } catch (Exception e) {
                Timber.e(EXCEPTION_PARSING_COLUMNS);
            }
        } else if (appearance.contains(COLUMNS)) {
            switch (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) {
                case Configuration.SCREENLAYOUT_SIZE_SMALL:
                    numColumns = 2;
                    break;
                case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                    numColumns = 3;
                    break;
                case Configuration.SCREENLAYOUT_SIZE_LARGE:
                    numColumns = 4;
                    break;
                case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                    numColumns = 5;
                    break;
                default:
                    numColumns = 3;
            }
        }
        return numColumns;
    }