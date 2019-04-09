package com.yusuf.damlaoyunu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;


import java.sql.Time;
import java.util.Iterator;


public class DamlaOyunu extends ApplicationAdapter implements InputProcessor {
	//damla = siyah balon
	public SpriteBatch sayfa;
	public Texture arkaplan, siyahbalon,kirmizibalon,yesilbalon,saribalon,ses,misket,iknciharita,ucuncuharita,gunes,kus,gameover,youwin,hakkimizda,haritalar;
	public OrthographicCamera kamera;
	public Rectangle rctKova,rctses,rctmisket,rctgunes,rctkus;
	public Array<Rectangle> damlalar/*siyah*/,balonlar/*kırmızı*/,sarilar,yesiller,misketler,gunesler,kuslar;
	public long sonDamlamaZamani,sonDamlamaZamani2=0 ;  //siyah
	public long sonkirmizizamani;
	public long sonsarizamani;
	public long sonyesilzamani,sonyesilzamani2=0,oyunbaslamazamani=0,sonkuszamani;
	public Sound sesDamla;
	public Music fon,fon2,tada,aww;
	public Vector3 dokunmaP;
	public boolean touchActive=false;
	public int durum=0, puan =0,red=0,green=0,yellow=0,black=0;
	public long sayac=0,sayac1=0,sayac2=0,tadaw=0;
	public int calssdurum=0,durum2=1,puan1=0,puan2=0,puan3=0,kusx=0,kusy=0;
	public Texture girisarkaplan;
	Rectangle rctkus1;

	public void getFon()
	{
		fon.setLooping(true);
		fon.play();
	}

	public void anamenu()
	{
		if (dokunmaP.x > 750 && dokunmaP.x < 800 && dokunmaP.y > 0 && dokunmaP.y < 50) {
			puan =0;
			red=0;
			green=0;
			yellow=0;
			black=0;
			oyunbaslamazamani=TimeUtils.millis();
			calssdurum=0;


		}
	}

	public void getFon2()
	{
		fon2.setLooping(true);
		fon2.play();
	}

	public void damlasesdegis()
	{
		if (dokunmaP.x > 0 && dokunmaP.x < 75 && dokunmaP.y > 400 && dokunmaP.y < 480) {
			if (durum2==0)
			{
				durum2=1;
			}
			else if(durum2==1)
			{
				durum2=0;
			}
		}
	}
	@Override
	public void create()
	{
		oyunbaslamazamani=TimeUtils.millis();
		kamera = new OrthographicCamera();
		kamera.setToOrtho(false, 800, 480);
		sayfa = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		arkaplan = new Texture("arkaplan.png");
		haritalar = new Texture("haritalar.png");
		hakkimizda = new Texture("hakkimizda.png");
		gameover = new Texture("gameover.png");
		youwin = new Texture("win.png");
		iknciharita = new Texture("map2.png");
		ucuncuharita= new Texture("map3.png");
		girisarkaplan = new Texture("girisarkaplan.png");
		fon = Gdx.audio.newMusic(Gdx.files.internal("fon.mp3"));
		fon2 = Gdx.audio.newMusic(Gdx.files.internal("fon2.mp3"));
		//kova = new Texture("kova.png");
		rctKova = new Rectangle();
		rctKova.width = 25;
		rctKova.height = 25;
		rctKova.x =-200 /*800 / 2 - rctKova.width / 2*/;
		rctKova.y = -200;

		siyahbalon=new Texture(Gdx.files.internal("damla.png"));//balon resimini ekledim
		kirmizibalon=new Texture(Gdx.files.internal("kirmizibalon.png"));//balon resimini ekledim
		kus=new Texture(Gdx.files.internal("kus.png"));
		yesilbalon=new Texture(Gdx.files.internal("yesil.png"));
		saribalon=new Texture(Gdx.files.internal("sari.png"));
		damlalar = new Array<Rectangle>();
		balonlar = new Array<Rectangle>();
		yesiller=new Array<Rectangle>();
		sarilar=new Array<Rectangle>();
		kuslar = new Array<Rectangle>();
		damlaUret();
		balonuret();
		yesiluret();
		sariuret();
		kusuret();
		sesDamla = Gdx.audio.newSound(Gdx.files.internal("damla.mp3"));
		tada = Gdx.audio.newMusic(Gdx.files.internal("tada.mp3"));
		aww = Gdx.audio.newMusic(Gdx.files.internal("aww.MP3"));
		ses = new Texture("ses.png");
		misket = new Texture("misket.png");
		rctses = new Rectangle(719,400,64,64);
		rctmisket = new Rectangle(350,300,80,100);
		gunes = new Texture("gunes.png");
		rctgunes = new Rectangle(350,300,80,100);

		rctkus = new Rectangle(184,180,80,100);

	}

	public void kusuret()
	{//metot tanımladık bu metotdu her çagırdıgımda balon üretmeyi yapacagım

		rctkus1=new Rectangle();
		rctkus1.width=100;
		rctkus1.height=50;
		rctkus1.x= 800;
		rctkus1.y=315;
		kuslar.add(rctkus1);
		sonkuszamani= TimeUtils.nanoTime();


	}

	public void yesiluret()
	{//metot tanımladık bu metotdu her çagırdıgımda balon üretmeyi yapacagım

		Rectangle rctyesil=new Rectangle();
		rctyesil.width=82;
		rctyesil.height=82;
		rctyesil.x= MathUtils.random(0,750-64);
		rctyesil.y=-64;
		yesiller.add(rctyesil);
		sonyesilzamani= TimeUtils.nanoTime();

	}

	public void sariuret()
	{//metot tanımladık bu metotdu her çagırdıgımda balon üretmeyi yapacagım

		Rectangle rctsari=new Rectangle();
		rctsari.width=64;
		rctsari.height=64;
		rctsari.x= MathUtils.random(0,750-64);
		rctsari.y=-MathUtils.random(-250,480-64);
		sarilar.add(rctsari);
		sonsarizamani= TimeUtils.nanoTime();
	}

	public void damlaUret()
	{
		Rectangle rctDamla = new Rectangle();
		rctDamla.width = 64;
		rctDamla.height = 64;
		rctDamla.x = MathUtils.random(0, 750 - 64);
		rctDamla.y = -64;
		damlalar.add(rctDamla);
		sonDamlamaZamani = TimeUtils.nanoTime();

	}

	public void balonuret()
	{//metot tanımladık bu metotdu her çagırdıgımda balon üretmeyi yapacagım

		Rectangle rctbalon=new Rectangle();
		rctbalon.width=64;
		rctbalon.height=64;
		rctbalon.x= MathUtils.random(0,750-64);//random olarak sagda solda oluşmasını sağlıyor
		rctbalon.y=-64;
		balonlar.add(rctbalon);
		sonkirmizizamani= TimeUtils.nanoTime();

	}

	public void durum4()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		kamera.update();
		sayfa.setProjectionMatrix(kamera.combined);
		BitmapFont font = new BitmapFont();
		sayfa.begin();
		sayfa.draw(gameover, 0, 0);
		font.setColor(Color.WHITE);
		font.draw(sayfa, "1. Map : "+puan1+"    2. Map : "+puan2+"    3. Map : "+puan3, 350, 365);
		font.draw(sayfa, "HIGH SCORE : "+puan, 90, 365);
		sayfa.draw(ses,rctses.x,rctses.y);
		sayfa.end();
		//getFon2();
		if(tadaw==0) {
			aww.play();
			//	if(!tada.isPlaying())
			tadaw=1;
		}

		if (touchActive) {
			Vector3 dokunmaP = new Vector3();
			dokunmaP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			kamera.unproject(dokunmaP);
			rctKova.x = dokunmaP.x - rctKova.width / 2;
			rctKova.y = dokunmaP.y - rctKova.height / 2;
			if (rctses.contains(dokunmaP.x,dokunmaP.y))
			{

				if (durum==0)
				{
					getFon();
					durum=1;
				}
				else if(durum==1)
				{
					fon.pause();
					durum=0;
				}
			}

			if (dokunmaP.x > 103 && dokunmaP.x < 695 && dokunmaP.y > 86 && dokunmaP.y < 172) {
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=0;


			}
		}
	}

	public void durum5()
	{

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		kamera.update();
		sayfa.setProjectionMatrix(kamera.combined);
		BitmapFont font = new BitmapFont();
		sayfa.begin();
		sayfa.draw(youwin, 0, 0);
		font.setColor(Color.WHITE);
		font.draw(sayfa, "1. Map : "+puan1+"    2. Map : "+puan2+"    3. Map : "+puan3, 350, 365);
		font.draw(sayfa, "HIGH SCORE : "+puan, 90, 365);
		sayfa.draw(ses,rctses.x,rctses.y);
		sayfa.end();
		//	getFon2();
		if(tadaw==0) {
			tada.play();
		//	if(!tada.isPlaying())
			tadaw=1;
		}

		if (touchActive) {
			Vector3 dokunmaP = new Vector3();
			dokunmaP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			kamera.unproject(dokunmaP);
			rctKova.x = dokunmaP.x - rctKova.width / 2;
			rctKova.y = dokunmaP.y - rctKova.height / 2;
			if (rctses.contains(dokunmaP.x,dokunmaP.y))
			{
				if (durum==0)
				{
					getFon();
					durum=1;
				}
				else if(durum==1)
				{
					fon.pause();
					durum=0;
				}
			}
			//play
			if (dokunmaP.x > 150 && dokunmaP.x < 656 && dokunmaP.y > 86 && dokunmaP.y < 151) {
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=0;


			}
		}
	}

	public void durum6()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		kamera.update();
		sayfa.setProjectionMatrix(kamera.combined);
		sayfa.begin();
		sayfa.draw(hakkimizda, 0, 0);
		sayfa.draw(ses,rctses.x,rctses.y);
		sayfa.end();
		//	getFon2();


		if (touchActive) {
			Vector3 dokunmaP = new Vector3();
			dokunmaP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			kamera.unproject(dokunmaP);
			rctKova.x = dokunmaP.x - rctKova.width / 2;
			rctKova.y = dokunmaP.y - rctKova.height / 2;
			if (rctses.contains(dokunmaP.x,dokunmaP.y))
			{
				if (durum==0)
				{
					getFon();
					durum=1;
				}
				else if(durum==1)
				{
					fon.pause();
					durum=0;
				}
			}
			//play
			if (dokunmaP.x > 20 && dokunmaP.x < 300 && dokunmaP.y > 15 && dokunmaP.y < 100) {

				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=0;


			}
		}
	}

	public void durum7()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		kamera.update();
		sayfa.setProjectionMatrix(kamera.combined);
		sayfa.begin();
		sayfa.draw(haritalar, 0, 0);
		sayfa.draw(ses,rctses.x,rctses.y);
		sayfa.end();
		//	getFon2();


		if (touchActive) {
			Vector3 dokunmaP = new Vector3();
			dokunmaP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			kamera.unproject(dokunmaP);
			rctKova.x = dokunmaP.x - rctKova.width / 2;
			rctKova.y = dokunmaP.y - rctKova.height / 2;
			if (rctses.contains(dokunmaP.x,dokunmaP.y))
			{
				if (durum==0)
				{
					getFon();
					durum=1;
				}
				else if(durum==1)
				{
					fon.pause();
					durum=0;
				}
			}
			if (dokunmaP.x > 750 && dokunmaP.x < 800 && dokunmaP.y > 9 && dokunmaP.y < 65) {
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=0;


			}
			//harita 1
			if (dokunmaP.x > 328 && dokunmaP.x < 455 && dokunmaP.y > 160 && dokunmaP.y < 326) {
				balonlar.clear();
				damlalar.clear();
				yesiller.clear();
				sarilar.clear();
				kuslar.clear();
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=1;


			}
//harita 2
			if (dokunmaP.x > 465 && dokunmaP.x < 585 && dokunmaP.y > 160 && dokunmaP.y < 326) {
				balonlar.clear();
				damlalar.clear();
				yesiller.clear();
				sarilar.clear();
				kuslar.clear();
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=2;


			}
//harita 3
			if (dokunmaP.x > 600 && dokunmaP.x < 718 && dokunmaP.y > 160 && dokunmaP.y < 326) {
				balonlar.clear();
				damlalar.clear();
				yesiller.clear();
				sarilar.clear();
				kuslar.clear();
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=3;


			}
		}
	}

	public void durum0()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		kamera.update();
		sayfa.setProjectionMatrix(kamera.combined);
		sayfa.begin();
		sayfa.draw(girisarkaplan, 0, 0);
		sayfa.draw(ses,rctses.x,rctses.y);
		sayfa.end();
		//	getFon2();
		if (touchActive) {
			Vector3 dokunmaP = new Vector3();
			dokunmaP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			kamera.unproject(dokunmaP);
			rctKova.x = dokunmaP.x - rctKova.width / 2;
			rctKova.y = dokunmaP.y - rctKova.height / 2;

			if (rctses.contains(dokunmaP.x,dokunmaP.y))
			{
				if (durum==0)
				{
					getFon();
					durum=1;
				}
				else if(durum==1)
				{
					fon.pause();
					durum=0;
				}
			}
			//play
			if (dokunmaP.x > 91 && dokunmaP.x < 178 && dokunmaP.y > 284 && dokunmaP.y < 313) {
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				//	fon2.stop();
				calssdurum=1;
				balonlar.clear();
				damlalar.clear();
				yesiller.clear();
				sarilar.clear();
				kuslar.clear();
			}
			//HaritaSeç
			else if (dokunmaP.x > 91 && dokunmaP.x < 260 && dokunmaP.y > 226 && dokunmaP.y < 258) {
				//System.out.println("Harita Seç");
				balonlar.clear();
				damlalar.clear();
				yesiller.clear();
				sarilar.clear();
				kuslar.clear();
				calssdurum=7;


			}
			//Hakkımızda
			else if (dokunmaP.x > 91 && dokunmaP.x < 283 && dokunmaP.y > 163 && dokunmaP.y < 195) {
				//	System.out.println("Hakkımızda");

				balonlar.clear();
				damlalar.clear();
				yesiller.clear();
				sarilar.clear();
				kuslar.clear();
				calssdurum=6;
			}
			//ÇIKIŞ
			else if (dokunmaP.x > 91 && dokunmaP.x < 172 && dokunmaP.y > 99 && dokunmaP.y < 133) {
				Gdx.app.exit();


			}



		}
	}

	public void durum1()
	{
//	fon2.stop();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		kamera.update();
		BitmapFont font = new BitmapFont();
		font.setColor(Color.PURPLE);
		sayfa.setProjectionMatrix(kamera.combined);

		sayfa.begin();
		sayfa.draw(ucuncuharita, 0, 0);

		font.draw(sayfa, "PUAN3 : "+puan3, 710, 335);
		font.draw(sayfa, "PUAN2 : "+puan2, 710, 350);
		font.draw(sayfa, "PUAN1 : "+puan1, 710, 365);
		font.draw(sayfa, "PUAN : "+puan, 710, 380);
		font.setColor(Color.BLACK);
		font.draw(sayfa, "BLACK : "+black, 710, 320);
		font.setColor(Color.RED);
		font.draw(sayfa, "RED : "+red, 710, 305);
		font.setColor(Color.YELLOW);
		font.draw(sayfa, "YELLOW : "+yellow, 710, 290);
		font.setColor(Color.GREEN);
		font.draw(sayfa, "GREEN : "+green, 710, 275);
		//	sayfa.draw(kova, rctKova.x, rctKova.y);



		for(Rectangle rctDamla : damlalar){
			sayfa.draw(siyahbalon, rctDamla.x, rctDamla.y);
		}
		for(Rectangle rctbalon : balonlar ){
			sayfa.draw(kirmizibalon ,rctbalon.x,rctbalon.y);
		}
		for(Rectangle rctyesil : yesiller ){

			sayfa.draw(yesilbalon,rctyesil.x,rctyesil.y);
		}
		for(Rectangle rctsari : sarilar ){

			sayfa.draw(saribalon,rctsari.x,rctsari.y);
		}
		for(Rectangle rctkus1 : kuslar ){

			sayfa.draw(kus,rctkus1.x,rctkus1.y);
		}
		sayfa.draw(ses,rctses.x,rctses.y);
		sayfa.draw(gunes,360,300);
		//font.setScale(3, 3);

		font.setColor(Color.YELLOW);
		font.draw(sayfa, ""+((TimeUtils.millis()-oyunbaslamazamani)/1000), 390, 470);

		sayfa.end();

		if ((TimeUtils.millis()-oyunbaslamazamani)/1000>=30  && puan1>=100 && red>=1 && green>=1 && yellow>=1 && black>=1)
		{
			balonlar.clear();
			damlalar.clear();
			yesiller.clear();
			sarilar.clear();
			kuslar.clear();
			//puan =0;
			red=0;
			green=0;
			yellow=0;
			black=0;
			oyunbaslamazamani=TimeUtils.millis();
			//		fon2.stop();
			calssdurum=2;

		}

		//GAMEOVER
		if ((TimeUtils.millis()-oyunbaslamazamani)/1000>=30 && !(puan1>=100 && red>=1 && green>=1 && yellow>=1 && black>=1))
		{
			calssdurum=4;
		}

		//if(Gdx.input.isTouched()){
		if(touchActive){
			Vector3 dokunmaP = new Vector3();
			dokunmaP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			kamera.unproject(dokunmaP);
			rctKova.x = dokunmaP.x - rctKova.width / 2;
			rctKova.y = dokunmaP.y - rctKova.height / 2;

			if (dokunmaP.x > -10 && dokunmaP.x < 100 && dokunmaP.y > 390 && dokunmaP.y < 480) {
				if (durum2==0)
				{
					durum2=1;
				}
				else if(durum2==1)
				{
					durum2=0;
				}
			}

			if (rctses.contains(dokunmaP.x,dokunmaP.y))
			{
				if (durum==0)
				{
					getFon();
					durum=1;
				}
				else if(durum==1)
				{
					fon.pause();
					durum=0;
				}
			}

			if (dokunmaP.x > 750 && dokunmaP.x < 800 && dokunmaP.y > 0 && dokunmaP.y < 50) {
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=0;


			}

		}

		if(TimeUtils.nanoTime() - sonDamlamaZamani > 1000000000){
			damlaUret();
		}
		if(TimeUtils.nanoTime()-sonkirmizizamani >1000000000	){
			balonuret();
		}
		if(TimeUtils.nanoTime()-sonyesilzamani >1000000000	){

			yesiluret();
		}
		if(TimeUtils.nanoTime()-sonsarizamani >2100000000 ) {
			sariuret();
		}

		if(TimeUtils.nanoTime() - sonDamlamaZamani > 750000000){
			rctKova.y=-200;
			rctKova.x=-200;
		}


		Iterator<Rectangle> damla = damlalar.iterator();
		while (damla.hasNext() ){
			Rectangle rctDamla = damla.next();
			rctDamla.y += 200 * Gdx.graphics.getDeltaTime();

			if(rctDamla.y + 64 < 0){
				damla.remove();
				rctKova.y=-200;
				rctKova.x=-200;
			}

			if (rctgunes.contains(rctDamla.x,rctDamla.y))
			{
				if (durum2==1)
				{sesDamla.play();}
				damla.remove();
			}

			if(rctDamla.overlaps(rctKova)){
				if (durum2==1)
				{sesDamla.play();}
				puan-=10;
				puan1-=10;
				damla.remove();
				black+=1;
				rctKova.y=-200;
				rctKova.x=-200;
			}
			if (TimeUtils.nanoTime()-sonDamlamaZamani2>2100000000)
			{
				if (rctDamla.y>(MathUtils.random(0,25)) && rctDamla.y<(MathUtils.random(25,50)))
				{

					Rectangle rctyesil = new Rectangle();
					rctyesil.width = 82;
					rctyesil.height = 82;
					rctyesil.x = rctDamla.x;
					rctyesil.y = rctDamla.y;
					yesiller.add(rctyesil);
					damla.remove();
					sonDamlamaZamani2=TimeUtils.nanoTime();
				}


			}

		}

		Iterator<Rectangle> balon = balonlar.iterator();
		while(balon.hasNext()) {
			final Rectangle rctbalon=balon.next();
			rctbalon.y +=240*Gdx.graphics.getDeltaTime();

			Timer.schedule(new Timer.Task(){
							   @Override
							   public void run() {

								   sayac+=1;
								   if (sayac>0  && sayac<=1000 )
								   {

									   rctbalon.y +=80*Gdx.graphics.getDeltaTime();
									   //  rctbalon.x += 75 * Gdx.graphics.getDeltaTime();

								   }
								   if (sayac>1000 && sayac<=2000)
								   {
									   rctbalon.x +=250*Gdx.graphics.getDeltaTime();
									   rctbalon.x -= 150 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>2000 && sayac<=3000)
								   {
									   rctbalon.y +=50*Gdx.graphics.getDeltaTime();
									   rctbalon.x += 100 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>=4000 && sayac<5000)
								   {
									   rctbalon.y +=75*Gdx.graphics.getDeltaTime();
									   rctbalon.x -= 250 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>3000 && sayac<4000)
								   {
									   rctbalon.y +=10*Gdx.graphics.getDeltaTime();
									   rctbalon.x += 300 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>=5000 && sayac<6000)
								   {
									   //  rctbalon.y -=170*Gdx.graphics.getDeltaTime();
									   rctbalon.x += 100 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac==6000)
								   {  sayac=0;
								   }
							   }
						   }
					, 1        //    (delay)
					, 10000     //    (seconds)
			);

			if(rctbalon.y +64 <0){
				balon.remove();
				rctKova.y=-200;
				rctKova.x=-200;


			}

			if(rctbalon.overlaps(rctKova)){
				if (durum2==1)
				{sesDamla.play();}
				balon.remove();
				puan+=10;
				puan1+=10;
				rctKova.y=-200;
				rctKova.x=-200;
				red+=1;

			}

			if (rctgunes.contains(rctbalon.x,rctbalon.y))
			{
				if (durum2==1)
				{sesDamla.play();}
				balon.remove();
			}

		}

		Iterator<Rectangle> yesil = yesiller.iterator();
		while(yesil.hasNext()) {
			final Rectangle rctyesil=yesil.next();
			rctyesil.y +=300*Gdx.graphics.getDeltaTime();

			if (rctgunes.contains(rctyesil.x,rctyesil.y))
			{
				if (durum2==1)
				{sesDamla.play();}
				yesil.remove();
			}

			if(rctyesil.y +64 <0){
				yesil.remove();
				rctKova.y=-200;
				rctKova.x=-200;
			}
			if(rctyesil.overlaps(rctKova)){
				if (durum2==1)
				{sesDamla.play();}
				yesil.remove();
				puan+=5;
				puan1+=5;
				rctKova.y=-200;
				rctKova.x=-200;
				green+=1;
			}
			if (TimeUtils.nanoTime()-sonyesilzamani2>2100000000)
			{
				if (rctyesil.y>(MathUtils.random(150,175)) && rctyesil.y<(MathUtils.random(175,200)))
				{

					Rectangle rctdamla = new Rectangle();
					rctdamla.width = 64;
					rctdamla.height = 64;
					rctdamla.x = rctyesil.x;
					rctdamla.y = rctyesil.y;
					damlalar.add(rctdamla);
					yesil.remove();
					sonyesilzamani2=TimeUtils.nanoTime();
				}
			}

		}

		final Iterator<Rectangle> sari = sarilar.iterator();
		while(sari.hasNext()) {
			Rectangle rctsari=sari.next();
			rctsari.y +=150*Gdx.graphics.getDeltaTime();

			if(rctsari.y +64 <0){
				sari.remove();
				rctKova.y=-200;
				rctKova.x=-200;

			}
			if(rctsari.overlaps(rctKova)){
				if (durum2==1)
				{sesDamla.play();}
				sari.remove();
				puan+=20;
				puan1+=20;
				rctKova.y=-200;
				rctKova.x=-200;
				yellow+=1;
			}
			if (rctgunes.contains(rctsari.x,rctsari.y))
			{
				if (durum2==1)
				{sesDamla.play();}
				sari.remove();
			}

			if(TimeUtils.nanoTime()-sonsarizamani >1500000000 ) {
				sari.remove();
			}

		}
	}

	public void durum2()
	{
		//	fon2.stop();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		kamera.update();
		BitmapFont font = new BitmapFont();
		font.setColor(Color.PURPLE);
		sayfa.setProjectionMatrix(kamera.combined);

		sayfa.begin();
		sayfa.draw(iknciharita, 0, 0);

		font.draw(sayfa, "PUAN3 : "+puan3, 710, 335);
		font.draw(sayfa, "PUAN2 : "+puan2, 710, 350);
		font.draw(sayfa, "PUAN1 : "+puan1, 710, 365);
		font.draw(sayfa, "PUAN : "+puan, 710, 380);
		font.setColor(Color.BLACK);
		font.draw(sayfa, "BLACK : "+black, 710, 320);
		font.setColor(Color.RED);
		font.draw(sayfa, "RED : "+red, 710, 305);
		font.setColor(Color.YELLOW);
		font.draw(sayfa, "YELLOW : "+yellow, 710, 290);
		font.setColor(Color.GREEN);
		font.draw(sayfa, "GREEN : "+green, 710, 275);
		//	sayfa.draw(kova, rctKova.x, rctKova.y);



		for(Rectangle rctDamla : damlalar){
			sayfa.draw(siyahbalon, rctDamla.x, rctDamla.y);
		}
		for(Rectangle rctbalon : balonlar ){
			sayfa.draw(kirmizibalon ,rctbalon.x,rctbalon.y);
		}
		for(Rectangle rctyesil : yesiller ){

			sayfa.draw(yesilbalon,rctyesil.x,rctyesil.y);
		}
		for(Rectangle rctsari : sarilar ){

			sayfa.draw(saribalon,rctsari.x,rctsari.y);
		}
		sayfa.draw(ses,rctses.x,rctses.y);
		sayfa.draw(gunes,360,300);
		//font.setScale(3, 3);

		font.setColor(Color.YELLOW);
		font.draw(sayfa, ""+((TimeUtils.millis()-oyunbaslamazamani)/1000), 390, 470);

		sayfa.end();

		if ((TimeUtils.millis()-oyunbaslamazamani)/1000>=30 && (puan2>=100 && red>=1 && green>=1 && yellow>=1 && black>=1))
		{
			balonlar.clear();
			damlalar.clear();
			yesiller.clear();
			sarilar.clear();
			kuslar.clear();
			//puan =0;
			red=0;
			green=0;
			yellow=0;
			black=0;
			oyunbaslamazamani=TimeUtils.millis();
			//		fon2.stop();
			calssdurum=3;

		}
		//GAMEOVER
		if ((TimeUtils.millis()-oyunbaslamazamani)/1000>=30 && !(puan2>=100 && red>=1 && green>=1 && yellow>=1 && black>=1))
		{
			calssdurum=4;
		}
		//if(Gdx.input.isTouched()){
		if(touchActive){
			Vector3 dokunmaP = new Vector3();
			dokunmaP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			kamera.unproject(dokunmaP);
			rctKova.x = dokunmaP.x - rctKova.width / 2;
			rctKova.y = dokunmaP.y - rctKova.height / 2;

			if (dokunmaP.x > -10 && dokunmaP.x < 100 && dokunmaP.y > 390 && dokunmaP.y < 480) {
				if (durum2==0)
				{
					durum2=1;
				}
				else if(durum2==1)
				{
					durum2=0;
				}
			}

			if (rctses.contains(dokunmaP.x,dokunmaP.y))
			{
				if (durum==0)
				{
					getFon();
					durum=1;
				}
				else if(durum==1)
				{
					fon.pause();
					durum=0;
				}
			}

			if (dokunmaP.x > 750 && dokunmaP.x < 800 && dokunmaP.y > 0 && dokunmaP.y < 50) {
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=0;


			}

		}

		if(TimeUtils.nanoTime() - sonDamlamaZamani > 1000000000){
			damlaUret();
		}
		if(TimeUtils.nanoTime()-sonkirmizizamani >1000000000	){
			balonuret();
		}
		if(TimeUtils.nanoTime()-sonyesilzamani >1000000000	){

			yesiluret();
		}
		if(TimeUtils.nanoTime()-sonsarizamani >2100000000 ) {
			sariuret();
		}







		if(TimeUtils.nanoTime() - sonDamlamaZamani > 750000000){
			rctKova.y=-200;
			rctKova.x=-200;
		}


		Iterator<Rectangle> damla = damlalar.iterator();
		while (damla.hasNext() ){
			Rectangle rctDamla = damla.next();
			rctDamla.y += 180 * Gdx.graphics.getDeltaTime();

			if(rctDamla.y + 64 < 0){
				damla.remove();
				rctKova.y=-200;
				rctKova.x=-200;
			}

			if (rctgunes.contains(rctDamla.x,rctDamla.y))
			{
				if (durum2==1)
				{
					sesDamla.play();
				}

				damla.remove();
			}

			if(rctDamla.overlaps(rctKova)){
				if (durum2==1)
				{
					sesDamla.play();
				}
				puan-=10;
				puan2-=10;
				damla.remove();
				black+=1;
				rctKova.y=-200;
				rctKova.x=-200;
			}
			if (TimeUtils.nanoTime()-sonDamlamaZamani2>2100000000)
			{
				if (rctDamla.y>(MathUtils.random(0,25)) && rctDamla.y<(MathUtils.random(25,50)))
				{

					Rectangle rctyesil = new Rectangle();
					rctyesil.width = 82;
					rctyesil.height = 82;
					rctyesil.x = rctDamla.x;
					rctyesil.y = rctDamla.y;
					yesiller.add(rctyesil);
					damla.remove();
					sonDamlamaZamani2=TimeUtils.nanoTime();
				}


			}

		}

		Iterator<Rectangle> balon = balonlar.iterator();
		while(balon.hasNext()) {
			final Rectangle rctbalon=balon.next();
			rctbalon.y +=250*Gdx.graphics.getDeltaTime();

			Timer.schedule(new Timer.Task(){
							   @Override
							   public void run() {

								   sayac+=1;
								   if (sayac>0  && sayac<=1000 )
								   {

									   rctbalon.y +=80*Gdx.graphics.getDeltaTime();
									   //  rctbalon.x += 75 * Gdx.graphics.getDeltaTime();

								   }
								   if (sayac>1000 && sayac<=2000)
								   {
									   rctbalon.x +=250*Gdx.graphics.getDeltaTime();
									   rctbalon.x -= 150 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>2000 && sayac<=3000)
								   {
									   rctbalon.y +=50*Gdx.graphics.getDeltaTime();
									   rctbalon.x += 100 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>=4000 && sayac<5000)
								   {
									   rctbalon.y +=75*Gdx.graphics.getDeltaTime();
									   rctbalon.x -= 250 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>3000 && sayac<4000)
								   {
									   rctbalon.y +=10*Gdx.graphics.getDeltaTime();
									   rctbalon.x += 300 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>=5000 && sayac<6000)
								   {
									   //  rctbalon.y -=170*Gdx.graphics.getDeltaTime();
									   rctbalon.x += 100 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac==6000)
								   {  sayac=0;
								   }
							   }
						   }
					, 1        //    (delay)
					, 10000     //    (seconds)
			);

			if(rctbalon.y +64 <0){
				balon.remove();
				rctKova.y=-200;
				rctKova.x=-200;


			}

			if(rctbalon.overlaps(rctKova)){
				if (durum2==1)
				{
					sesDamla.play();
				}
				balon.remove();
				puan+=10;
				puan2+=10;
				rctKova.y=-200;
				rctKova.x=-200;
				red+=1;

			}

			if (rctgunes.contains(rctbalon.x,rctbalon.y))
			{
				if (durum2==1)
				{
					sesDamla.play();
				}
				balon.remove();
				puan-=5;
				puan2-=5;
			}

		}

		Iterator<Rectangle> yesil = yesiller.iterator();
		while(yesil.hasNext()) {
			final Rectangle rctyesil=yesil.next();
			rctyesil.y +=305*Gdx.graphics.getDeltaTime();

			if (rctgunes.contains(rctyesil.x,rctyesil.y))
			{if (durum2==1)
			{
				sesDamla.play();
			}
				yesil.remove();
				puan-=3;
				puan2-=3;
			}

			if(rctyesil.y +64 <0){
				yesil.remove();
				rctKova.y=-200;
				rctKova.x=-200;
			}
			if(rctyesil.overlaps(rctKova)){
				if (durum2==1)
				{
					sesDamla.play();
				}
				yesil.remove();
				puan+=5;
				puan2+=5;
				rctKova.y=-200;
				rctKova.x=-200;
				green+=1;
			}
			if (TimeUtils.nanoTime()-sonyesilzamani2>2100000000)
			{
				if (rctyesil.y>(MathUtils.random(150,175)) && rctyesil.y<(MathUtils.random(175,200)))
				{

					Rectangle rctdamla = new Rectangle();
					rctdamla.width = 64;
					rctdamla.height = 64;
					rctdamla.x = rctyesil.x;
					rctdamla.y = rctyesil.y;
					damlalar.add(rctdamla);
					yesil.remove();
					sonyesilzamani2=TimeUtils.nanoTime();
				}
			}

		}

		final Iterator<Rectangle> sari = sarilar.iterator();
		while(sari.hasNext()) {
			Rectangle rctsari=sari.next();
			rctsari.y +=170*Gdx.graphics.getDeltaTime();

			if(rctsari.y +64 <0){
				sari.remove();
				rctKova.y=-200;
				rctKova.x=-200;

			}
			if(rctsari.overlaps(rctKova)){
				if (durum2==1)
				{
					sesDamla.play();
				}
				sari.remove();
				puan+=20;
				puan2+=20;
				rctKova.y=-200;
				rctKova.x=-200;
				yellow+=1;
			}
			if (rctgunes.contains(rctsari.x,rctsari.y))
			{if (durum2==1)
			{
				sesDamla.play();
			}
				sari.remove();
				puan-=10;
				puan2-=10;
			}

			if(TimeUtils.nanoTime()-sonsarizamani >1500000000 ) {
				sari.remove();
			}

		}
	}

	public void durum3()
	{

		//	fon2.stop();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		kamera.update();
		BitmapFont font = new BitmapFont();
		font.setColor(Color.PURPLE);
		sayfa.setProjectionMatrix(kamera.combined);

		sayfa.begin();
		sayfa.draw(arkaplan, 0, 0);

		font.draw(sayfa, "PUAN3 : "+puan3, 710, 335);
		font.draw(sayfa, "PUAN2 : "+puan2, 710, 350);
		font.draw(sayfa, "PUAN1 : "+puan1, 710, 365);
		font.draw(sayfa, "PUAN : "+puan, 710, 380);
		font.setColor(Color.BLACK);
		font.draw(sayfa, "BLACK : "+black, 710, 320);
		font.setColor(Color.RED);
		font.draw(sayfa, "RED : "+red, 710, 305);
		font.setColor(Color.YELLOW);
		font.draw(sayfa, "YELLOW : "+yellow, 710, 290);
		font.setColor(Color.GREEN);
		font.draw(sayfa, "GREEN : "+green, 710, 275);
		//	sayfa.draw(kova, rctKova.x, rctKova.y);
		sayfa.draw(kus, rctkus.x, rctkus.y);


		for(Rectangle rctDamla : damlalar){
			sayfa.draw(siyahbalon, rctDamla.x, rctDamla.y);
		}
		for(Rectangle rctbalon : balonlar ){
			sayfa.draw(kirmizibalon ,rctbalon.x,rctbalon.y);
		}
		for(Rectangle rctyesil : yesiller ){

			sayfa.draw(yesilbalon,rctyesil.x,rctyesil.y);
		}
		for(Rectangle rctkus : kuslar ){

			sayfa.draw(kus,rctkus.x,rctkus.y);
		}
		for(Rectangle rctsari : sarilar ){

			sayfa.draw(saribalon,rctsari.x,rctsari.y);
		}
		sayfa.draw(ses,rctses.x,rctses.y);
		//sayfa.draw(kus,360,300);
		//font.setScale(3, 3);

		font.setColor(Color.YELLOW);
		font.draw(sayfa, ""+((TimeUtils.millis()-oyunbaslamazamani)/1000), 390, 470);

		sayfa.end();

		if ((TimeUtils.millis()-oyunbaslamazamani)/1000>=30 && (puan3>=100 && red>=1 && green>=1 && yellow>=1 && black>=1))
		{

			//puan =0;
			red=0;
			green=0;
			yellow=0;
			black=0;
			oyunbaslamazamani=TimeUtils.millis();
			//	fon2.stop();
			fon.stop();
			calssdurum=5;

		}
		//GAMEOVER
		if ((TimeUtils.millis()-oyunbaslamazamani)/1000>=30 && !(puan3>=100 && red>=1 && green>=1 && yellow>=1 && black>=1))
		{
			calssdurum=4;
		}



		//if(Gdx.input.isTouched()){
		if(touchActive){

			Vector3 dokunmaP = new Vector3();
			dokunmaP.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			kamera.unproject(dokunmaP);
			rctKova.x = dokunmaP.x - rctKova.width / 2;
			rctKova.y = dokunmaP.y - rctKova.height / 2;
			if (dokunmaP.x > -10 && dokunmaP.x < 100 && dokunmaP.y > 390 && dokunmaP.y < 480) {
				if (durum2==0)
				{
					durum2=1;
				}
				else if(durum2==1)
				{
					durum2=0;
				}
			}

			if (rctses.contains(dokunmaP.x,dokunmaP.y))
			{
				if (durum==0)
				{
					getFon();
					durum=1;
				}
				else if(durum==1)
				{
					fon.pause();
					durum=0;
				}
			}

			if (dokunmaP.x > 750 && dokunmaP.x < 800 && dokunmaP.y > 0 && dokunmaP.y < 50) {
				puan =0;
				red=0;
				green=0;
				yellow=0;
				black=0;
				oyunbaslamazamani=TimeUtils.millis();
				calssdurum=0;


			}

		}

		if(TimeUtils.nanoTime() - sonDamlamaZamani > 1000000000){
			damlaUret();
		}
		if(TimeUtils.nanoTime()-sonkirmizizamani >1000000000	){
			balonuret();
		}
		if(TimeUtils.nanoTime()-sonyesilzamani >1000000000	){

			yesiluret();
		}
		if(TimeUtils.nanoTime()-sonsarizamani >2100000000 ) {
			sariuret();
		}

		if(TimeUtils.nanoTime()-sonkuszamani >1500000000	){

			kusuret();
		}






		if(TimeUtils.nanoTime() - sonDamlamaZamani > 750000000){
			rctKova.y=-200;
			rctKova.x=-200;
		}


		Iterator<Rectangle> damla = damlalar.iterator();
		while (damla.hasNext() ){
			Rectangle rctDamla = damla.next();
			rctDamla.y += 200 * Gdx.graphics.getDeltaTime();

			if (rctkus1.contains(rctDamla.x+20,rctDamla.y-40))
			{
				if(durum2==1)
				{sesDamla.play();}
				damla.remove();
			}

			if(rctDamla.y + 64 < 0){
				damla.remove();
				rctKova.y=-200;
				rctKova.x=-200;
			}

			if (rctkus.contains(rctDamla.x,rctDamla.y))
			{	if(durum2==1)
			{sesDamla.play();}
				damla.remove();
			}


			if(rctDamla.overlaps(rctKova)){
				if(durum2==1)
				{sesDamla.play();}
				puan-=10;
				puan3-=10;
				damla.remove();
				black+=1;
				rctKova.y=-200;
				rctKova.x=-200;
			}
			if (TimeUtils.nanoTime()-sonDamlamaZamani2>2100000000)
			{
				if (rctDamla.y>(MathUtils.random(0,25)) && rctDamla.y<(MathUtils.random(25,50)))
				{

					Rectangle rctyesil = new Rectangle();
					rctyesil.width = 82;
					rctyesil.height = 82;
					rctyesil.x = rctDamla.x;
					rctyesil.y = rctDamla.y;
					yesiller.add(rctyesil);
					damla.remove();
					sonDamlamaZamani2=TimeUtils.nanoTime();
				}


			}

		}

		Iterator<Rectangle> balon = balonlar.iterator();
		while(balon.hasNext()) {
			final Rectangle rctbalon=balon.next();
			rctbalon.y +=255*Gdx.graphics.getDeltaTime();

			Timer.schedule(new Timer.Task(){
							   @Override
							   public void run() {

								   sayac+=1;
								   if (sayac>0  && sayac<=1000 )
								   {

									   rctbalon.y +=80*Gdx.graphics.getDeltaTime();
									   //  rctbalon.x += 75 * Gdx.graphics.getDeltaTime();

								   }
								   if (sayac>1000 && sayac<=2000)
								   {
									   rctbalon.x +=250*Gdx.graphics.getDeltaTime();
									   rctbalon.x -= 150 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>2000 && sayac<=3000)
								   {
									   rctbalon.y +=50*Gdx.graphics.getDeltaTime();
									   rctbalon.x += 100 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>=4000 && sayac<5000)
								   {
									   rctbalon.y +=75*Gdx.graphics.getDeltaTime();
									   rctbalon.x -= 250 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>3000 && sayac<4000)
								   {
									   rctbalon.y +=10*Gdx.graphics.getDeltaTime();
									   rctbalon.x += 300 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac>=5000 && sayac<6000)
								   {
									   //  rctbalon.y -=170*Gdx.graphics.getDeltaTime();
									   rctbalon.x += 100 * Gdx.graphics.getDeltaTime();
								   }
								   if (sayac==6000)
								   {  sayac=0;
								   }
							   }
						   }
					, 1        //    (delay)
					, 10000     //    (seconds)
			);

			if(rctbalon.y +64 <0){
				balon.remove();
				rctKova.y=-200;
				rctKova.x=-200;


			}



			if(rctbalon.overlaps(rctKova)){

				if(durum2==1)
				{sesDamla.play();}
				balon.remove();
				puan+=10;
				puan3+=10;
				rctKova.y=-200;
				rctKova.x=-200;
				red+=1;

			}

			if (rctkus.contains(rctbalon.x,rctbalon.y))
			{	if(durum2==1)
			{sesDamla.play();}
				balon.remove();
				puan-=10;
				puan3-=10;
			}
			if (rctbalon.x==rctkus.x && rctbalon.y==rctkus.y)
			{
				if(durum2==1)
				{sesDamla.play();}
				balon.remove();
				puan-=10;
				puan3-=10;
			}

			if (rctkus1.contains(rctbalon.x+20,rctbalon.y-40))
			{
				if(durum2==1)
				{sesDamla.play();}
				balon.remove();
				puan-=10;
				puan3-=10;
			}

		}

		Iterator<Rectangle> kus = kuslar.iterator();
		while(kus.hasNext()) {
			final Rectangle rctkus=kus.next();
			rctkus.x -=310*Gdx.graphics.getDeltaTime();

			if(rctkus.x <=0){
				kus.remove();
				rctKova.y=-200;
				rctKova.x=-200;
			}
			/*if(rctyesil.overlaps(rctKova)){
				if(durum2==1)
				{sesDamla.play();}
				yesil.remove();
				puan+=5;
				puan3+=5;
				rctKova.y=-200;
				rctKova.x=-200;
				green+=1;
			}*/

		}
		Iterator<Rectangle> kuss = kuslar.iterator();
		while(kuss.hasNext()) {
			final Rectangle rctkus1=kuss.next();
			rctkus1.x -=310*Gdx.graphics.getDeltaTime();

			if(rctkus1.x <=0){
				kuss.remove();
			}



		}


		Iterator<Rectangle> yesil = yesiller.iterator();
		while(yesil.hasNext()) {
			final Rectangle rctyesil=yesil.next();
			rctyesil.y +=310*Gdx.graphics.getDeltaTime();

			if (rctkus.contains(rctyesil.x,rctyesil.y))
			{	if(durum2==1)
			{sesDamla.play();}
				yesil.remove();
				puan-=5;
				puan3-=5;
			}

			if (rctkus1.contains(rctyesil.x+20,rctyesil.y-10))
			{
				if(durum2==1)
				{sesDamla.play();}
				yesil.remove();
				puan-=5;
				puan3-=5;
			}

			if(rctyesil.y +64 <0){
				yesil.remove();
				rctKova.y=-200;
				rctKova.x=-200;
			}
			if(rctyesil.overlaps(rctKova)){
				if(durum2==1)
				{sesDamla.play();}
				yesil.remove();
				puan+=5;
				puan3+=5;
				rctKova.y=-200;
				rctKova.x=-200;
				green+=1;
			}
			if (TimeUtils.nanoTime()-sonyesilzamani2>2100000000)
			{
				if (rctyesil.y>(MathUtils.random(150,175)) && rctyesil.y<(MathUtils.random(175,200)))
				{

					Rectangle rctdamla = new Rectangle();
					rctdamla.width = 64;
					rctdamla.height = 64;
					rctdamla.x = rctyesil.x;
					rctdamla.y = rctyesil.y;
					damlalar.add(rctdamla);
					yesil.remove();
					sonyesilzamani2=TimeUtils.nanoTime();
				}
			}

		}

		final Iterator<Rectangle> sari = sarilar.iterator();
		while(sari.hasNext()) {
			Rectangle rctsari=sari.next();
			rctsari.y +=170*Gdx.graphics.getDeltaTime();
			rctsari.x +=80*Gdx.graphics.getDeltaTime();
			if(rctsari.y +64 <0){
				sari.remove();
				rctKova.y=-200;
				rctKova.x=-200;

			}
			if(rctsari.overlaps(rctKova)){
				if(durum2==1)
				{sesDamla.play();}
				sari.remove();
				puan+=20;
				puan3+=20;
				rctKova.y=-200;
				rctKova.x=-200;
				yellow+=1;
			}
			if (rctkus.contains(rctsari.x,rctsari.y))
			{	if(durum2==1)
			{sesDamla.play();}
				sari.remove();
				puan-=20;
				puan3-=20;
			}

			if (rctkus1.contains(rctsari.x+20,rctsari.y-15))
			{
				if(durum2==1)
				{sesDamla.play();}
				sari.remove();
				puan-=20;
				puan3-=20;
			}

			if(TimeUtils.nanoTime()-sonsarizamani >1500000000 ) {
				sari.remove();
			}

		}
	}

	@Override
	public void render()
	{
		//Game Over Ekranı
		if(calssdurum==4)
		{
			durum4();
		}
		//Win Ekranı
		else  if (calssdurum==5)
		{
			durum5();
		}
		//Hakkımızda Ekranı
		else if(calssdurum==6)
		{
			durum6();
		}
		//Harita Seçim Ekranı
		else if(calssdurum==7)
		{
			durum7();
		}

		//Giriş Ekranı
		else if (calssdurum==0)
		{
			puan1=0;
			puan2=0;
			puan3=0;
			tadaw=0;
			durum0();
		}
		//Harita 2
		else if (calssdurum==2)
		{
			durum2();
		}

		//harita 3
		else if (calssdurum==3)
		{
			durum3();
		}

		//Harita 1
		else if(calssdurum==1)
		{
			durum1();
		}
	}

	@Override
	public void dispose()
	{

		arkaplan.dispose();
		siyahbalon.dispose();
		sesDamla.dispose();
		kirmizibalon.dispose();
		fon.dispose();
		saribalon.dispose();
		yesilbalon.dispose();
		ses.dispose();
		sayfa.dispose();
		girisarkaplan.dispose();
		fon2.dispose();
		misket.dispose();
		kus.dispose();
		ucuncuharita.dispose();
		iknciharita.dispose();
		gunes.dispose();
		youwin.dispose();
		gameover.dispose();
		tada.dispose();
		aww.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchActive = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touchActive = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;

	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
