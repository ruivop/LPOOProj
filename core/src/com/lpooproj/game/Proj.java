package com.lpooproj.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.PlayScreen;

public class Proj extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 13*16;
	public static final int V_HEIGHT = 11*16;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

	}

	@Override
	public void render () {
		super.render();
	}
}
