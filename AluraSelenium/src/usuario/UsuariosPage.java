package usuario;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UsuariosPage {

	WebDriver driver;
	List<WebElement> gridUsuarios;

	public UsuariosPage(WebDriver driver) {
		this.driver = driver;
	}

	public void navegar() {
		driver.get("http://localhost:8080/usuarios");
	}

	public void excluirUsuario(WebElement linhaGridUsuarios) {

		if (linhaGridUsuarios.isDisplayed()) {
			linhaGridUsuarios.findElement(By.xpath("form/button")).click();
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}

	}

	public NovoUsuarioPage novo(WebDriver driver) {

		driver.findElement(By.linkText("Novo Usuário")).click();
		return new NovoUsuarioPage(driver);
	}

	// Métodos de tratamento de Grids

	public List<WebElement> getListaUsuariosCadastrados() {
		this.gridUsuarios = this.driver.findElements(By.xpath("//table/tbody/tr[position()>1]"));
		return this.gridUsuarios;
	}

	public List<WebElement> recuperaLinhaGrid(List<WebElement> gridUsuario, int elemento) {

		List<WebElement> linha = new ArrayList<WebElement>();

		if (!isGridVazia(gridUsuario)) {
			linha.add(0, gridUsuario.get(elemento - 1).findElement(By.xpath("td[1]")));
			linha.add(1, gridUsuario.get(elemento - 1).findElement(By.xpath("td[2]")));
			linha.add(2, gridUsuario.get(elemento - 1).findElement(By.xpath("td[3]")));
		}

		return linha;
	}
	

	public Boolean isGridVazia(List<WebElement> gridUsuarios) {
		Boolean flag;

		if (gridUsuarios.isEmpty()) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}
	
	public int retornaQtdeElementosGrid(List<WebElement> gridUsuarios){
		return gridUsuarios.size();
	}
	
	public List<WebElement> filtraElementoGrid(List<WebElement> gridUsuarioPopulada,String nome, String email){
		
		List<WebElement> linhaRecuperada = new ArrayList<WebElement>();
		
		if(!gridUsuarioPopulada.isEmpty()){
				
			for (WebElement linha : gridUsuarioPopulada) {
				
				if(linha.findElement(By.xpath("td[1]")).getText().contains(nome) && linha.findElement(By.xpath("td[2]")).getText().contains(email)){
					linhaRecuperada.add(0,linha.findElement(By.xpath("td[1]")));
					linhaRecuperada.add(1,linha.findElement(By.xpath("td[2]")));
				}
			}
		}
		
		return linhaRecuperada;
	}

	public void realizarAcaoGrid(List<WebElement> linhaGridUsuario, String acao, WebDriver driver, String nome, String email) throws InterruptedException {

		if (!linhaGridUsuario.isEmpty()) {

			switch (acao) {

			case "editar":
				EditarUsuarioPage editarUsuario;
				linhaGridUsuario.get(2).findElement(By.xpath("a[2]")).click();
				editarUsuario = new EditarUsuarioPage(driver);
				editarUsuario.editar(nome, email, driver);
				break;

			case "visualizar":
				linhaGridUsuario.get(2).findElement(By.xpath("a[1]")).click();
				break;

			case "excluir":
				linhaGridUsuario.get(2).findElement(By.xpath("form/button")).click();
				Alert alert = driver.switchTo().alert();
				alert.accept();
				Thread.sleep(2000);
				break;
			}
		}

	}

}
