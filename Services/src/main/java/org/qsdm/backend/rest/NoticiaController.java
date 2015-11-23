package org.qsdm.backend.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.qsdm.backend.dao.NoticiaRepository;
import org.qsdm.backend.dao.NoticiasProfesionesRepository;
import org.qsdm.backend.dao.ProfesionesRepository;
import org.qsdm.backend.model.Noticias;
import org.qsdm.backend.model.NoticiasProfesion;
import org.qsdm.backend.model.Profesiones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/service/noticias")
@Api(value = "Noticias", description = "Noticias API")
public class NoticiaController extends AbstractRestHandler {

	@Autowired
    private NoticiasProfesionesRepository noticiasProfesionesRepository;
	@Autowired
    private NoticiaRepository noticiaRepository;
    
    @Autowired
    private ProfesionesRepository profesionesRepository;
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Noticia resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createNoticia(
    		@RequestParam("emailUsuarioPublicacion") String emailUsuarioPublicacion,
    		@RequestParam("titulo") String titulo,
     		@RequestParam("profesiones") String profesiones,
    		@RequestParam("descripcion") String descripcion,
    		@RequestParam("urlFuente") String urlFuente,
                                  HttpServletRequest request, HttpServletResponse response) {
    	
    	
    	Noticias noticia=new Noticias();
    	noticia.setDescripcion(descripcion);
    	noticia.setFechaAlta(new Date());
    	noticia.setTitulo(titulo);
    	noticia.setUsuarioPublicacion(emailUsuarioPublicacion);
    	noticia.setUrlFuente(urlFuente);
    	
		
   
    	
    	Noticias createdNoticia=noticiaRepository.save(noticia);
    	Set<NoticiasProfesion> noticiasProfesionSet=new HashSet<NoticiasProfesion>();
    	if(profesiones!=null && profesiones.length()>0){
    		String[] profs = profesiones.split(":");
    		List<String>profesionesIdList=new ArrayList<String>();
    		
    		for(int z=0;z<profs.length;z++){
    			profesionesIdList.add(profs[z]);
    			
    		}
    		Iterable<Profesiones> profsinDB = profesionesRepository.findProfesionesByCods(profesionesIdList);
    		Iterator<Profesiones> it = profsinDB.iterator();
    		while(it.hasNext()){
    			Profesiones prof = it.next();
    			NoticiasProfesion nuevaProfAsig=new NoticiasProfesion();
    			nuevaProfAsig.setCodProfesion(prof.getCod());
    			nuevaProfAsig.setIdNoticia(noticia);
    			nuevaProfAsig.setNombreProfesion(prof.getNombrePpal());
    			noticiasProfesionesRepository.save(nuevaProfAsig);
				noticiasProfesionSet.add(nuevaProfAsig);
    		}
    	}
    	
    	noticia.setNoticiasProfesionSet(noticiasProfesionSet);
         
        response.setHeader("Location", request.getRequestURL().append("/").append(createdNoticia.getIdnoticias()).toString());
    }
    
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all Noticiass.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<Noticias> getAllNoticias(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.noticiaRepository.findAll(new PageRequest(page, size));
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single Noticias.", notes = "You have to provide a valid Noticias ID.")
    public
    @ResponseBody
    Noticias getNoticias(@ApiParam(value = "The ID of the Noticias.", required = true)
                             @PathVariable("id") Integer id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Noticias Noticias = this.noticiaRepository.findOne(id);
        checkResourceFound(Noticias);
        return Noticias;
    }
    @RequestMapping(value = "/byProfesion/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all Noticias by ids of profesiones", notes = "You have to provide a valid profesiones IDs.")
    public
    @ResponseBody
    Set<Noticias> getNoticiasByProfesiones(@ApiParam(value = "The IDs of the profesiones.", required = true)
                             @PathVariable("profesiones") String profesiones,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Set<Noticias> res=new HashSet<Noticias>();	
    	if(profesiones!=null && profesiones.length()>0){
    		String[] profs = profesiones.split(":");
    		List<String>profesionesIdList=new ArrayList<String>();
    		
    		for(int z=0;z<profs.length;z++){
    			profesionesIdList.add(profs[z]);
    			
    		}
    		Iterable<Profesiones> profsinDB = profesionesRepository.findProfesionesByCods(profesionesIdList);
    		Iterator<Profesiones> it = profsinDB.iterator();
    		while(it.hasNext()){
    			Iterable<Noticias> notsFinded = noticiaRepository.findNoticiasByProfesion(it.next().getCod());
    			Iterator<Noticias> itfinded = notsFinded.iterator();
    			while(itfinded.hasNext()){
    				res.add(itfinded.next());}
    			}
    		}
    	
        
        checkResourceFound(res);
        return res;
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a Noticias resource.", notes = "You have to provide a valid Noticias ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateNoticias(@ApiParam(value = "The ID of the existing Noticias resource.", required = true)
                                 @PathVariable("id") Integer id, @RequestBody Noticias Noticias,
                                 HttpServletRequest request, HttpServletResponse response) throws DataFormatException {
        checkResourceFound(this.noticiaRepository.findOne(id));
        if (id != Noticias.getIdnoticias()) throw new DataFormatException("ID doesn't match!");
        this.noticiaRepository.save(Noticias);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a Noticias resource.", notes = "You have to provide a valid Noticias ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteNoticias(@ApiParam(value = "The ID of the existing Noticias resource.", required = true)
                                 @PathVariable("id") Integer id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.noticiaRepository.findOne(id));
        this.noticiaRepository.delete(id);
    }
}