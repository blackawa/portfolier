package jp.blackawa.handler.stem;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.form.DeleteForm;
import jp.blackawa.form.stem.DeleteStemResponseForm;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.model.Stem;
import jp.blackawa.testutils.EmfFactory;
import net.arnx.jsonic.JSON;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DeleteStemHandlerTest {

    private DeleteStemHandler handler;
    private UUID targetId;

    @Before
    public void setup() {
        EntityManagerFactory emf = EmfFactory.generateEmf();
        handler = new DeleteStemHandler(emf);

        GeneralDao dao = new GeneralDao(emf);
        targetId = dao.insert(new Stem(UUID.randomUUID(), "deleteTarget", new HashSet<>()));
    }

    @Test
    public void deleteSuccess() {
        Map<String, String> params = new HashMap<>();
        String body = JSON.encode(new DeleteForm(targetId));

        HandlerResponse<DeleteStemResponseForm> actual = handler.process(params, body);
        assertThat("Status Code 200", actual.getStatusCode(), is(204));
        assertThat("No Errors", actual.getContent().getErrors().size(), is(0));
    }
}
