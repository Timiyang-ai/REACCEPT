public static boolean[] hexDigitToBoolArray(char hexDigit) {
        switch (hexDigit) {
        case '0':
            return new boolean[]{false, false, false, false};
        case '1':
            return new boolean[]{true, false, false, false};
        case '2':
            return new boolean[]{false, true, false, false};
        case '3':
            return new boolean[]{true, true, false, false};
        case '4':
            return new boolean[]{false, false, true, false};
        case '5':
            return new boolean[]{true, false, true, false};
        case '6':
            return new boolean[]{false, true, true, false};
        case '7':
            return new boolean[]{true, true, true, false};
        case '8':
            return new boolean[]{false, false, false, true};
        case '9':
            return new boolean[]{true, false, false, true};
        case 'a':// fall through
        case 'A':
            return new boolean[]{false, true, false, true};
        case 'b':// fall through
        case 'B':
            return new boolean[]{true, true, false, true};
        case 'c':// fall through
        case 'C':
            return new boolean[]{false, false, true, true};
        case 'd':// fall through
        case 'D':
            return new boolean[]{true, false, true, true};
        case 'e':// fall through
        case 'E':
            return new boolean[]{false, true, true, true};
        case 'f':// fall through
        case 'F':
            return new boolean[]{true, true, true, true};
        default:
            throw new IllegalArgumentException("Cannot interpret '"
                + hexDigit
                + "' as a hexadecimal digit");
        }
    }