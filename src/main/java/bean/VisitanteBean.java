package bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.model.file.UploadedFile;

import dao.VisitanteDao;
import model.Visitante;

@Named
@RequestScoped
public class VisitanteBean {

	private Visitante visitante = new Visitante();
	private UploadedFile image;
	
	@Inject
	private VisitanteDao dao;
	
	public String save() throws IOException {
		System.out.println(this.visitante);
		
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getContext();
		
		String path = servletContext.getRealPath("/");
		
		FileOutputStream out =
				new FileOutputStream(path + "\\images\\" + image.getFileName());
		
		out.write(image.getContent());
		out.close();
		
		visitante.setImagePath("\\images\\" + image.getFileName());
		
		dao.create(visitante);
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Visitante cadastrado com sucesso!"));
		
		return "visitantes"; //caminho que ele vai retornar na url
	}

	public List<Visitante> getAll() {
		return new VisitanteDao().listAll();
	}

	public Visitante getVisitante() {
		return visitante;
	}

	public void setVisitante(Visitante visitante) {
		this.visitante = visitante;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

}
