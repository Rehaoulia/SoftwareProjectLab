/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;

/**
 *
 * @author asus
 */
public class CraftScreen extends AbstractAppState{
    public final Node guiNode;
    private final Node localRootNode = new Node("CraftScreen");
    private final AssetManager assetManager;
    private final InputManager inputManager;
    AppSettings settings;
    public boolean close;   //for checking if the close button is clicked or not
    
    public boolean teleportationGate, robot, spaceStation;

    //Constructor
    public CraftScreen(SimpleApplication app, AppSettings _settings) {
        guiNode = app.getGuiNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        settings = _settings;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        /* BACKGROUND */
        super.initialize(stateManager, app);
        Picture Background = new Picture("backgroundscreen");                               //name
        Background.setImage(assetManager, "Interface/CraftMenu/Background.png", false);    //reference
        Background.move(0, 0, -2);                                                          //position
        Background.setWidth(settings.getWidth());                                           //width
        Background.setHeight(settings.getHeight());                                         //height
        guiNode.attachChild(Background);

        //Craft Teleportation Gate Banner
        Picture TeleportationBanner = new Picture("TeleportationGate");
        TeleportationBanner.setImage(assetManager, "Interface/CraftMenu/Teleportation.png", false);
        TeleportationBanner.move(settings.getWidth() / 2 - 70, settings.getHeight() / 2 - 70, -2);
        TeleportationBanner.setWidth(350);
        TeleportationBanner.setHeight(550);
        guiNode.attachChild(TeleportationBanner);

        inputManager.addMapping("TeleportationGate", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "TeleportationGate");
        teleportationGate = false;
        
        //Craft Robot Banner
        Picture RobotBanner = new Picture("Robot");
        RobotBanner.setImage(assetManager, "Interface/CraftMenu/Robot.png", false);
        RobotBanner.move(settings.getWidth() / 2 - 70, settings.getHeight() / 2 - 70, -2);
        RobotBanner.setWidth(350);
        RobotBanner.setHeight(550);
        guiNode.attachChild(RobotBanner);

        inputManager.addMapping("Robot", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Robot");
        robot = false;
        
        //Craft Space Station Banner
        Picture SpaceStationBanner = new Picture("SpaceStation");
        SpaceStationBanner.setImage(assetManager, "Interface/CraftMenu/SpaceStation.png", false);
        SpaceStationBanner.move(settings.getWidth() / 2 - 70, settings.getHeight() / 2 - 70, -2);
        SpaceStationBanner.setWidth(350);
        SpaceStationBanner.setHeight(550);
        guiNode.attachChild(SpaceStationBanner);

        inputManager.addMapping("SpaceStation", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "SpaceStation");
        spaceStation = false;
        
        /* CLOSE BUTTON */
        Picture CloseButton = new Picture("CloseButton");                                   //name
        CloseButton.setImage(assetManager, "Interface/CraftMenu/Cancel.png", false);        //reference
        CloseButton.move(settings.getWidth()-100, settings.getHeight()-100, -2);            //position
        CloseButton.setWidth(140);                                                          //width
        CloseButton.setHeight(40);                                                          //height
        guiNode.attachChild(CloseButton);

        inputManager.addMapping("Close", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Close");
        close = false;
    }

    //Close button action listener
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("TeleportationGate") && keyPressed) {
                close = ((inputManager.getCursorPosition().getX() > settings.getWidth()-100)
                        && (inputManager.getCursorPosition().getX() < settings.getWidth()-50)
                        && (inputManager.getCursorPosition().getY() > settings.getHeight()-100)
                        && (inputManager.getCursorPosition().getY() < settings.getHeight()-50));
            }
            else if (name.equals("Robot") && keyPressed) {
                close = ((inputManager.getCursorPosition().getX() > settings.getWidth()-100)
                        && (inputManager.getCursorPosition().getX() < settings.getWidth()-50)
                        && (inputManager.getCursorPosition().getY() > settings.getHeight()-100)
                        && (inputManager.getCursorPosition().getY() < settings.getHeight()-50));
            }
            else if (name.equals("SpaceStation") && keyPressed) {
                close = ((inputManager.getCursorPosition().getX() > settings.getWidth()-100)
                        && (inputManager.getCursorPosition().getX() < settings.getWidth()-50)
                        && (inputManager.getCursorPosition().getY() > settings.getHeight()-100)
                        && (inputManager.getCursorPosition().getY() < settings.getHeight()-50));
            }
            else if (name.equals("Close") && keyPressed) {
                close = ((inputManager.getCursorPosition().getX() > settings.getWidth()-100)
                        && (inputManager.getCursorPosition().getX() < settings.getWidth()-50)
                        && (inputManager.getCursorPosition().getY() > settings.getHeight()-100)
                        && (inputManager.getCursorPosition().getY() < settings.getHeight()-50));
            }
        }
    };

    @Override
    public void update(float tpf) {
        if (close) {
            guiNode.detachChildNamed("CloseButton");
            guiNode.detachChildNamed("backgroundscreen");
        }

    }

    @Override
    public void cleanup() {
        guiNode.detachChild(localRootNode);

        super.cleanup();
    }
}
