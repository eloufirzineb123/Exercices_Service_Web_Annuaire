/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m2i.annuaireex;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/personnes")
public class AnnuareResource {
    
       
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Personne> getPersonnes(@Context HttpServletRequest request) {
        
        Annuaire annuaire = (Annuaire)request.getSession().getAttribute("annuaire");
        
        if(annuaire == null) {
            annuaire = new Annuaire();
            request.getSession().setAttribute("annuaire", annuaire);
        }
        
        return annuaire.getPersonnes();
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response postPersonne(Personne newPersonne, @Context HttpServletRequest request) {
        
        Annuaire annuaire = (Annuaire)request.getSession().getAttribute("annuaire");
           
        if(annuaire == null) {
            annuaire = new Annuaire();    
            request.getSession().setAttribute("annuaire", annuaire);
        }
        
        Personne personne = annuaire.addPersonne(newPersonne);
        
        return Response.status(Response.Status.CREATED).entity(personne).build();
    }
    
    @Path("/{id}")
    @GET()
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getPersonne(@PathParam("id") long personneId, @Context HttpServletRequest request) {
        
        Annuaire annuaire = (Annuaire)request.getSession().getAttribute("annuaire");
        
        if(annuaire == null) {
            annuaire = new Annuaire();       
        }
        
        Personne personne = annuaire.getPersonne(personneId);
        
        if(personne != null){
            //return Response.status(Response.Status.OK).entity(personne).build();
            return Response.ok(personne).build();
        }
        else {
            return Response.status(404).entity("Cette Personne n'existe pas").build();
        }
    }
    
    @Path("/{id}")
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    public void putPersonne(Personne personne, @Context HttpServletRequest request){
        Annuaire annuaire = (Annuaire)request.getSession().getAttribute("annuaire");
        
        if(annuaire == null) {
            annuaire = new Annuaire();       
        }
        
        annuaire.updatePersonne(personne);
        request.getSession().setAttribute("annuaire", annuaire);
    }
    
    
    @Path("/{id}")
    @DELETE()
    public void deletePersonnne(@PathParam("id") long personneId, @Context HttpServletRequest request){
        Annuaire annuaire = (Annuaire)request.getSession().getAttribute("annuaire");
        
        if(annuaire == null) {
            annuaire = new Annuaire();       
        }
        
        annuaire.deletePersonne(personneId);
        request.getSession().setAttribute("annuaire", annuaire);
    }
}