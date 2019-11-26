package ftt.ec.hw.rest;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ftt.ec.hw.dao.NotasDAO;
import ftt.ec.hw.entidade.Notas;

@Path ("/notas")
public class NotasService {
	 
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	private NotasDAO notasDAO;
	
	@PostConstruct
	private void init()	{
		notasDAO = new NotasDAO();
	}
	
	@GET
	@Path("/list")
	@Produces (MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Notas> listarNotas() {
		List<Notas> lista = null;
		try {
			lista = notasDAO.listarNotas();
		} catch (Exception e) {
			e.printStackTrace();;
		}
		return lista;
	}
	@POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8	)
    @Produces(MediaType.TEXT_PLAIN)
    public String addNota(Notas notas) {
        String msg = "";
 
        System.out.println(notas.getNome());
 
        try {
            int idGerado = notasDAO.addNotas(notas); 
 
            msg = String.valueOf(idGerado);
        } catch (Exception e) {
            msg = "Erro ao add a nota!";
            e.printStackTrace();
        }
 
        return msg;
    }
	@GET
    @Path("/get/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
    public Notas buscarPorId(@PathParam("id") int idNota) {
        Notas nota = null;
        try {
            nota = notasDAO.buscarNotaPorId(idNota);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return nota;
    }
	@PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String editarNota(Notas notas, @PathParam("id") int idNota) {
        String msg = "";
         
        System.out.println(notas.getNome());
         
        try {
            notasDAO.editarNotas(notas, idNota);
             
            msg = "Nota editada com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao editar a nota!";
            e.printStackTrace();
        }
         
        return msg;
    }
	 
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String removerNota(@PathParam("id") int idNota) {
        String msg = "";
         
        try {
            notasDAO.removerNota(idNota);
             
            msg = "Nota removida com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao remover a nota!";
            e.printStackTrace();
        }
         
        return msg;
    }
}
