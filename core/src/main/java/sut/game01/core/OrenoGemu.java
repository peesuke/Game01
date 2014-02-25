package sut.game01.core;

import playn.core.Game;
import playn.core.Keyboard;
import playn.core.PlayN;
import playn.core.util.Clock;
import screen.Homescreen;
import tripleplay.game.ScreenStack;

public class OrenoGemu extends Game.Default {

//  private ImageLayer bgcLayer;
//  private ImageLayer bgcLayer2;
//  private ImageLayer bgcLayer3;
//
//
//  GroupLayer catLayer;
//  List<Cat> cats = new ArrayList<Cat>(0);

  public static final int UPDATE_RATE = 25;
  private ScreenStack ss = new ScreenStack();
  protected final Clock.Source clock = new Clock.Source(UPDATE_RATE);


  public OrenoGemu() {
    super(UPDATE_RATE);
  }
  
  // public OrenoGemu() {
  //   super(33); // call update every 33ms (30 times per second)
  // }

  @Override
  public void init() {
//    // create and add background image layer
//    Image bgImage = assets().getImage("images/bg.png");
//    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
//    graphics().rootLayer().add(bgLayer);
//
//    catLayer = graphics().createGroupLayer();
//    graphics().rootLayer().add(catLayer);
//    assets().getImage(Cat.IMAGE);
//
//    pointer().setListener(new Pointer.Adapter() {
//      @Override
//      public void onPointerEnd(Pointer.Event event) {
//        Cat cat = new Cat(catLayer, event.x(), event.y());
//        cats.add(cat);
//      }
//    });
//
//    // Image bgc = assets().getImage("images/bgc.png");
//    // bgcLayer = graphics().createImageLayer(bgc);
//    // graphics().rootLayer().add(bgcLayer);
//
//    // Image bgc2 = assets().getImage("images/bgc2.png");
//    // bgcLayer2 = graphics().createImageLayer(bgc2);
//    // graphics().rootLayer().add(bgcLayer2);
//
//    // bgcLayer.setOrigin(710,0);
//    // bgcLayer2.setOrigin(0,0);
//
//    //// bgcLayer.setTx(bgcLayer.width()/2);
//    //// bgcLayer.setTy(bgcLayer.height()/2);
//
//    //// bgcLayer.setTranslation(100,230);
//    //// bgcLayer2.setTranslation(180,200);
//    //// bgcLayer.setRotation(0.5);

      ss.push(new Homescreen(ss));
  }

  @Override
  public void update(int delta) {
      ss.update(delta);
//    for (Cat cat : cats) {
//      cat.update(delta);
//    }
////     // x = x + (float)0.4 ;
////     borderx = 640 - bgcLayer.width() ;
////     if(x >= 0 && x <= 540){
////       x = x + speedx ;
////     }else if(x <= 0){
////       speedx = speedx * cb ;
////       x = x + speedx ;
////     }else if(x >= 540){
////       speedx = speedx * cb ;
////       x = x + speedx ;
////     }
////     //x2 = x * cb + 500 ;
//
////     bordery = 480 - bgcLayer.height() ;
////     if(y >= 0 && y <= bordery){
////       y = y + speedy ;
////     }else if(y >= bordery){
////       speedy = speedy * cb ;
////       y = y + speedy ;
////     }else if(y <= 0){
////       speedy = speedy * cb ;
////       y = y + speedy ;
////     }
////     //y2 = y ;
//
//// //======================================================================//
//
////     if(y2 >= 120 && y2 <= 240 ){
////       y2 = y2 + speed2 ;
////     }else if(y2 <= 120){
////       speed2 = speed2 * cb ;
////       y2 = y2 + speed2 ;
////     }else if(y2 >= 240){
////       speed2 = speed2 * cb ;
////       y2 = y2 + speed2 ;
////     }
//
////     x2 -= 0.4 ;
////     if(x2 <= 250){
////       x2 += 0.4 ;
////     }
  }

  @Override
  public void paint(float alpha) {
      clock.paint(alpha);
      ss.paint(clock);
//    for (Cat cat : cats) {
//      cat.paint(alpha);
//    }
//    // // bgcLayer.setRotation(x);
//    // bgcLayer.setTranslation(x,y);
//    // bgcLayer2.setTranslation(x2,y2);
  }
}
