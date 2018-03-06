package car.superfun.game.menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import car.superfun.game.CarSuperFun;
import car.superfun.game.states.GameStateManager;
import car.superfun.game.states.State;

/**
 * Created by Jonas on 06.03.2018.
 */

public class MainMenu extends State {
    private Texture background, button, settings;

    public MainMenu(){
        background = new Texture("background.png");
        settings = new Texture("cogwheel.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            //Gdx.app.log("X", Integer.toString(Gdx.input.getX()));
            //Gdx.app.log("Y", Integer.toString(Gdx.input.getY()));
            // height: 1080, width: 1796
            if(isOnSettings()){
                Gdx.app.log("Touched", "heyo");
                GameStateManager.getInstance().push(new SettingsMenu());
            }/*if(isOnHostGame()){

            }*/

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(settings, 1600, 890);
        sb.end();
    }

    @Override
    public void dispose() {
        settings.dispose();
        background.dispose();
    }

    public boolean isOnSettings(){
        Circle textureBounds = new Circle(1600+settings.getWidth()/2, (Gdx.graphics.getHeight() - 890)-settings.getHeight()/2, settings.getWidth()/2);
        if(textureBounds.contains(Gdx.input.getX(), Gdx.input.getY())){
            return true;
        }else{
            return false;
        }
    }
}
