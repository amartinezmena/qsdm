package org.qsdm.backend.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.zip.DataFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.qsdm.backend.model.Sesion;
import org.qsdm.backend.model.Usuario;
import org.qsdm.backend.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping(value = "/service/usuarios")
@Api(value = "Usuarios", description = "Usuario API")
public class UsuariosController extends AbstractRestHandler {

    @Autowired
    private UsuariosService usuarioService;
    private static final String SESSION_PARA_NAME="QSDM_SESSION";
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Usuario resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createUsuario(
    		@RequestParam("email") String email,
    		@RequestParam("password") String password,
    		@RequestParam("nombre") String nombre,
    		@RequestParam("apellidos") String apellidos,
    		@RequestParam("fechaNacimiento") String fechaNacimiento,
    		@RequestParam("codigoPostal") Integer codigoPostal,
    		@RequestParam("sexo") String sexo,
                                 HttpServletRequest request, HttpServletResponse response) {
    	
    	SimpleDateFormat spf=new SimpleDateFormat("dd/MM/yy");
    	Usuario usuario=new Usuario();
    	usuario.setApellidos(apellidos);
    	usuario.setNombre(nombre);
    	usuario.setEmail(email);
    	usuario.setPassword(password);
    	usuario.setCodigoPostal(codigoPostal);
    	try {
			usuario.setFechaNacimiento(spf.parse(fechaNacimiento));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	usuario.setApellidos(apellidos);
    	
        Usuario createdUsuario = this.usuarioService.createUsuario(usuario);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdUsuario.getIdUsuario()).toString());
    }

    
    @RequestMapping(value = "create",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Register user", notes = "Returns the URL of the new resource in the Location header.")
    public void createUsuario(@RequestBody Usuario Usuario,
                                 HttpServletRequest request, HttpServletResponse response) {
        Usuario createdUsuario = this.usuarioService.createUsuario(Usuario);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdUsuario.getIdUsuario()).toString());
    }
    
    
    
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all Usuarios.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<Usuario> getAllUsuario(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.usuarioService.getAllUsuarios(page, size);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single Usuario.", notes = "You have to provide a valid Usuario ID.")
    public
    @ResponseBody
    Usuario getUsuario(@ApiParam(value = "The ID of the Usuario.", required = true)
                             @PathVariable("id") Integer id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Usuario Usuario = this.usuarioService.getUsuario(id);
        checkResourceFound(Usuario);
        return Usuario;
    }
    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Check user login", notes = "You have to provide a valid email and password.")
    public
    @ResponseBody
    Sesion checkUsuario(@ApiParam(value = "The email of the Usuario, and password", required = true)
    @RequestParam("email") String email,
    @RequestParam("password") String password,
                             HttpServletRequest request, HttpServletResponse response ,HttpSession session) throws Exception {
        Usuario usuario = this.usuarioService.checkUsuario(email, password);
        checkResourceFound(usuario );
        Sesion ses=new Sesion();
        ses.setUsuario(usuario);
        ses.setToken(UUID.randomUUID().toString());
        session.setAttribute(SESSION_PARA_NAME, ses);
        return ses;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a Usuario resource.", notes = "You have to provide a valid Usuario ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateUsuario(@ApiParam(value = "The ID of the existing Usuario resource.", required = true)
                                 @PathVariable("id") Integer id, @RequestBody Usuario Usuario,
                                 HttpServletRequest request, HttpServletResponse response) throws DataFormatException {
        checkResourceFound(this.usuarioService.getUsuario(id));
        if (id != Usuario.getIdUsuario()) throw new DataFormatException("ID doesn't match!");
        this.usuarioService.updateUsuario(Usuario);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a Usuario resource.", notes = "You have to provide a valid Usuario ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteUsuario(@ApiParam(value = "The ID of the existing Usuario resource.", required = true)
                                 @PathVariable("id") Integer id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.usuarioService.getUsuario(id));
        this.usuarioService.deleteUsuario(id);
    }
}