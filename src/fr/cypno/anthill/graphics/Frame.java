package fr.cypno.anthill.graphics;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.graphics.tiles.*;
import fr.cypno.anthill.map.*;
import fr.cypno.anthill.simulation.Simulation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Frame extends Application {
    private static int cellSize;
    private static Simulation simulation;
    private static Map map;
    private Stage stage;
    private Thread thread;

    public static void launchFrame(String[] args, int cellSize, int nbAnts, double pheromonDecrease, long step) {
        Frame.cellSize = cellSize;
        Frame.simulation = new Simulation(nbAnts, pheromonDecrease, step);
        Frame.map = simulation.getMap();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        simulation.setFrame(this);
        thread = new Thread(simulation);
        thread.start();
        stage.setWidth(map.getMap()[0].length * cellSize + map.getMap()[0].length + cellSize * 2);
        stage.setHeight(map.getMap().length * cellSize + map.getMap().length + cellSize * 2);
        stage.setTitle("Ant Simulator 17 - Game of the Year Edition");
        drawScene(stage);
    }

    public void drawScene(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(mouseEvent.getSceneX() + "/" + mouseEvent.getSceneY());
                togglePause();
            }
        });
        scene.setFill(Color.GRAY);

        Group ui = new Group();

        Group ground = new Group();
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

    public void close()
    {
        System.exit(0);
    }
    
    public void togglePause() {
        if (simulation.isInPause()) {
            simulation.setInPause(false);
        }
        else {
            simulation.setInPause(true);
        }
    }

    public void notifyFrame() {
        Platform.runLater(() -> drawScene(stage));
    }
}
