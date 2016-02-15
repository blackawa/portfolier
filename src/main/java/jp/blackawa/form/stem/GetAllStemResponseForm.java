package jp.blackawa.form.stem;

import jp.blackawa.form.AbstractResponseForm;
import jp.blackawa.model.Stem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllStemResponseForm extends AbstractResponseForm {
    private List<Stem> stems;
}
