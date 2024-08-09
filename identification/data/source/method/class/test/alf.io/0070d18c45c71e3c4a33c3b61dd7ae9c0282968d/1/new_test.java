    @Test
    public void buildModelForTicketEmail() throws Exception {
        Pair<ZonedDateTime, ZonedDateTime> dates = getDates();
        Map<String, Object> model = TemplateResource.buildModelForTicketEmail(organization, event, ticketReservation, "Https://test", ticket, ticketCategory, Map.of());
        assertEquals(dates.getLeft(), model.get("validityStart"));
        assertEquals(dates.getRight(), model.get("validityEnd"));
    }