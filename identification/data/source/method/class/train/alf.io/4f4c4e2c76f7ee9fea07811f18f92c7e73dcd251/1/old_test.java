    @Test
    public void buildModelForTicketPDF() throws Exception {
        Pair<ZonedDateTime, ZonedDateTime> dates = getDates();
        when(ticket.ticketCode(anyString())).thenReturn("abcd");
        when(event.getPrivateKey()).thenReturn("key");
        Map<String, Object> model = TemplateResource.buildModelForTicketPDF(organization, event, ticketReservation, ticketCategory, ticket, Optional.empty(), "abcd", Collections.emptyMap());
        assertEquals(dates.getLeft(), model.get("validityStart"));
        assertEquals(dates.getRight(), model.get("validityEnd"));
    }