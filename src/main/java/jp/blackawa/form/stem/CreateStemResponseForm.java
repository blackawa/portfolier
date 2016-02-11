package jp.blackawa.form.stem;

import jp.blackawa.form.AbstractResponseForm;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateStemResponseForm extends AbstractResponseForm {
    private UUID id;
}
