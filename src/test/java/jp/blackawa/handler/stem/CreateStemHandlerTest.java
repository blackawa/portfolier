package jp.blackawa.handler.stem;

import jp.blackawa.form.stem.CreateStemForm;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.testutils.EmfFactory;
import net.arnx.jsonic.JSON;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CreateStemHandlerTest {

    private EntityManagerFactory emf;

    @Before
    public void setup() {
        emf = EmfFactory.generateEmf();
    }

    @Test
    public void createSuccess() {
        CreateStemHandler handler = new CreateStemHandler(emf);

        Map<String, String> params = new HashMap<>();
        String body = JSON.encode(new CreateStemForm("BrandNewStem"));

        HandlerResponse actual = handler.process(params, body);
        assertThat("Status Code 200", actual.getStatusCode(), is(200));
        assertThat("Should Return JSON", actual.isReturnJson(), is(true));
    }
}
