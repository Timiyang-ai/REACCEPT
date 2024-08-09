@DELETE
    @Path("{mep_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMep(@PathParam("md_name") String mdName,
            @PathParam("ma_name") String maName,
            @PathParam("mep_id") short mepIdShort) {
        log.debug("DELETE called for MEP " + mdName + "/" + maName + "/" + mepIdShort);
        try {
            MdId mdId = MdIdCharStr.asMdId(mdName);
            MaIdShort maId = MaIdCharStr.asMaId(maName);
            boolean deleted = get(CfmMepService.class)
                    .deleteMep(mdId, maId, MepId.valueOf(mepIdShort));
            if (!deleted) {
                return Response.notModified(mdName + "/" + maName + "/" +
                        mepIdShort + " did not exist").build();
            } else {
                return ok("{ \"success\":\"deleted " + mdName + "/" + maName +
                        "/" + mepIdShort + "\" }").build();
            }
        } catch (CfmConfigException e) {
            log.error("Delete Mep {} failed because of exception ",
                    mdName + "/" + maName + "/" + mepIdShort, e);
            return Response.serverError().entity("{ \"failure\":\"" +
                    e.toString() + "\" }").build();
        }
    }