    public void listErrataChannelPackages() {
        try {
            Channel chan = ChannelTestUtils.createBaseChannel(user);
            Errata e = ErrataFactoryTest.createTestErrata(user.getId());
            Package p = PackageTest.createTestPackage(user.getOrg());
            chan.getErratas().add(e);
            chan.getPackages().add(p);
            e.getPackages().add(p);
            ChannelFactory.save(chan);

            chan = (Channel) TestUtils.saveAndReload(chan);
            e = (Errata) TestUtils.saveAndReload(e);
            p = (Package) TestUtils.saveAndReload(p);


            List<Long> list = ErrataFactory.listErrataChannelPackages(chan.getId(),
                    e.getId());
            assertContains(list, p.getId());

        }
        catch (Exception e) {
            assertTrue(false);
        }
    }