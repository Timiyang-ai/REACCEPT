@Test
    public void testGetMembers() throws TwilioRestException {

        setupMocks();
        stub(
                client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
                        + ".json"), Matchers.eq("GET"), Matchers.any(Map.class)))
                .toReturn(resp);

        TwilioRestResponse membersresp = mock(TwilioRestResponse.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        stub(membersresp.toMap()).toReturn(map);
        stub(membersresp.getParser()).toReturn(new JsonResponseParser());
        
        
          formattedDate = dateFormat.format(new Date());
        map.put("next_page_uri", "http://next.page.uri/");
        map.put("start", "1");
        map.put("end", "1");
        map.put("total", "1");
        map.put("page", "1");
        map.put("num_pages", "1");
               stub(
                client.safeRequest(Matchers.eq("/2010-04-01/Accounts/" + accountSid + "/Queues/" + queueSid
                        + "/Members.json"), Matchers.eq("GET"), Matchers.any(Map.class)))
                .toReturn(membersresp);
        Queue q = new Queue(client, queueSid);
        q.setRequestAccountSid(accountSid);
        MemberList m = q.getMembers();
        Iterator<Member> memiter = m.iterator();
        assertTrue(memiter.hasNext());
    }