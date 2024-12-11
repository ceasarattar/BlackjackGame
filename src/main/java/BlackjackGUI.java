import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlackjackGUI extends Application {

    private BlackjackGame game = new BlackjackGame();
    private BorderPane gameLayout; // Declare gameLayout as an instance variable

    private void updateCardImages(HBox handDisplay, ArrayList<Card> hand, boolean isDealer) {
        handDisplay.getChildren().clear(); // Clear existing card images
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            String cardImageFileName;
            // If it's the dealer's hand and the first card, use the hidden card image
            if (isDealer && i == 0) {
                cardImageFileName = "hidden_card.png";
            } else {
                cardImageFileName = card.getFace() + "_of_" + card.getSuit().toLowerCase() + ".png";
            }
            Image cardImage = new Image(getClass().getResourceAsStream("/images/" + cardImageFileName));
            ImageView cardImageView = new ImageView(cardImage);
            cardImageView.setFitHeight(200);
            cardImageView.setPreserveRatio(true);
            handDisplay.getChildren().add(cardImageView);
        }
    }

    private void showMessage(String text, Color color) {
        // Clear the center of the game layout before showing a new message
        gameLayout.setCenter(null);

        Label messageLabel = new Label(text);
        messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 80));
        messageLabel.setTextFill(color);
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Ensure the label can grow
        messageLabel.setPadding(new Insets(0, 0, 0, 300));

        // Center the message in a VBox to ensure it's in the middle of the screen
        VBox messageBox = new VBox(messageLabel);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Allow VBox to take up all available space

        // Set the message box in the center of the game layout
        gameLayout.setCenter(messageBox);
    }
    private Label moneyLabel = new Label();  // Label to display player's money

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Blackjack Game");

        // Main layout
        VBox mainLayout = new VBox();
        mainLayout.setStyle("-fx-background-color: #006400;"); // Dark green background
        mainLayout.setAlignment(Pos.TOP_CENTER);

        gameLayout = new BorderPane();
        gameLayout.setStyle("-fx-background-color: #006400;");
        gameLayout.setPadding(new Insets(20, 20, 20, 20));

        // Welcome text at the top
        Label welcomeLabel = new Label("Welcome to Blackjack");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 100));
        welcomeLabel.setTextFill(Color.web("#FFD700"));
        welcomeLabel.setPadding(new Insets(10, 20, 0, 20));
        welcomeLabel.setFocusTraversable(true);

        // Layout for input and buttons, centered
        VBox centerLayout = new VBox(20);
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.setPadding(new Insets(100, 20, 20, 20));

        TextField betInput = new TextField();
        betInput.setPromptText("Choose starting amount of money");
        betInput.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%); -fx-font-size: 20px; -fx-font-weight: bold;");
        betInput.setPrefWidth(400);
        betInput.setMaxWidth(400);
        betInput.setMinWidth(400);
        betInput.setAlignment(Pos.CENTER);

        // HBox for buttons
        HBox buttonLayout = new HBox(10);
        buttonLayout.setAlignment(Pos.CENTER);

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: black; -fx-text-fill: #FFD700; -fx-font-size: 30px;");
        startButton.setPrefSize(300, 60);

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: black; -fx-text-fill: #FFD700; -fx-font-size: 30px;");
        exitButton.setPrefSize(300, 60);
        exitButton.setOnAction(e -> System.exit(0));

        buttonLayout.getChildren().addAll(startButton, exitButton);
        centerLayout.getChildren().addAll(betInput, buttonLayout);

        // HBox for card images
        HBox cardLayout = new HBox(10);
        cardLayout.setAlignment(Pos.CENTER);
        cardLayout.setPadding(new Insets(20, 0, 20, 0));

        // Load and display card images
        for (String cardName : new String[]{"ace_of_hearts.png", "king_of_diamonds.png", "queen_of_clubs.png", "jack_of_spades.png"}) {
            Image cardImage = new Image(getClass().getResourceAsStream("/images/" + cardName));
            ImageView cardView = new ImageView(cardImage);
            cardView.setFitHeight(300);
            cardView.setPreserveRatio(true);
            cardLayout.getChildren().add(cardView);
        }

        // Add the card layout to the center layout
        centerLayout.getChildren().add(cardLayout);

        mainLayout.getChildren().addAll(welcomeLabel, centerLayout);

        Scene startScene = new Scene(mainLayout, 1250, 750);
        moneyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        moneyLabel.setTextFill(Color.web("#FFD700"));

        // Game Screen
        HBox playerHandImages = new HBox(10);

        gameLayout.setTop(dealerHandImages);
        BorderPane.setAlignment(dealerHandImages, Pos.CENTER);

        Button hitButton = new Button("Hit");
        hitButton.setStyle("-fx-background-color: black; -fx-text-fill: #FFD700; -fx-font-size: 30px;");
        hitButton.setPrefSize(300, 60);

        Button stayButton = new Button("Stay");
        stayButton.setStyle("-fx-background-color: black; -fx-text-fill: #FFD700; -fx-font-size: 30px;");
        stayButton.setPrefSize(300, 60);

        // VBox for buttons
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(moneyLabel,hitButton, stayButton);
        gameLayout.setRight(buttonBox);

        gameLayout.setBottom(playerHandImages);
        BorderPane.setAlignment(playerHandImages, Pos.CENTER);

        playerHandImages.setAlignment(Pos.CENTER);
        playerHandImages.setPadding(new Insets(20, 50, 20, 50));

        dealerHandImages.setAlignment(Pos.CENTER);
        dealerHandImages.setPadding(new Insets(20, 50, 20, 50));

        Scene gameScene = new Scene(gameLayout, 1250, 750);
        

        // Start button action
        startButton.setOnAction(e -> {
        	game.currentBet = Double.parseDouble(betInput.getText());
            game.startingMoney = Double.parseDouble(betInput.getText()); // Initialize player money
            moneyLabel.setText("Money: $" + game.startingMoney); // Update moneyLabel
            game.theDealer.generateDeck();
            game.theDealer.shuffleDeck();
            game.playerHand = game.theDealer.dealHand();
            game.bankerHand = game.theDealer.dealHand();

            updateCardImages(playerHandImages, game.playerHand, false);

            ArrayList<Card> initialDealerHand = new ArrayList<>(game.bankerHand);
            initialDealerHand.set(0, new Card("hidden", "card", 0));
            updateCardImages(dealerHandImages, initialDealerHand, true);

            primaryStage.setScene(gameScene);
        });

        // Hit button action
        hitButton.setOnAction(e -> {
            game.playerHand.add(game.theDealer.drawOne());
            updateCardImages(playerHandImages, game.playerHand, false);

            if (game.gameLogic.handTotal(game.playerHand) > 21) {
                updateCardImages(dealerHandImages, game.bankerHand, false);
                hitButton.setDisable(true);
                stayButton.setDisable(true);
                showMessage("BUST!", Color.RED); // Display BUST message
            }
        });

        // Stay button action
        stayButton.setOnAction(e -> {
            hitButton.setDisable(true);
            stayButton.setDisable(true);

            while (game.gameLogic.handTotal(game.bankerHand) <= 16) {
                game.bankerHand.add(game.theDealer.drawOne());
            }
            updateCardImages(dealerHandImages, game.bankerHand, false);

            String winner = game.gameLogic.whoWon(game.playerHand, game.bankerHand);
            if ("player".equals(winner)) {
                game.totalWinnings += game.currentBet;
                showMessage("WINNER!", Color.web("#90EE90"));
            } else if ("dealer".equals(winner)) {
                game.totalWinnings -= game.currentBet;
                showMessage("LOSER!", Color.RED); // Display LOSER message

        	} else   {
        		showMessage("DRAW!", Color.RED); // Display DRAW message
        	}
        	});

        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
