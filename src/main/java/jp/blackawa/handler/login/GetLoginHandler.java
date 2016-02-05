package jp.blackawa.handler.login;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;

public class GetLoginHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        // ログイン画面を返却する
        return new ModelAndView(new HashMap<>(), "login");
    }
}
