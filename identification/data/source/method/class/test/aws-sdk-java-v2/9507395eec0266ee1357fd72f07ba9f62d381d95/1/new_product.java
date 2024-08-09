public static AttributeValue toAttributeValue(Object value) {
        AttributeValue.Builder resultBuilder = AttributeValue.builder();
        if (value == null) {
            return resultBuilder.nul(Boolean.TRUE).build();
        } else if (value instanceof Boolean) {
            return resultBuilder.bool((Boolean) value).build();
        } else if (value instanceof String) {
            return resultBuilder.s((String) value).build();
        } else if (value instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) value;
            return resultBuilder.n(bd.toPlainString()).build();
        } else if (value instanceof Number) {
            return resultBuilder.n(value.toString()).build();
        } else if (value instanceof byte[]) {
            return resultBuilder.b(SdkBytes.fromByteArray((byte[]) value)).build();
        } else if (value instanceof ByteBuffer) {
            return resultBuilder.b(SdkBytes.fromByteBuffer((ByteBuffer) value)).build();
        } else if (value instanceof Set) {
            // default to an empty string set if there is no element
            @SuppressWarnings("unchecked")
            Set<Object> set = (Set<Object>) value;
            if (set.size() == 0) {
                resultBuilder.ss(new ArrayList<>());
                return resultBuilder.build();
            }
            Object element = set.iterator().next();
            if (element instanceof String) {
                @SuppressWarnings("unchecked")
                Set<String> ss = (Set<String>) value;
                resultBuilder.ss(new ArrayList<String>(ss));
            } else if (element instanceof Number) {
                @SuppressWarnings("unchecked")
                Set<Number> in = (Set<Number>) value;
                List<String> out = new ArrayList<String>(set.size());
                for (Number n : in) {
                    BigDecimal bd = InternalUtils.toBigDecimal(n);
                    out.add(bd.toPlainString());
                }
                resultBuilder.ns(out);
            } else if (element instanceof byte[]) {
                @SuppressWarnings("unchecked")
                Set<byte[]> in = (Set<byte[]>) value;
                List<SdkBytes> out = new ArrayList<>(set.size());
                for (byte[] buf : in) {
                    out.add(SdkBytes.fromByteArray(buf));
                }
                resultBuilder.bs(out);
            } else if (element instanceof ByteBuffer) {
                @SuppressWarnings("unchecked")
                Set<ByteBuffer> in = (Set<ByteBuffer>) value;
                List<SdkBytes> out = new ArrayList<>(set.size());
                for (ByteBuffer buf : in) {
                    out.add(SdkBytes.fromByteBuffer(buf));
                }
                resultBuilder.bs(out);
            } else {
                throw new UnsupportedOperationException("element type: "
                                                        + element.getClass());
            }
        } else if (value instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> in = (List<Object>) value;
            List<AttributeValue> out = new ArrayList<AttributeValue>();
            for (Object v : in) {
                out.add(toAttributeValue(v));
            }
            resultBuilder.l(out);
        } else if (value instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> in = (Map<String, Object>) value;
            Map<String, AttributeValue> attrs = new HashMap<>();
            for (Map.Entry<String, Object> e : in.entrySet()) {
                attrs.put(e.getKey(), toAttributeValue(e.getValue()));
                //resultBuilder.addMEntry(e.getKey(), toAttributeValue(e.getValue()));
            }
            resultBuilder.m(attrs);
        } else {
            throw new UnsupportedOperationException("value type: "
                                                    + value.getClass());
        }
        return resultBuilder.build();
    }