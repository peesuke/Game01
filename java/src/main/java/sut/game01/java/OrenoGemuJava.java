package sut.game01.java;

import playn.core.Game;
import playn.core.PlayN;
import playn.java.JavaPlatform;

import sut.game01.core.OrenoGemu;

public class OrenoGemuJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    // use config to customize the Java platform, if needed
    JavaPlatform.register(config);
    PlayN.run(new OrenoGemu());

//    Game.Default gd = new OrenoGemu();
//    PlayN.run(gd);

     // Object game = new OrenoGemu();
  }
}
