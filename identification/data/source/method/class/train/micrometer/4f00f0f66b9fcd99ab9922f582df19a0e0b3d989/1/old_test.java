    @Test
    void humanReadableByteCount() {
        LoggingMeterRegistry.Printer printer = registry.new Printer(DistributionSummary.builder("my.summary")
                .baseUnit(BaseUnits.BYTES)
                .register(registry));

        assertThat(printer.humanReadableBaseUnit(Double.NaN)).isEqualTo("NaN B");
        assertThat(printer.humanReadableBaseUnit(1.0)).isEqualTo("1 B");
        assertThat(printer.humanReadableBaseUnit(1024)).isEqualTo("1 KiB");
        assertThat(printer.humanReadableBaseUnit(1024 * 1024 * 2.5678976654)).isEqualTo("2.567898 MiB");
    }