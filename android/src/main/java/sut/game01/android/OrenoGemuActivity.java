package sut.game01.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import sut.game01.core.OrenoGemu;

public class OrenoGemuActivity extends GameActivity {

  @Override
  public void main(){
    PlayN.run(new OrenoGemu());
  }
}
