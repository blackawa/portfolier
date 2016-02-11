package jp.blackawa.form.stem;

import jp.blackawa.form.AbstractResponseForm;
import jp.blackawa.model.Stem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteStemResponseForm extends AbstractResponseForm {
    private Stem stem;
}
