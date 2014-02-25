package screen;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import react.UnitSlot;
import sprite.Zealot;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import org.jbox2d.pooling.IWorldPool;
import static playn.core.PlayN.graphics;

public class TestScreen extends UIScreen {
//---------------------------------------------------LAB-5---------------------------------------------------------{
    public  static float M_PER_PIXEL = 1 / 26.666667f;
    private static int width = 24;
    private static int height = 18;
    private World world;
    private DebugDrawBox2D debugDraw;
    private boolean showDebugDraw = true ;
//---------------------------------------------------LAB-5---------------------------------------------------------}
//---------------------------------------------------LAB-4---------------------------------------------------------{
    private final ScreenStack ss;
    private Root root;
    private Zealot z ;

    public TestScreen(ScreenStack ss){
        this.ss = ss;
    }
//---------------------------------------------------LAB-4---------------------------------------------------------}
    @Override
    public void wasAdded(){
        super.wasAdded();

        Vec2 gravity = new Vec2(0.0f, 10.0f);
        world = new World(gravity, true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
//                if(contact.getFixtureA().getBody() == z.body() ||
//                        contact.getFixtureB().getBody() == z.body()){
//                            z.contact(contact);
//                }
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {
            }

            @Override
            public void postSolve(Contact contact , ContactImpulse contactImpulse) {
            }
        });


        if (showDebugDraw) {
            CanvasImage image = graphics().createImage(
                    (int) (width / TestScreen.M_PER_PIXEL),
                    (int) (height / TestScreen.M_PER_PIXEL));
            layer.add(graphics().createImageLayer(image));
            debugDraw = new DebugDrawBox2D();
            debugDraw.setCanvas(image);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_shapeBit | DebugDraw.e_jointBit | DebugDraw.e_aabbBit);
            debugDraw.setCamera(0, 0, 1f / TestScreen.M_PER_PIXEL);
            world.setDebugDraw(debugDraw);
        }

        Body ground = world.createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(2f, height-2),new Vec2(width-2f, height-2));
        ground.createFixture(groundShape, 0.0f);
//
//        Body ground1 = world.createBody(new BodyDef());
//        PolygonShape groundShape1 = new PolygonShape();
//        groundShape.setAsEdge(new Vec2(2f, height-478),new Vec2(width-2f, height-478));
//        ground.createFixture(groundShape, 0.0f);

        final Body a = createBox(world,5,15);

        Body b = createBox(world,10,15);

        Image l = PlayN.assets().getImage("images/l.png");
        ImageLayer lLayer = graphics().createImageLayer(l);
        layer.add(lLayer);
        lLayer.setTranslation(42,0);

        Image u = PlayN.assets().getImage("images/u.png");
        ImageLayer uLayer = graphics().createImageLayer(u);
        layer.add(uLayer);
        uLayer.setTranslation(84,0);

        Image r = PlayN.assets().getImage("images/r.png");
        ImageLayer rLayer = graphics().createImageLayer(r);
        layer.add(rLayer);
        rLayer.setTranslation(126,0);

        rLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                a.applyLinearImpulse(new Vec2(100f, 20f), a.getPosition());
            }
        });

        uLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                a.applyForce(new Vec2(-10f, -800f), a.getPosition());
            }
        });

        lLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.remove(ss.top());
            }
        });



//        Image bgImage = PlayN.assets().getImage("images/bg.png");
//        bgImage.addCallback(new Callback<Image>(){
//            @Override
//            public void onSuccess(Image result){
//
//            }
//            @Override
//            public void onFailure(Throwable cause){
//
//            }
//        });
//        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
//        layer.add(bgLayer);
        z = new Zealot(world,500f,100f);
        this.layer.add(z.layer());
    }
//    public void contact(Contact contact){
//        contacted = true;
//        contactCheck = 0;
//
//        if(State == Zealot.State.RUN){
//            state = State.FUN;
//        }
//    }


    private Body createBox(World world, float x, float y) {
//        BodyDef bf = new BodyDef();
//        bf.type = BodyType.DYNAMIC;
//        bf.position =new Vec2(0, 0);
//
//        Body body = world.createBody(bf);
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(1f , 1f);
//        FixtureDef fd = new FixtureDef();
//        fd.shape = shape;
//        fd.density = 0.1f;
//        fd.friction = 0.1f;
//        fd.restitution = 1f;
//        body.createFixture(fd);
//        body.setLinearDamping(0.5f);
//        body.setTransform(new Vec2(10f, 0f),0);

        BodyDef bf = new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0, 0);

        Body body = world.createBody(bf);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f , 1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 2f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        body.setTransform(new Vec2(x,y),0);
        return body;
    }

//---------------------------------------------------LAB-3---------------------------------------------------------{

//    @Override
//    public void wasShown() {
//        super.wasShown();
//        root = iface.createRoot(
//                AxisLayout.vertical().gap(15),
//                SimpleStyles.newSheet(), layer);
//        root.setSize(width(), height());
//        root.add(new Button("back").onClick(new UnitSlot(){
//            public void onEmit(){
//                ss.remove(ss.top());
//            }
//        }));
//    }
//---------------------------------------------------LAB-3---------------------------------------------------------}
    @Override
    public void update(int delta) {
        super.update(delta);
        world.step(0.033f, 10, 10);
       // body.setTransfromation(new Vec2(x,y),0f);
        z.update(delta);
        //b.update(delta);
    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        //b.paint(clock);
        z.paint(clock);
        if (showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
    }
}
