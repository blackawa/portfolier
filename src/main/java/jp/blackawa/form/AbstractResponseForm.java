package jp.blackawa.form;

import lombok.Data;

import java.util.List;

@Data
public abstract class AbstractResponseForm {
    private List<String> errors;
}
