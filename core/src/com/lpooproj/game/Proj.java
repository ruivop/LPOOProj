package com.lpooproj.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import Screnes.PlayScrene;

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
