public static boolean endsWithIgnoreCase(String a, String b, int posA) {
        int  posB; // Position in b.
        char cA;   // Current character in a.
        char cB;   // Current character in b.

        // Checks whether there's any point in testing the strings.
        if(posA < (posB = b.length()))
            return false;

        // Loops until we've tested the whole of b.
        while(posB > 0) {

            // Works on lower-case characters only. 
            if(!Character.isLowerCase(cA = a.charAt(--posA)))
                cA = Character.toLowerCase(cA);
            if(!Character.isLowerCase(cB = b.charAt(--posB)))
                cB = Character.toLowerCase(cB);
            if(cA != cB)
                return false;
        }
        return true;
    }