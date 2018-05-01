package usuario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovoUsuarioPage {
	
	WebDriver driver;
	
	WebElement txtNome;
	WebElement txtEmail;
	WebElement btSalvar;
	
	UsuariosPage usuariosPage;
	
	public NovoUsuarioPage(WebDriver driver){
		this.driver = driver;
		this.txtNome = driver.findElement(By.name("usuario.nome"));
		this.txtEmail = driver.findElement(By.name("usuario.email"));
		this.btSalvar = driver.findElement(By.id("btnSalvar"));
	}
	
	public void preencheCampos(String nome, String email){
		this.txtNome.sendKeys(nome);
		this.txtEmail.sendKeys(email);
	}
	
	public UsuariosPage cadastra(String nome, String email, WebDriver driver){
		this.preencheCampos(nome, email);
		this.btSalvar.click();
		return new UsuariosPage(driver);
	}
	
	public UsuariosPage voltar(WebDriver driver){
		driver.findElement(By.linkText("Voltar")).click();
		return new UsuariosPage(driver);
	}
	
	public Boolean existeNaListagem(String nome, String email){
		
		return this.driver.getPageSource().contains(nome) &&
				this.driver.getPageSource().contains(email);
	}
	
	
}
