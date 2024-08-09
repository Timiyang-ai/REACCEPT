@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimpleTypedNameDeclaration other = (SimpleTypedNameDeclaration) obj;
        if (type == null) {
            if (other.type == Object.class)
                return true;
            if (other.type != null)
                return false;
        }
        if (type != null && (type.equals(other.type) || type == Object.class))
            return true;

        // if the type is given, only compare the type and don't care about the type image
        if (type != null && other.type != null && (type.isAssignableFrom(other.type) || other.type.isAssignableFrom(type)))
            return true;

        if (typeImage == null) {
            if (other.typeImage != null)
                return false;
        } else if (!typeImage.equals(other.typeImage)) {
            // consider auto-boxing
            if (other.typeImage != null) {
                String lcType = typeImage.toLowerCase();
                String otherLcType = other.typeImage.toLowerCase();
                if (primitiveTypes.contains(lcType) && primitiveTypes.contains(otherLcType)) {
                    if (lcType.equals(otherLcType)) {
                        return true;
                    } else if ((lcType.equals("char") || lcType.equals("character"))
                            && (otherLcType.equals("char") || otherLcType.equals("character"))) {
                        return true;
                    } else if ((lcType.equals("int") || lcType.equals("integer")) && (
                            otherLcType.equals("int")
                            || otherLcType.equals("integer")
                            || otherLcType.equals("short")
                            || otherLcType.equals("char")
                            || otherLcType.equals("character")
                            || otherLcType.equals("byte")
                            )) {
                        return true;
                    } else if (lcType.equals("double") && (
                            otherLcType.equals("float")
                            || otherLcType.equals("int")
                            || otherLcType.equals("integer")
                            || otherLcType.equals("long")
                            )) {
                        return true;
                    } else if (lcType.equals("float") && (
                            otherLcType.equals("int")
                            || otherLcType.equals("integer")
                            || otherLcType.equals("long")
                            )) {
                        return true;
                    } else if (lcType.equals("long") && (
                            otherLcType.equals("int")
                            || otherLcType.equals("integer")
                            || otherLcType.equals("char")
                            || otherLcType.equals("character")
                            )) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }