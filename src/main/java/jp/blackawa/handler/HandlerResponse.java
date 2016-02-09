package jp.blackawa.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  HandlerResponse<T> {
    private int statusCode;
    private T content;
}
