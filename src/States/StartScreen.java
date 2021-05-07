/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import CoreClasses.Settler;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.FlyByCamera;
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

/**
 *
 * @author samer
 */
public class StartScreen extends AbstractAppState {

    ArrayList<Picture> hotbar;
    public final Node guiNode;
    private final Node localRootNode = new Node("StartScreen");
    private final AssetManager assetManager;
    private final InputManager inputManager;
    AppSettings settings;
    ArrayList<Picture> Buttons;
    public boolean start, starting, doneStarting;
    BitmapText AsteroidStats;
    private final FlyByCamera flyCam;

    public boolean help;
    
    public StartScreen(SimpleApplication app, AppSettings _settings) {
        this.Buttons = new ArrayList<>();
        guiNode = app.getGuiNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        settings = _settings;
        flyCam = app.getFlyByCamera();
        flyCam.setEnabled(false);

    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        Picture Background = new Picture("backgroundscreen");
        Background.setImage(assetManager, "Interface/StartScreen/Background.png", false);
        Background.move(0, 0, -2);
        Background.setWidth(settings.getWidth());
        Background.setHeight(settings.getHeight());
        guiNode.attachChild(Background);

        //Start Button
        Picture StartButton = new Picture("StartButton");
        StartButton.setImage(assetManager, "Interface/StartScreen/StartButton.png", false);
        StartButton.move(settings.getWidth() / 2 - 70, settings.getHeight() / 2 - 20, -2);
        StartButton.setWidth(140);
        StartButton.setHeight(40);
        guiNode.attachChild(StartButton);

        inputManager.addMapping("Start", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Start");
        start = false;
        doneStarting = false;
        starting = false;
        
        //Help Button
        Picture HelpButton = new Picture("HelpButton");
        HelpButton.setImage(assetManager, "Interface/StartScreen/HelpButton.png", false);
        HelpButton.move(settings.getWidth() / 2 - 70, settings.getHeight() / 2 - 70, -2);
        HelpButton.setWidth(140);
        HelpButton.setHeight(40);
        guiNode.attachChild(HelpButton);

        inputManager.addMapping("Help", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Help");
        help = false;
    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Start") && keyPressed) {
                start = ((inputManager.getCursorPosition().getX() > settings.getWidth() / 2 - 70)
                        && (inputManager.getCursorPosition().getX() < settings.getWidth() / 2 + 70)
                        && (inputManager.getCursorPosition().getY() > settings.getHeight() / 2 - 20)
                        && (inputManager.getCursorPosition().getY() < settings.getHeight() / 2 + 20));
            }
            else if (name.equals("Help") && keyPressed){
                help = ((inputManager.getCursorPosition().getX() > settings.getWidth() / 2 - 70)
                        && (inputManager.getCursorPosition().getX() < settings.getWidth() / 2 + 70)
                        && (inputManager.getCursorPosition().getY() > settings.getHeight() / 2 - 70)
                        && (inputManager.getCursorPosition().getY() < settings.getHeight() / 2 - 50));
            }
        }
    };

    @Override
    public void update(float tpf) {
        if (starting||help) {
            guiNode.detachChildNamed("StartButton");
            guiNode.detachChildNamed("HelpButton");
        }
        if (doneStarting) {
            guiNode.detachChildNamed("backgroundscreen");
        }
    }

    @Override
    public void cleanup() {
        guiNode.detachChild(localRootNode);

        super.cleanup();
    }
}
