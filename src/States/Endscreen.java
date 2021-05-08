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
public class Endscreen extends AbstractAppState{
    public final Node guiNode;
    private final Node localRootNode = new Node("EndScreen");
    private final AssetManager assetManager;
    private final InputManager inputManager;
    AppSettings settings;
    public boolean close;   //for checking if the close button is clicked or not
    boolean won;
    
    public boolean restart, exit;
    
    //Constructor
    public Endscreen(SimpleApplication app, AppSettings _settings, boolean _won) {
        guiNode = app.getGuiNode();
        assetManager = app.getAssetManager();
        inputManager = app.getInputManager();
        settings = _settings;
        won = _won;
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        /* BACKGROUND */
        super.initialize(stateManager, app);
        Picture Background = new Picture("endScreenBanner");
        
        if(won)
            Background.setImage(assetManager, "Interface/EndScreen/won.png", false);
        else
            Background.setImage(assetManager, "Interface/EndScreen/lost.png", false);//reference
        
        Background.move(0, settings.getHeight()/2 - 200, -2);                        //position
        Background.setWidth(settings.getWidth());                                    //width
        Background.setHeight(400);                                                   //height
        guiNode.attachChild(Background);
        
        //Exit Button
        Picture ExitButton = new Picture("StartButton");
        ExitButton.setImage(assetManager, "Interface/EndScreen/exit.png", false);
        ExitButton.move(settings.getWidth() / 2 -70, settings.getHeight() / 2 - 250, -2);
        ExitButton.setWidth(140);
        ExitButton.setHeight(40);
        guiNode.attachChild(ExitButton);

        inputManager.addMapping("Exit", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Exit");
        exit = false;
        
        //Restart Button
//        Picture RestartButton = new Picture("RestartButton");
//        RestartButton.setImage(assetManager, "Interface/EndScreen/restart.png", false);
//        RestartButton.move(settings.getWidth() / 2 - 145, settings.getHeight() / 2 - 250, -2);
//        RestartButton.setWidth(140);
//        RestartButton.setHeight(40);
//        guiNode.attachChild(RestartButton);
//
//        inputManager.addMapping("Restart", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
//        inputManager.addListener(actionListener, "Restart");
//        restart = false;
    }
    
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Exit") && keyPressed) {
                exit = ((inputManager.getCursorPosition().getX() > settings.getWidth() / 2 - 70 )
                        && (inputManager.getCursorPosition().getX() < settings.getWidth() / 2 + 70)
                        && (inputManager.getCursorPosition().getY() > settings.getHeight() / 2 - 250)
                        && (inputManager.getCursorPosition().getY() < settings.getHeight() / 2 - 210));
                System.out.println("exit:"+Boolean.toString(exit));
            }
//            else if (name.equals("Restart") && keyPressed){
//                restart = ((inputManager.getCursorPosition().getX() > settings.getWidth() / 2 - 145)
//                        && (inputManager.getCursorPosition().getX() < settings.getWidth() / 2 - 5)
//                        && (inputManager.getCursorPosition().getY() > settings.getHeight() / 2 - 250)
//                        && (inputManager.getCursorPosition().getY() < settings.getHeight() / 2 - 210));
//            }
        }
    };
    
    @Override
    public void update(float tpf) {
        
    }
    
    @Override
    public void cleanup() {
        guiNode.detachChild(localRootNode);

        super.cleanup();
    }
}
