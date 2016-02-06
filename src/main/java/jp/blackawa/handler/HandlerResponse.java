package jp.blackawa.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor()
public class  HandlerResponse<T> {
    private int statusCode;
    private boolean returnJson;
    private T content;
}
