    private StubFlyweight newFlyweight() {
        return mapper.newFlyweight(StubFlyweight.class, "StubTemplate.java", startAddress);
    }