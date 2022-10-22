package fr.univartois.butinfo.ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La classe JustePrixApplication correspond à la classe principale de
 * l'application. Elle permet de la lancer.
 *
 * @author Sébastien COZE
 * @version 0.1.0, 22/10/2022
 */
public class JustePrixApplication extends Application {

	/**
	 * Cette méthode permet d'initialiser l'affichage de la fenêtre de
	 * l'application.
	 *
	 * @param stage La fenêtre de l'application.
	 */
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
		Parent viewContent = fxmlLoader.load();
		Scene scene = new Scene(viewContent, 640, 480);
		stage.setScene(scene);
		stage.setTitle("Juste Prix");
		stage.show();
	}

	/**
	 * Cette méthode exécute l'application JavaFX.
	 *
	 * @param args Les arguments de la ligne de commande.
	 *
	 * @see #launch(String...)
	 */
	public static void main(String[] args) {
		launch();
	}

}
