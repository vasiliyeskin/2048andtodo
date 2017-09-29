package com.example.big_appls;

import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.awt.event.KeyEvent;

@Component
@UIScope
public class GameField extends VerticalLayout {
    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 96;
    private static final int TILE_MARGIN = 12;
    private static final String tileFormat =
            "<div style=\"background-color:rgb(%d,%d,%d);font-size:50px;" +
            "width:100px;height:100px;color:rgb" +
            "(255,255,255);text-align: center;vertical-align:middle\">%s</div>";

    boolean isGameWon = false;
    boolean isGameLost = false;

    private Model model = new Model();
    private Controller controller = new Controller(model,this);

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Controller getController() {
        return controller;
    }

    @PostConstruct
    void init(){
        setSpacing(true);
        update();
    }

    public void update()
    {
        removeAllComponents();
        getVerticalLayout();
    }

    public void getVerticalLayout() {

        Tile tile = new Tile();
        for (int y = 0; y < Model.FIELD_WIDTH; y++) {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setSpacing(true);
            layout.setWidth(500, Sizeable.Unit.PIXELS);
            for (int x = 0; x < Model.FIELD_WIDTH; x++) {
                tile = controller.getGameTiles()[y][x];
                Color c = tile.getTileColor();
                String s =String.format(tileFormat,
                        c.getRed(),
                        c.getGreen(),
                        c.getBlue(),
                        tile.value + "");
                Label l = new Label(s, ContentMode.HTML);
                layout.addComponent(l);
            }
            addComponents(layout);
        }
    }

    public void keyPress(int e)
    {
        if(e!=KeyEvent.VK_ESCAPE && (isGameLost || isGameWon)) {
            if (isGameWon) {
                Notification.show( "You've won!");
            } else if (isGameLost) {
                Notification.show( "You've lost :(");
            }
        }
        else {
            controller.keyPressedWeb(e);
            this.update();
        }
    }
}
