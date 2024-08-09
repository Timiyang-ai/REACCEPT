public static char boolsToHexDigit(boolean[] src, int srcPos) {
        if (src.length > srcPos + 3 && src[srcPos + 3]) {
            if (src.length > srcPos + 2 && src[srcPos + 2]) {
                if (src.length > srcPos + 1 && src[srcPos + 1]) {
                    if (src[srcPos]) return 'f';
                    else return 'e';
                } else {
                    if (src[srcPos]) return 'd';
                    else return 'c';
                }
            } else {
                if (src.length > srcPos + 1 && src[srcPos + 1]) {
                    if (src[srcPos]) return 'b';
                    else return 'a';
                } else {
                    if (src[srcPos]) return '9';
                    else return '8';
                }
            }
        } else {
            if (src.length > srcPos + 2 && src[srcPos + 2]) {
                if (src.length > srcPos + 1 && src[srcPos + 1]) {
                    if (src[srcPos]) return '7';
                    else return '6';
                } else {
                    if (src[srcPos]) return '5';
                    else return '4';
                }
            } else {
                if (src.length > srcPos + 1 && src[srcPos + 1]) {
                    if (src[srcPos]) return '3';
                    else return '2';
                } else {
                    if (src[srcPos]) return '1';
                    else return '0';
                }
            }
        }
    }