package fr.cypno.anthill.graphics;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.graphics.tiles.*;
import fr.cypno.anthill.graphics.ui.*;
import fr.cypno.anthill.map.*;
import fr.cypno.anthill.simulation.Simulation;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Frame extends Application {
    private static int cellSize;
    private static Simulation simulation;
    private static Map map;
    private Stage stage;
    private Thread thread;
    private ArrayList<Button> buttons;

    /**
     * Crée une nouvelle Frame.
     * @param args les arguments du main
     * @param cellSize la taille en pixels d'une cellule
     * @param nbAnts le nombre de fourmis
     * @param pheromonDecrease le taux de disparition des phéromones à chaque
     *          mise à jour
     * @param step le temps d'attente entre chaque mise à jour en millisecondes
     */
    public static void launchFrame(String[] args, int cellSize, int nbAnts, double pheromonDecrease, long step) {
        Frame.cellSize = cellSize;
        Frame.simulation = new Simulation(nbAnts, pheromonDecrease, step);
        Frame.map = simulation.getMap();
        launch(args);
    }

    /**
     * Lance l'exécution de la fenêtre.
     * @param stage le stage de dessin
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        this.buttons = new ArrayList<>();
        buttons.add(new PauseButton(51, 0, 50, 50));
        buttons.add(new SlowButton(0, 0, 50, 50));
        buttons.add(new SpeedButton(102, 0, 50, 50));

        simulation.setFrame(this);
        thread = new Thread(simulation);
        thread.start();
        stage.setWidth(1200);
        stage.setHeight(700);
        stage.setTitle("Ant Simulator 17 - Game of the Year Edition");
        drawScene(stage);
    }

    /**
     * Rafraichit la zone de dessin.
     * @param stage
     */
    public void drawScene(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            for (Button b : buttons)
                b.click(mouseEvent.getX(), mouseEvent.getY(), this);
        });
        scene.setFill(Color.GRAY);

        Group ui = new Group();
        for (Button b : buttons)
            ui.getChildren().add(b.draw());
        root.getChildren().add(ui);

        Group ground = new Group();
        ground.setTranslateX(0);
        ground.setTranslateY(51);
        Cell[][] cells = map.getMap();
        for (int l = 0; l < cells.length; l++) {
            for (int c = 0; c < cells[l].length; c++) {
                Cell cell = cells[l][c];
                if (cell instanceof Anthill)
                    ground.getChildren().add(new AnthillTile((Anthill) cell, cellSize).draw());
                else if (cell instanceof Obstacle)
                    ground.getChildren().add(new ObstacleTile((Obstacle) cell, cellSize).draw());
                else if (cell instanceof Food)
                    ground.getChildren().add(new FoodTile((Food) cell, cellSize).draw());
                else
                    ground.getChildren().add(new EmptyTile((Empty) cell, cellSize).draw());
            }
        }
        for (Ant ant : simulation.getAnts())
            ground.getChildren().add(new AntTile(ant, cellSize).draw());
        root.getChildren().add(ground);

        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> close());
    }

    /**
     * Ferme l'application.
     */
    public void close()
    {
        System.exit(0);
    }
    
    /**
     * Met la simulation en pause ou en route.
     */
    public void togglePause() {
        if (simulation.isInPause()) {
            simulation.setUnPause();
        }
        else {
            simulation.setPause();
        }
        drawScene(stage);
    }
    
    /**
     * Accélère la simulation.
     */
    public void speedUp() {
        simulation.setStep(simulation.getStep() / 2);
    }
    
    /**
     * Ralentit la simulation.
     */
    public void slowDown() {
        simulation.setStep(simulation.getStep() * 2);
    }

    /**
     * Notifie la fenêtre pour rafraichir la zone de dessin.
     */
    public synchronized void notifyFrame() {
        Platform.runLater(() -> drawScene(stage));
    }
}
