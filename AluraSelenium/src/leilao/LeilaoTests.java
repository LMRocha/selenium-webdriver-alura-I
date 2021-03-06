package leilao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.GeraMassaDados;

public class LeilaoTests {
	
	WebDriver driver;
	NovoLeilaoPage novoLeilao;
	LeilaoPage leilao;
	String baseURL = "http://localhost:8080/leiloes";
	
	
	@Before
	public void inicializa(){
		System.setProperty("webdriver.gecko.driver", "/opt/Browser/geckodriver");
		this.driver = new FirefoxDriver();
		this.leilao = new LeilaoPage(this.driver);
		new GeraMassaDados(this.driver).run();
		this.leilao.navega(this.baseURL);
	}
	
	@Test
	public void cadastraLeilaoSucesso(){
		this.novoLeilao = this.leilao.novoLeilao(this.driver);
		this.leilao = this.novoLeilao.cadastrarLeilao("Geladeira", "123", true,"Renan Sérgio Nunes",this.driver);
		Assert.assertTrue(this.leilao.filtraGrid(this.leilao.getGridLeiloesCadastrados(), "Geladeira", "", "123", "Lauro Barbosa", "Sim").get(0).getText().contains("Geladeira"));
	}
	
	@Test
	public void cadastraLeilaoSemValorInicial(){
		this.novoLeilao = this.leilao.novoLeilao(this.driver);
		this.novoLeilao.cadastrarLeilao("Opala Comodoro", "", true, "Renan Sérgio Nunes", this.driver);
		Assert.assertTrue(novoLeilao.recuperaMensagensErro().contains("Valor inicial deve ser maior que zero!"));
	}
	
	@Test
	public void cadastraLeilaoSemNome(){
		this.novoLeilao = this.leilao.novoLeilao(this.driver);
		this.novoLeilao.cadastrarLeilao("", "15000", true, "Renan Sérgio Nunes", this.driver);
		Assert.assertTrue(novoLeilao.recuperaMensagensErro().contains("Nome obrigatorio!"));
	}

	
	@After
	public void finaliza(){
		this.driver.close();
	}

}