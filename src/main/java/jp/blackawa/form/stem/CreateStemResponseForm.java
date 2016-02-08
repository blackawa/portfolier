package jp.blackawa.form.stem;

import jp.blackawa.form.AbstractResponseForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStemResponseForm extends AbstractResponseForm {
    private UUID id;
}
