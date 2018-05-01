package usuario;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.GeraMassaDados;


public class UsuarioTests {

	private WebDriver driver;
	private UsuariosPage usuarioPage;
	private NovoUsuarioPage novoUsuarioPage;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "/opt/Browser/geckodriver");
		this.driver = new FirefoxDriver();
		new GeraMassaDados(this.driver).run();
		this.usuarioPage = new UsuariosPage(this.driver);
		this.usuarioPage.navegar();
	}

	@Test
	public void acessarPaginaNovoUsuario() {
		this.novoUsuarioPage = this.usuarioPage.novo(this.driver);
		Assert.assertTrue(novoUsuarioPage.existeNaListagem("Nome", "E-mail"));
	}

	@Test
	public void cadastraUsuarioComSucesso() {
		this.novoUsuarioPage = this.usuarioPage.novo(this.driver);
		this.usuarioPage = this.novoUsuarioPage.cadastra("Lauro Barbosa", "lauro.mrocha@gmail.com",this.driver);
		
		Assert.assertTrue(this.usuarioPage.filtraElementoGrid(
				this.usuarioPage.getListaUsuariosCadastrados(), "Lauro Barbosa", "lauro.mrocha@gmail.com").get(0).getText().contains("Lauro Barbosa")
				&& this.usuarioPage.filtraElementoGrid(
						this.usuarioPage.getListaUsuariosCadastrados(), "Lauro Barbosa", "lauro.mrocha@gmail.com").get(1).getText().contains("lauro.mrocha@gmail.com"));		
	}
	
	@Test
	public void excluirUsuario() throws InterruptedException{
		
		int qtdeRegistrosGridAntes = this.usuarioPage.retornaQtdeElementosGrid(this.usuarioPage.getListaUsuariosCadastrados());
		this.usuarioPage.realizarAcaoGrid(this.usuarioPage.recuperaLinhaGrid(this.usuarioPage.getListaUsuariosCadastrados(), 4), 
				"excluir", this.driver,"","");
		int qtdeRegistrosGridDepois = this.usuarioPage.retornaQtdeElementosGrid(this.usuarioPage.getListaUsuariosCadastrados());

		Assert.assertTrue("Elemento removido da grid",qtdeRegistrosGridDepois < qtdeRegistrosGridAntes);
		
	}	
	
	@Test
	public void editarRegistroDeUsuario() throws InterruptedException{
		this.usuarioPage.realizarAcaoGrid(this.usuarioPage.recuperaLinhaGrid(this.usuarioPage.getListaUsuariosCadastrados(), 1), 
				"editar", this.driver,"Lauro Barbosa [ALTERADO]","lauro.mrocha@gmail.com[ALTERADO]");
		Assert.assertTrue(this.driver.getPageSource().contains("[ALTERADO]"));
	}
	
	@After
	public void finaliza() {
		this.driver.close();
	}

}
