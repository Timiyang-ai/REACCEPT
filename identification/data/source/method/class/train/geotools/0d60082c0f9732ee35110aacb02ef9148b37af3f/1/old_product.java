public boolean equals(Object obj) {
        if (obj instanceof LiteralExpressionImpl) {
            LiteralExpressionImpl expLit = (LiteralExpressionImpl) obj;
            // This is a problem.  The Expression with type String of "2.0"
            // should be equals to the Expression with type Integer of "2.0"
            // Same thing with doubles and integers (as noted in the javadocs)

            // null handling
            if (this.literal == null) {
                return expLit.literal == null;
            } else if (expLit.literal == null) {
                return false;
            }

            // direct comparison if same type
            if (getExpressionType(this) == getExpressionType(expLit)) {
                if (this.literal.equals(expLit.literal)) {
                    return true;
                }
            }

            // do the conversion dance
            int expressionType = getExpressionType(this);
            if (expressionType == ExpressionType.LITERAL_GEOMETRY) {
                return ((Geometry) this.literal).equalsExact(expLit.evaluate(null, Geometry.class));
            } else if (expressionType == ExpressionType.LITERAL_INTEGER) {
                return ((Integer) this.literal).equals(expLit.evaluate(null, Integer.class));
            } else if (expressionType == ExpressionType.LITERAL_STRING) {
                return ((String) this.literal).equals(expLit.evaluate(null, String.class));
            } else if (expressionType == ExpressionType.LITERAL_DOUBLE) {
                return ((Double) this.literal).equals(expLit.evaluate(null, Double.class));
            } else if (expressionType == ExpressionType.LITERAL_LONG) {
                return ((Long) this.literal).equals(expLit.evaluate(null, Long.class));
            } else {
                // try to convert the other to the current type
                Object other = expLit.evaluate(null, this.literal.getClass());
                if (other != null) {
                    return other.equals(this.literal);
                }
                // converters might be one way, try the opposite
                other = expLit.getValue();
                Object converted = this.evaluate(null, other.getClass());
                if (converted != null) {
                    return converted.equals(other);
                }
                // final attemp with a string to string comparison
                String str1 = (String) this.evaluate(null, String.class);
                String str2 = (String) expLit.evaluate(null, String.class);
                return str1 != null && str2 != null && str1.equals(str2);
            }
        } else if (obj instanceof Literal) {
            // some other Literal implementation like ConstantExpression
            Literal other = (Literal) obj;
            return equals(new LiteralExpressionImpl(other.getValue()));
        } else {
            return false;
        }
    }