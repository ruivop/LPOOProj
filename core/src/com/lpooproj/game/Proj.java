package com.lpooproj.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.PlayScrene;

public class Proj extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 9*16;
	public static final int V_HEIGHT = 9*16;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScrene(this));

	}

	@Override
	public void render () {
		super.render();
	}
}
