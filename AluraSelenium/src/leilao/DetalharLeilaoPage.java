package leilao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetalharLeilaoPage {
	
	WebDriver driver;	
	
	public DetalharLeilaoPage(WebDriver driver) {
		this.driver = driver;
	}
	
		
	@Test
	public void preencheCampos(String usuario, String valor){
		Select select = new Select(this.driver.findElement(By.name("lance.usuario.id")));
		select.selectByVisibleText(usuario);
		this.driver.findElement(By.name("lance.valor")).sendKeys(valor);
	}
	
	@Test
	public void realizarLance(String usuario, String valor){
		this.preencheCampos(usuario, valor);
		this.driver.findElement(By.id("btnDarLance")).click();
		new WebDriverWait(driver, 10)
        .until(ExpectedConditions.textToBePresentInElement(By.id("lancesDados"), usuario));
	}
	
	@Test
	public void voltar(){
		
	}
	
	public List<WebElement> getGridLances(){
		return this.driver.findElements(By.xpath("//table/tr/td"));
	}
	
	public List<WebElement> getUltimoRegistroLance(List<WebElement> grid){
		List<WebElement> celulaLinha = new ArrayList<WebElement>();
		celulaLinha.add(0,grid.get(grid.size()).findElement(By.xpath("td[1]")));
		celulaLinha.add(1,grid.get(grid.size()).findElement(By.xpath("td[2]")));
		celulaLinha.add(2,grid.get(grid.size()).findElement(By.xpath("td[3]")));
		return celulaLinha;
	} 
}
