package jp.blackawa.handler.top;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;

public class GetTopHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        // トップ画面を返却する
        return new ModelAndView(new HashMap<>(), "index");
    }
}
