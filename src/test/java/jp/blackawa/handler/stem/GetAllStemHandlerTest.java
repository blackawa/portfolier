package jp.blackawa.handler.stem;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.form.stem.GetAllStemResponseForm;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.model.Stem;
import jp.blackawa.testutils.EmfFactory;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GetAllStemHandlerTest {

    private GetAllStemHandler handler;

    @Before
    public void setup() {
        EntityManagerFactory emf = EmfFactory.generateEmf();
        handler = new GetAllStemHandler(emf);

        GeneralDao dao = new GeneralDao(emf);
        dao.insert(new Stem(UUID.randomUUID(), "stem1", new HashSet<>()));
        dao.insert(new Stem(UUID.randomUUID(), "stem2", new HashSet<>()));
        dao.insert(new Stem(UUID.randomUUID(), "stem3", new HashSet<>()));
    }

    @Test
    public void getAllSuccess() {
        Map<String, String> params = new HashMap<>();
        String body = "";

        HandlerResponse<GetAllStemResponseForm> actual = handler.process(params, body);
        assertThat("Status Code 200", actual.getStatusCode(), is(200));
        assertThat("Get 3 records", actual.getContent().getStems().size(), is(3));
    }
}