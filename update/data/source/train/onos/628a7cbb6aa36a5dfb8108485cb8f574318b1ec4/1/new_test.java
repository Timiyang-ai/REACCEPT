@Test
    public void testUnsubscribePreCommit() {

        sampleSubscribe();

        InstancePortAdminService service = new TestInstancePortAdminService();

        target.unsubscribePreCommit(PORT_ID_1, OPENSTACK_PORT_PRE_REMOVE, service, CLASS_NAME_1);
        target.unsubscribePreCommit(PORT_ID_2, OPENSTACK_PORT_PRE_REMOVE, service, CLASS_NAME_2);

        assertEquals(0, target.subscriberCountByEventType(PORT_ID_1, OPENSTACK_PORT_PRE_REMOVE));
        assertEquals(1, target.subscriberCountByEventType(PORT_ID_2, OPENSTACK_PORT_PRE_REMOVE));

        assertEquals(0, target.subscriberCount(PORT_ID_1));
        assertEquals(2, target.subscriberCount(PORT_ID_2));
    }