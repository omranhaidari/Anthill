package fr.cypno.anthill.graphics;

import fr.cypno.anthill.ant.Ant;
import fr.cypno.anthill.graphics.tiles.*;
import fr.cypno.anthill.map.*;
import fr.cypno.anthill.simulation.Simulation;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Frame extends Application {
    private static int cellSize;
    private static Simulation simulation;
    private static Map map;
    private Stage stage;
    private Thread thread;

    public static void launchFrame(String[] args, int cellSize, int nbAnts) {
        Frame.cellSize = cellSize;
        Frame.simulation = new Simulation(nbAnts);
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
        scene.setFill(Color.GRAY);
        Cell[][] cells = map.getMap();
        for (int l = 0; l < cells.length; l++) {
            for (int c = 0; c < cells[l].length; c++) {
                Cell cell = cells[l][c];
                if (cell instanceof Anthill)
                    root.getChildren().add(new AnthillTile((Anthill) cell).draw(cellSize));
                else if (cell instanceof Obstacle)
                    root.getChildren().add(new ObstacleTile((Obstacle) cell).draw(cellSize));
                else if (cell instanceof Food)
                    root.getChildren().add(new FoodTile((Food) cell).draw(cellSize));
                else
                    root.getChildren().add(new EmptyTile((Empty) cell).draw(cellSize));
            }
        }
        for (Ant ant : simulation.getAnts())
            root.getChildren().add(new AntTile(ant).draw(cellSize));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> close());
    }

    public void close()
    {
        System.exit(0);
    }

    public void notifyFrame() {
        Platform.runLater(() -> drawScene(stage));
        System.out.println("Frame rafraichie !");
    }
}
