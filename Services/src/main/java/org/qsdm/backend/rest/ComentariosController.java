package org.qsdm.backend.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.qsdm.backend.dao.ComentarioRepository;
import org.qsdm.backend.dao.NoticiaRepository;
import org.qsdm.backend.model.Comentarios;
import org.qsdm.backend.model.Noticias;
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
@RequestMapping(value = "/service/Comentarios")
@Api(value = "Comentarios", description = "Comentarios API")
public class ComentariosController extends AbstractRestHandler {

	
	@Autowired
    private ComentarioRepository comentarioRepository;
	@Autowired
    private NoticiaRepository noticiaRepository;
    
       
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Comentario resource over a Comentario", notes = "Returns the URL of the new resource in the Location header.")
    public void createComentario(
    		@RequestParam("emailUsuarioPublicacion") String emailUsuarioPublicacion,
    		@RequestParam("textoComentario") String textoComentario,
     		@RequestParam("idNoticia") Integer idNoticia ,
    		@RequestParam("descripcion") String descripcion,
    		@RequestParam("urlFuente") String urlFuente,
                                  HttpServletRequest request, HttpServletResponse response) {
    	
    	Noticias not = noticiaRepository.findOne(idNoticia);
    	Comentarios comentario=new Comentarios();
    	comentario.setComentario(textoComentario);
    	comentario.setFechaAlta(new Date());
    	comentario.setIdNoticiaRelacionada(not);
    	comentario.setEmailUsuarioPublicacion(emailUsuarioPublicacion);
    	
    	
    	Comentarios createdComentario = comentarioRepository.save(comentario);
   
    	 
    	
          
        response.setHeader("Location", request.getRequestURL().append("/").append(createdComentario.getIdcomentarios()).toString());
    }
    
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all Comentarioss.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<Comentarios> getAllComentarios(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.comentarioRepository.findAll(new PageRequest(page, size));
    }
    @RequestMapping(value = "/byNoticia/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all Comentarios by noticia", notes = "You have to provide a valid Comentarios ID.")
    public
    @ResponseBody
    List<Comentarios> getComentariosByNoticia(@ApiParam(value = "The ID of the Comentarios.", required = true)
                             @PathVariable("id") Integer idNoticia,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Iterable<Comentarios> Comentarios = this.comentarioRepository.findComentarioByNoticia(idNoticia);
        checkResourceFound(Comentarios);
        List<Comentarios>res=new ArrayList<Comentarios>();
        Iterator<org.qsdm.backend.model.Comentarios> it = Comentarios.iterator();
        while(it.hasNext()){
        	res.add(it.next());
        }
        return res;
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single Comentarios.", notes = "You have to provide a valid Comentarios ID.")
    public
    @ResponseBody
    Comentarios getComentarios(@ApiParam(value = "The ID of the Comentarios.", required = true)
                             @PathVariable("id") Integer id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Comentarios Comentarios = this.comentarioRepository.findOne(id);
        checkResourceFound(Comentarios);
        return Comentarios;
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a Comentarios resource.", notes = "You have to provide a valid Comentarios ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateComentarios(@ApiParam(value = "The ID of the existing Comentarios resource.", required = true)
                                 @PathVariable("id") Integer id, @RequestBody Comentarios Comentarios,
                                 HttpServletRequest request, HttpServletResponse response) throws DataFormatException {
        checkResourceFound(this.comentarioRepository.findOne(id));
        if (id != Comentarios.getIdcomentarios()) throw new DataFormatException("ID doesn't match!");
        this.comentarioRepository.save(Comentarios);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a Comentarios resource.", notes = "You have to provide a valid Comentarios ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteComentarios(@ApiParam(value = "The ID of the existing Comentarios resource.", required = true)
                                 @PathVariable("id") Integer id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.comentarioRepository.findOne(id));
        this.comentarioRepository.delete(id);
    }
}