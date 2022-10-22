package fr.univartois.butinfo.ihm;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * La classe JustePrixController permet de faire fonctionner la vue
 * main-view.fxml
 *
 * @author Sébastien COZE
 * @version 0.1.0, 22/10/2022
 */
public class JustePrixController {

	/**
	 * Random pour générer le nombre mystère.
	 */
	static final Random MYSTER_NUMBER = new Random();

	/**
	 * Entier correspondant au nombre maximum que peut prendre le nombre mystère.
	 */
	static final int MAX_NUMBER = 100;

	/**
	 * Entier correspondant au nombre maximum d'essais.
	 */
	static final int MAX_TRYING = 20;

	/**
	 * Entier correspondant au nombre mystère.
	 */
	private int number;

	/**
	 * Booléen pour savoir si le nombre a été trouvé ou non.
	 */
	private boolean finded;

	/**
	 * Entier correspondant au nombre d'essais qu'a eu le joueur.
	 */
	private int nbTrying;

	/**
	 * Entier correspondant au nombre de point qu'a le joueur.
	 */
	private int point;

	/**
	 * Le label où l'on va pouvoir afficher des messages.
	 */
	@FXML
	private Label welcomeText;

	/**
	 * Le label où l'on va pouvoir afficher les indices.
	 */
	@FXML
	private Label clue;

	/**
	 * Le label où l'on va pouvoir afficher le nombre de points du joueur.
	 */
	@FXML
	private Label pointLabel;

	/**
	 * La zone de texte où le joueur pourra répondre.
	 */
	@FXML
	private TextField answer;

	/**
	 * Cette méthode est exécuté lorsque le joueur souhaite vérifier si son nombre
	 * est le bon.
	 */
	@FXML
	protected void onConfirmButtonClick() {
		try {
			if (finded)
				answer.setText(String.format("%d", number));
			else {
				int nb = Integer.parseInt(answer.getText());
				if (nb == number) {
					clue.setText("C'est bon !");
					answer.setText(String.format("%d", number));
					finded = true;
					point++;
					answer.setDisable(true);
				} else if (nb > number)
					clue.setText("C'est moins !");
				else
					clue.setText("C'est plus !");
			}
		} catch (Exception e) {
			clue.setText("Ne mettez que des nombres entiers...");
		}
		nbTrying++;
		refresh();
	}

	/**
	 * Cette méthode est exécuté lorsque le joueur souhaite avoir la solution. Un
	 * point lui sera retiré.
	 */
	@FXML
	protected void onSolutionButtonClick() {
		if (nbTrying > 5) {
			answer.setText(String.format("%d", number));
			point--;
		} else
			answer.setText("Tentes encore...");
		refresh();
	}

	/**
	 * Cette méthode est exécuté lorsque le joueur souhaite recommencer. Un point
	 * lui sera retiré.
	 */
	@FXML
	protected void onRestartButtonClick() {
		if (finded) {
			number = MYSTER_NUMBER.nextInt(MAX_NUMBER);
			finded = false;
			nbTrying = 0;
			answer.setDisable(false);
		} else if (point >= 5) {
			point -= 5;
			number = MYSTER_NUMBER.nextInt(MAX_NUMBER);
			finded = false;
			nbTrying = 0;
			answer.setDisable(false);
		} else
			clue.setText("Vous n'avez pas assez de points pour cela.");
		refresh();
	}

	/**
	 * Cette méthode initialise le nombre mystère.
	 */
	@FXML
	protected void initialize() {
		number = MYSTER_NUMBER.nextInt(MAX_NUMBER);
		finded = false;
		nbTrying = 0;
		point = 5;
	}

	/**
	 * Cette méthode rafraichit l'application.
	 */
	private void refresh() {
		pointLabel.setText(String.format("%d points", point));
		if (nbTrying >= MAX_TRYING) {
			answer.setDisable(true);
			clue.setText("Vous avez trop fait d'essai...");
		}
	}

}
