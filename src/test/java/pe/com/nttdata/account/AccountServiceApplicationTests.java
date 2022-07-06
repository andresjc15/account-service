package pe.com.nttdata.account;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import pe.com.nttdata.account.model.document.Account;
import pe.com.nttdata.account.type.model.document.TypeAccount;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AccountServiceApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(AccountServiceApplicationTests.class);

	private Account account;
	private TestInfo testInfo;
	private TestReporter testReporter;
	@BeforeEach
	void initMethodTest(TestInfo testInfo, TestReporter testReporter) {
		this.account = new Account(0001L,"00000020f51bb4362eee2a4d", "C005", new TypeAccount(),
				1648517941534L, new BigDecimal(300.00), null, true,new Date(), null);
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		log.info("Iniciando el metodo test");
		testReporter.publishEntry(" ejecutando: " + testInfo.getDisplayName() + " "
				+ testInfo.getTestMethod().orElse(null).getName()
				+ " con las etiquetas " + testInfo.getTags());
	}

	@Test
	@DisplayName("Error en caso de prueba numero de cuenta")
	void testNumberAccount() {
		assertNotNull(account.getNumberAccount());
		assertEquals(1648517941534L, account.getNumberAccount().longValue());
		assertFalse(account.getNumberAccount().compareTo(0L) < 0);
		assertTrue(account.getNumberAccount().compareTo(0L) > 0);
	}

	@Test
	@DisplayName("Error en caso de prueba monto")
	void testAmount() {
		assertNotNull(account.getAmount());
		assertNotEquals(new BigDecimal(200.00), account.getAmount());
		assertFalse(account.getAmount().compareTo(BigDecimal.ZERO) < 0);
		assertTrue(account.getAmount().compareTo(BigDecimal.ZERO) > 0);
	}

}
