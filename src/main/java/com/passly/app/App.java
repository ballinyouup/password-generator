package com.passly.app;

import com.passly.app.Routes.Dashboard;
import com.passly.app.Routes.Login;
import com.passly.app.Routes.Register;
import atlantafx.base.theme.CupertinoDark;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static final double WINDOW_WIDTH = 1280;
    public static final double WINDOW_HEIGHT = 960;

    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
        Router.setStage(stage);
        Dotenv env = Dotenv.configure().directory("./src/main/resources/com/passly/app").load();
        Context.setEnv(env);
        Router.addRoute(Route.LOGIN, () -> new Login());
        Router.addRoute(Route.REGISTER, () -> new Register());
        Router.addRoute(Route.DASHBOARD, () -> new Dashboard());

        Router.push(Route.LOGIN);
        Router.getStage().show();
    }

    public static void main(String[] args) {
        launch();
    }
}
