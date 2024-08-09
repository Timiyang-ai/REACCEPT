public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');

        boolean first = true;
        for (int i = Bits.findFirst(bits, 0)
                ; i >= 0
                ; i = Bits.findFirst(bits, i + 1)) {
            if (!first) {
                sb.append(", ");
            }
            first = false;
            sb.append(i);
        }

        sb.append('}');

        return sb.toString();
    }