package com.yenitsrm.damlaoyunu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;


public class DamlaOyunu extends ApplicationAdapter {

    private SpriteBatch sayfa;
    private Texture arkaplan, kova, damla;
    private OrthographicCamera kamera;
    private Rectangle rctKova;
    private Array<Rectangle> damlalar;
    private long sonDamlamaZamani;
    private Sound sesDamla;

    @Override
    public void create() {

        kamera = new OrthographicCamera();
        kamera.setToOrtho(false, 800, 480);

        sayfa = new SpriteBatch();

        arkaplan = new Texture("arkaplan.png");

        kova = new Texture("kova.png");
        rctKova = new Rectangle();
        rctKova.width = 64;
        rctKova.height = 64;
        rctKova.x = 800 / 2 - rctKova.width / 2;
        rctKova.y = 20;

        damla = new Texture("damla.png");
        damlalar = new Array<Rectangle>();

        damlaUret();

        sesDamla = Gdx.audio.newSound(Gdx.files.internal("damla.mp3"));
    }

    private void damlaUret() {
        Rectangle rctDamla = new Rectangle();
        rctDamla.width = 64;
        rctDamla.height = 64;
        rctDamla.x = MathUtils.random(0, 800 - 64);
        rctDamla.y = 480;
        damlalar.add(rctDamla);
        sonDamlamaZamani = TimeUtils.nanoTime();
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        kamera.update();

        sayfa.setProjectionMatrix(kamera.combined);

        sayfa.begin();
        sayfa.draw(arkaplan, 0, 0);
        sayfa.draw(kova, rctKova.x, rctKova.y);
        for(Rectangle rctDamla : damlalar){
            sayfa.draw(damla, rctDamla.x, rctDamla.y);
        }
        sayfa.end();

        if(Gdx.input.isTouched()){
            Vector3 dokunmaP = new Vector3();
            dokunmaP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            kamera.unproject(dokunmaP);
            rctKova.x = dokunmaP.x - rctKova.width / 2;
        }

        if(TimeUtils.nanoTime() - sonDamlamaZamani > 1000000000){
            damlaUret();
        }

        Iterator<Rectangle> damla = damlalar.iterator();
        while (damla.hasNext()){
            Rectangle rctDamla = damla.next();
            rctDamla.y -= 200 * Gdx.graphics.getDeltaTime();

            if(rctDamla.y + 64 < 0){
                damla.remove();
            }

            if(rctDamla.overlaps(rctKova)){
                sesDamla.play();
                damla.remove();
            }
        }

    }

    @Override
    public void dispose() {
        arkaplan.dispose();
        kova.dispose();
        damla.dispose();
        sesDamla.dispose();
    }
}
