public static int hexDigitToInt(char hexDigit) {
        switch (hexDigit) {
        case '0':
            return 0;
        case '1':
            return 1;
        case '2':
            return 2;
        case '3':
            return 3;
        case '4':
            return 4;
        case '5':
            return 5;
        case '6':
            return 6;
        case '7':
            return 7;
        case '8':
            return 8;
        case '9':
            return 9;
        case 'a':// fall through
        case 'A':
            return 10;
        case 'b':// fall through
        case 'B':
            return 11;
        case 'c':// fall through
        case 'C':
            return 12;
        case 'd':// fall through
        case 'D':
            return 13;
        case 'e':// fall through
        case 'E':
            return 14;
        case 'f':// fall through
        case 'F':
            return 15;
        default:
            throw new IllegalArgumentException("Cannot interpret '"
                + hexDigit
                + "' as a hexadecimal digit");
        }
    }