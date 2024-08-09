static int validateAndGetArrayQuantifierFromCurrentToken(String token, String fullPath) {
        String quantifier = extractArgumentsFromAttributeName(token);
        if (quantifier == null) {
            throw new IllegalArgumentException("Malformed quantifier in " + fullPath);
        }
        int index = Integer.parseInt(quantifier);
        if (index < 0) {
            throw new IllegalArgumentException("Array index " + index + " cannot be negative in " + fullPath);
        }
        return index;
    }