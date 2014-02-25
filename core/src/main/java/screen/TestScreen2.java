package screen;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.Pointer;
import playn.core.util.Clock;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;

import static playn.core.PlayN.graphics;

public class TestScreen2 extends UIScreen {
    private final ScreenStack ss ;
    public TestScreen2(ScreenStack ss){
        this.ss = ss;
    }
    @Override
    public void wasAdded(){
        super.wasAdded();
        Image r = PlayN.assets().getImage("images/l.png");
        ImageLayer lLayer = graphics().createImageLayer(r);
        layer.add(lLayer);
        lLayer.setTranslation(126,0);

        lLayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.remove(ss.top());
            }
        });
    }
}
