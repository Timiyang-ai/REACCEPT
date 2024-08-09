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
            if (other.type != null)
                return false;
        }
        if (type != null && type.equals(other.type))
            return true;

        // if the type is given, only compare the type and don't care about the type image
        if (type != null && other.type != null && (type.isAssignableFrom(other.type) || other.type.isAssignableFrom(type)))
            return true;

        if (typeImage == null) {
            if (other.typeImage != null)
                return false;
        } else if (!typeImage.equals(other.typeImage))
            return false;
        return true;
    }