/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import CoreClasses.Settler;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author samer
 */
public class HUD extends AbstractAppState {

    private final Node guiNode;
    private final Node localRootNode = new Node("HUD");
    private final AssetManager assetManager;
    private final InputManager inputManager;
    AppSettings settings;
    ArrayList<Picture> emptyMineralsHotBar;
    ArrayList<Picture> fullMineralsHotBar;
    ArrayList<Picture> emptyGatesHotBar;
    ArrayList<Picture> fullGatesHotBar;
    BitmapText AsteroidStats;

    private boolean craft, cancelcraft;
    private int fillId;
    private Settler s;

    public HUD(SimpleApplication app, AppSettings _settings) {
        this.emptyMineralsHotBar = new ArrayList<>();
        this.fullMineralsHotBar = new ArrayList<>();
        this.emptyGatesHotBar = new ArrayList<>();
        this.fullGatesHotBar = new ArrayList<>();
        guiNode = app.getGuiNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        settings = _settings;
        fillId = -1;

    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        inputManager.addMapping("Craft", new KeyTrigger(KeyInput.KEY_C));
        inputManager.addMapping("CancelCraft", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("Fill", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.addListener(actionListener, "Craft");
        inputManager.addListener(actionListener, "CancelCraft");
        inputManager.addListener(actionListener, "Fill");
        for (int i = 0; i < 10; i++) {
            Picture frame = new Picture("emptyMineralsHotBar" + i);
            frame.setImage(assetManager, "Interface/HUD/Empty.png", false);
            frame.move(settings.getWidth() / 2 - 250 + 50 * i, 25, -2);
            frame.setWidth(50);
            frame.setHeight(50);
            emptyMineralsHotBar.add(frame);
            guiNode.attachChild(frame);
        }
        for (int i = 0; i < 2; i++) {
            Picture frame = new Picture("emptyGateHotBar" + i);
            frame.setImage(assetManager, "Interface/HUD/Empty.png", false);
            frame.move(settings.getWidth() / 2 - 250 + 50 * i, 75, -2);
            frame.setWidth(50);
            frame.setHeight(50);
            emptyGatesHotBar.add(frame);
            guiNode.attachChild(frame);
        }
        s = app.getStateManager().getState(SettlerPlace.class).s;
        BitmapFont guiFont = assetManager.loadFont("Interface/Fonts/ROG.fnt");
        AsteroidStats = new BitmapText(guiFont, false);
        AsteroidStats.setSize(guiFont.getCharSet().getRenderedSize()); // font size
        AsteroidStats.setColor(ColorRGBA.White); // font color
        // the text
        AsteroidStats.setLocalTranslation(settings.getWidth() / 2 + 300, AsteroidStats.getLineHeight() * 6 + 25, 0); // position
        guiNode.attachChild(AsteroidStats);

    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {

            craft = name.equals("Craft") && keyPressed;
            cancelcraft = name.equals("CancelCraft") && keyPressed;
            if (name.equals("Fill") && keyPressed) {

                if ((inputManager.getCursorPosition().getX() > settings.getWidth() / 2 - 250)
                        && (inputManager.getCursorPosition().getX() < settings.getWidth() / 2 + 250)
                        && (inputManager.getCursorPosition().getY() > 25)
                        && (inputManager.getCursorPosition().getY() < 75)) {
                    fillId = (int) (inputManager.getCursorPosition().getX() - (settings.getWidth() / 2 - 250)) / 50;

                } else
                    fillId = -1;
            }
        }
    };

    @Override
    public void update(float tpf) {
        for (int i = 0; i < 10; i++) {
            Picture frame = emptyMineralsHotBar.get(i);
            if (i < s.getMinerals().size()) {
                frame.setImage(assetManager, "Interface/HUD/" + s.getMinerals().get(i) + ".png", false);

            } else {
                frame.setImage(assetManager, "Interface/HUD/Empty.png", false);
            }
        }
        if (fillId >= 0 && fillId < s.getMinerals().size()) {
            s.fill(s.getMinerals().get(fillId));
            fillId = -1;
        }
        AsteroidStats.setText(s.getAsteroid().viewInfo());

    }

    @Override
    public void cleanup() {
        guiNode.detachChild(localRootNode);

        super.cleanup();
    }
}
