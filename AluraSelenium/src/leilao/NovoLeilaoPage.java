package leilao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NovoLeilaoPage {
	
	private WebDriver driver;
	
	public NovoLeilaoPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void preencherFormulario(String nome, String valorInicial, Boolean usado, String usuario){
		
		WebElement txtNome = this.driver.findElement(By.name("leilao.nome"));
		WebElement txtValorInicial = this.driver.findElement(By.name("leilao.valorInicial"));
		Select cbUsuario = new Select(this.driver.findElement(By.name("leilao.usuario.id")));
		WebElement cbUsado = this.driver.findElement(By.name("leilao.usado"));
		
		txtNome.sendKeys(nome);
		txtValorInicial.sendKeys(valorInicial);
		cbUsuario.selectByVisibleText(usuario);
		
		if(usado){
			cbUsado.click();
		}
	}
	
	public LeilaoPage cadastrarLeilao(String nome, String valorInicial, Boolean usado, String usuario, WebDriver driver){
		preencherFormulario(nome, valorInicial, usado, usuario);
		this.driver.findElement(By.tagName("button")).click();		
		return new LeilaoPage(driver);
	}
	
	public String recuperaMensagensErro(){
		
		return this.driver.getPageSource();
	}
	
	
}
