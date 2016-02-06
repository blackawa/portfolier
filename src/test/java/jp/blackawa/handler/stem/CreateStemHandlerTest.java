package jp.blackawa.handler.stem;

import jp.blackawa.form.stem.CreateStemForm;
import jp.blackawa.form.stem.CreateStemResponseForm;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.testutils.EmfFactory;
import net.arnx.jsonic.JSON;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class CreateStemHandlerTest {

    private CreateStemHandler handler;

    @Before
    public void setup() {
        EntityManagerFactory emf = EmfFactory.generateEmf();
        handler = new CreateStemHandler(emf);
    }

    @Test
    public void createSuccess() {
        Map<String, String> params = new HashMap<>();
        String body = JSON.encode(new CreateStemForm("BrandNewStem"));

        HandlerResponse<CreateStemResponseForm> actual = handler.process(params, body);
        assertThat("Status Code 200", actual.getStatusCode(), is(200));
        assertThat("Should Return JSON", actual.isReturnJson(), is(true));
        assertThat("Error Not Included", actual.getContent().getErrors(), nullValue());
    }

    @Test
    public void invalidBody() {
        String body = JSON.encode(new CreateStemForm(null));
        HandlerResponse<CreateStemResponseForm> actual = handler.process(new HashMap<>(), body);
        assertThat("Status Code 400", actual.getStatusCode(), is(400));
        assertThat("Should Return JSON", actual.isReturnJson(), is(true));
        assertThat("Error Included", actual.getContent().getErrors().length, not(0));
    }

    @Test
    public void invalidJsonFormat() {
        String body = JSON.encode(new HashMap<String, Object>(){{
            put("invalidId", 999L);
        }});
        HandlerResponse<CreateStemResponseForm> actual = handler.process(new HashMap<>(), body);
        assertThat("Status Code 400", actual.getStatusCode(), is(400));
        assertThat("Should Return JSON", actual.isReturnJson(), is(true));
        assertThat("Error Included", actual.getContent().getErrors().length, not(0));
    }
}
