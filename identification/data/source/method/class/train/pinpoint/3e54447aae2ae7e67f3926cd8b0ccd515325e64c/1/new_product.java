static String toJvmSignature(String javaType) {
        if (javaType == null) {
            throw new NullPointerException("javaType must not be null");
        }
        if (javaType.isEmpty()) {
            throw new IllegalArgumentException("invalid javaType. \"\"");
        }

        final int javaObjectArraySize = getJavaObjectArraySize(javaType);
        final int javaArrayLength = javaObjectArraySize * 2;
        String pureJavaType;
        if (javaObjectArraySize != 0) {
            // pure java
            pureJavaType = javaType.substring(0, javaType.length() - javaArrayLength);
        } else {
            pureJavaType = javaType;
        }
        final String signature = PRIMITIVE_JAVA_TO_JVM.get(pureJavaType);
        if (signature != null) {
            // primitive type
            return appendJvmArray(signature, javaObjectArraySize);
        }
        return toJvmObject(javaObjectArraySize, pureJavaType);

    }