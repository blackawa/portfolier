package jp.blackawa.form.stem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStemRequestForm {
    @Size(max = 80)
    @NotNull
    private String name;
}
