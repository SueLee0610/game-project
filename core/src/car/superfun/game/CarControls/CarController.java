package car.superfun.game.CarControls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import car.superfun.game.observerPattern.Subject;

/**
 * Created by kristian on 06.03.18.
 */

public class CarController extends Subject {
    public float slider1Position;
    public float slider2Position;

    public float forward; // Value range is [-1, 1]
    public float rotation; // Value range is [-1, 1], where -1 means left and 1 means right

    private Texture slider1Texture;
    private Texture slider2Texture;

    private Texture knob1Texture;
    private Texture knob2Texture;

    private int knobRadius = 50;
    private int sliderWidth = 120;

    public CarController() {
        super();
        slider1Position = Gdx.graphics.getHeight() / 2;
        slider2Position = Gdx.graphics.getHeight() / 2;

        slider1Texture = new Texture("slider.png");
        slider2Texture = new Texture("slider.png");

        knob1Texture = new Texture("slider_knob.png");
        knob2Texture = new Texture("slider_knob.png");
    }

    public void update() {
        boolean slider1Touched = false;
        boolean slider2Touched = false;
        for (int i = 0; i < 5; i++) {
            if (Gdx.input.isTouched(i)) {
                Vector2 justTouched = new Vector2(Gdx.input.getX(i), Gdx.input.getY(i) * (-1) + Gdx.graphics.getHeight());
                if (justTouched.x < Gdx.graphics.getWidth() / 8) {
                    slider1Touched = true;
                    slider1Position =
                            (((Gdx.graphics.getHeight() / 2) - 50 > justTouched.y)
                                    || (justTouched.y > (Gdx.graphics.getHeight() / 2) + 50))
                                    ? justTouched.y :
                                    Gdx.graphics.getHeight() / 2;
                }
                if (justTouched.x > 7 * Gdx.graphics.getWidth() / 8) {
                    slider2Touched = true;
                    slider2Position =
                            (((Gdx.graphics.getHeight() / 2) - 50 > justTouched.y)
                                    || (justTouched.y > (Gdx.graphics.getHeight() / 2) + 50))
                                    ? justTouched.y :
                                    Gdx.graphics.getHeight() / 2;
                }
            }
        }
        if (!slider1Touched) {
//          slider1Position = Gdx.graphics.getHeight() / 2;
//          slider1Position = (Gdx.graphics.getHeight() / 2) * (1 + forward*0.75f);
            slider1Position = slider1Position - (slider1Position - Gdx.graphics.getHeight() / 2) / 5;
        }
        if (!slider2Touched) {
//          slider2Position = Gdx.graphics.getHeight() / 2;
//          slider2Position = (Gdx.graphics.getHeight() / 2) * (1 + forward*0.75f);
            slider2Position = slider2Position - (slider2Position - Gdx.graphics.getHeight() / 2) / 5;
        }

        forward = Math.max(-1f, Math.min(1f, (slider1Position + slider2Position - Gdx.graphics.getHeight()) / (Gdx.graphics.getHeight() * 0.8f)));
        rotation = Math.max(-1f, Math.min(1f, (slider2Position - slider1Position) / (Gdx.graphics.getHeight() * 0.8f)));

        notifyObservers();
    }

    public void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(slider1Texture, 0, 0, slider1Texture.getWidth(), Gdx.graphics.getHeight());
        sb.draw(slider2Texture, Gdx.graphics.getWidth() - sliderWidth, 0, slider1Texture.getWidth(), Gdx.graphics.getHeight());

        sb.draw(knob1Texture, (sliderWidth / 2) - knobRadius, slider1Position - knobRadius);
        sb.draw(knob2Texture, Gdx.graphics.getWidth() - (sliderWidth / 2) - knobRadius, slider2Position - knobRadius);

        sb.end();
    }

}
