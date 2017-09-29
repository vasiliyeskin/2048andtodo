package com.example.big_appls;

import com.vaadin.event.ShortcutAction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Vaisiliy Es'kin on 02/11/17.
 */
public class Controller extends KeyAdapter{
    private Model model;
    private View view;
    private GameField gameField;
    private static int WINNING_TILE = 2048;



    public Tile[][] getGameTiles()
    {
        return model.getGameTiles();
    }

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public Controller(Model model, GameField gameField) {
        this.model = model;
        this.view = new View(this);
        this.gameField = gameField;
    }

    public int getScore()
    {
        return model.score;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(!model.canMove()) {
            view.isGameLost = true;
           // return;
        }

        switch (e.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE: resetGame();
            break;
        }


        if(!view.isGameLost && !view.isGameWon) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    model.left();
                    break;

                case KeyEvent.VK_RIGHT:
                    model.right();
                    break;

                case KeyEvent.VK_DOWN:
                    model.down();
                    break;

                case KeyEvent.VK_UP:
                    model.up();
                    break;
                case KeyEvent.VK_Z:
                    model.rollback();
                    break;
                case KeyEvent.VK_R:
                    model.randomMove();
                    break;
                case KeyEvent.VK_A:
                    model.autoMove();
                    break;
            }
        }

        if(model.maxTile == WINNING_TILE)
        {
            view.isGameWon = true;
        }

        view.repaint();
    }

    public void keyPressedWeb(int e) {

        if(!model.canMove()) {
            view.isGameLost = true;
            gameField.isGameLost = true;
            // return;
        }

        switch (e)
        {
            case KeyEvent.VK_ESCAPE: resetGame();
                break;
        }


        if(!view.isGameLost && !view.isGameWon) {
            switch (e) {
                case ShortcutAction.KeyCode.ARROW_LEFT:
                    model.left();
                    break;

                case ShortcutAction.KeyCode.ARROW_RIGHT:
                    model.right();
                    break;

                case ShortcutAction.KeyCode.ARROW_DOWN:
                    model.down();
                    break;

                case ShortcutAction.KeyCode.ARROW_UP:
                    model.up();
                    break;
                case KeyEvent.VK_Z:
                    model.rollback();
                    break;
                case KeyEvent.VK_R:
                    model.randomMove();
                    break;
                case KeyEvent.VK_A:
                    model.autoMove();
                    break;
            }
        }

        if(model.maxTile == WINNING_TILE) {
            view.isGameWon = true;
            gameField.isGameWon = true;
        }
    }

    public void resetGame()
    {
        model.score = 0;
        model.maxTile = 0;

        view.isGameLost = false;
        view.isGameWon = false;

        gameField.isGameLost = false;
        gameField.isGameWon = false;

        model.resetGameTiles();
    }

    public View getView() {
        return view;
    }
}
