package com.yenitsrm.damlaoyunu.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.yenitsrm.damlaoyunu.DamlaOyunu;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 800;
        config.height = 480;


		new LwjglApplication(new DamlaOyunu(), config);
	}
}
