package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.character.Player;
import move.Move;

public class JavaFXController {
    EventHandler<? super KeyEvent> eventHandler;

    JavaFXController(Player player){

     eventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
            }
        }
    }; }
}
