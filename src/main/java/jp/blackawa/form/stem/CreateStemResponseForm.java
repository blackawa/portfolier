package jp.blackawa.form.stem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStemResponseForm {
    private UUID id;
    private List<String> errors;
}
