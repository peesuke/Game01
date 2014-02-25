package sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.Keyboard;
import playn.core.Layer;
import playn.core.PlayN;
import playn.core.Pointer;
import playn.core.util.Callback;
import playn.core.util.Clock;
import screen.TestScreen;

public class Zealot {

    private Sprite sprite ;
    private int spriteIndex = 0 ;
    private boolean hasLoaded = false ;
    private int hp = 100 ;
    private int t ;
    private Body body;

    public enum State {
        RUN,FUN,DIE
    };

    private State state = State.RUN;

    private int e = 0;
    private int offset = 0;

    public Zealot(final World world ,final float x,final float y){
        this.sprite = SpriteLoader.getSprite("images/cat.json");
        this.sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result){
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width() / 2f, sprite.height() / 2f);
                sprite.layer().setTranslation(x,y);

                body = initPhysicBody(world,
                        TestScreen.M_PER_PIXEL * x,
                        TestScreen.M_PER_PIXEL * y);
                hasLoaded = true ;
            }
            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("Error loading image!", cause);
            }
        });
        PlayN.keyboard().setListener(new Keyboard.Listener() {
            @Override
            public void onKeyDown(Keyboard.Event event) {
                state = State.FUN;
                spriteIndex = -1;
                e = 0;
            }

            @Override
            public void onKeyTyped(Keyboard.TypedEvent typedEvent) {

            }

            @Override
            public void onKeyUp(Keyboard.Event event) {

            }
        });
        sprite.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event){
                state = State.DIE;
                spriteIndex = -1;
                e = 0 ;
                t = 0 ;
            }

        });
    }
    private Body initPhysicBody(World world, float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0,0);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(258 * TestScreen.M_PER_PIXEL / 2,
                sprite.layer().height()* TestScreen.M_PER_PIXEL / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.4f;
        fixtureDef.friction = 0.1f;
        body.createFixture(fixtureDef);
        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);
        return body;
    }
    public Layer layer(){
        return sprite.layer();
    }

    public void update(int delta) {
        if (!hasLoaded) return;
        e += delta;
        t += delta;
//        if(t > 1000){
//            hp -= 50 ;
//            t =0 ;
//        }
//        if(hp < 0){
//            state = State.DIE;
//        }
        if(e > 50){
            switch(state){
                case RUN: offset = 0 ;
                            break;
                case FUN: offset = 12 ;
                            break;
                case DIE: offset = 24 ;
                            if(spriteIndex == 35){
                                state = State.RUN;
                            }
                            break;
            }
            //if(spriteIndex == 35){
            //    spriteIndex = spriteIndex + 0;
            //}else{
                spriteIndex = offset + ((spriteIndex + 1) % 12);
                sprite.setSprite(spriteIndex);
                e = 0 ;
            //}
        }
    }
    public void paint(Clock clock){
        if(!hasLoaded) return;
        sprite.layer().setTranslation(
                (body.getPosition().x / TestScreen.M_PER_PIXEL),
                body.getPosition().y / TestScreen.M_PER_PIXEL);
    }

}
