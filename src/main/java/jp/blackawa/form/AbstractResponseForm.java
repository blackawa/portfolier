package jp.blackawa.form;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractResponseForm {
    /**
     * 拡張クラスが自分でerrorsにnull値を入れなくても良いように
     * デフォルトコンストラクタでListを生成する。
     */
    public AbstractResponseForm() {
        errors = new ArrayList<>();
    }
    private List<String> errors;
}
