package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.techiespace.projects.fallingnotes.Themes.RedTheme;
import com.techiespace.projects.fallingnotes.Themes.Theme;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

public class FallingNotesScreen implements Screen, InputProcessor {

    public static final String TAG = FallingNotesScreen.class.getName();
    // TODO: Add an ExtendViewport
//    ExtendViewport notesViewport;

    // TODO: Add a ShapeRenderer
    RoundRectShapeRenderer renderer;
    ShapeRenderer lineRenderer;

    // TODO: Add an Icicle
    Notes notes;

    SpriteBatch batch;
    Sprite sprite;
    Piano piano;
    Texture backgroundTexture;
    Sprite backgroundSprite;
    public static Theme theme;

    private boolean isPlaying = false;


    @Override
    public void show() {
        //notesViewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        theme = new RedTheme();

        renderer = new RoundRectShapeRenderer();
        renderer.setAutoShapeType(true);
        lineRenderer = new ShapeRenderer();
        //notes = new Notes(notesViewport);
        notes = new Notes();
        batch = new SpriteBatch();
        sprite = new Sprite();
        piano = new Piano();
        backgroundTexture = theme.getBackgroundTexture();
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegion region = new TextureRegion(backgroundTexture,backgroundTexture.getWidth(),backgroundTexture.getHeight());
        backgroundSprite = new Sprite(region);

      //  backgroundSprite.setSize(1f,1f*backgroundSprite.getHeight()/backgroundSprite.getWidth());
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        /*if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            // your actions

        }*/
        if (isPlaying) {
            notes.update(delta);
            //notesViewport.apply(true);

        }
        // TODO: Clear the screen to the background color
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO: Set the ShapeRenderer's projection matrix
        //renderer.setProjectionMatrix(notesViewport.getCamera().combined);


        batch.begin();
        // backgroundSprite.draw(batch);
        batch.draw(backgroundSprite,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        batch.end();


        //Draw vertical guide line
        lineRenderer.begin(ShapeRenderer.ShapeType.Line);
        lineRenderer.setColor(theme.getVerticalLineColor());   //color alpha not working
        lineRenderer.line(Note.mapCoordinates("C3"), Constants.OFFSET, Note.mapCoordinates("C3"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("C4"), Constants.OFFSET, Note.mapCoordinates("C4"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("C5"), Constants.OFFSET, Note.mapCoordinates("C5"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("C6"), Constants.OFFSET, Note.mapCoordinates("C6"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F2"), Constants.OFFSET, Note.mapCoordinates("F2"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F3"), Constants.OFFSET, Note.mapCoordinates("F3"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F4"), Constants.OFFSET, Note.mapCoordinates("F4"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F5"), Constants.OFFSET, Note.mapCoordinates("F5"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F6"), Constants.OFFSET, Note.mapCoordinates("F6"), Constants.WORLD_HEIGHT);
        Gdx.gl.glDisable(GL20.GL_BLEND);
        lineRenderer.end();

        //Draw the Note
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        notes.render(renderer);
        renderer.end();

        //Draw the piano
        batch.begin();
        piano.render(sprite,batch);
        batch.end();

//        renderer.begin(ShapeRenderer.ShapeType.Filled);
//        renderer.rect(0,0,Constants.WORLD_WIDTH,Constants.OFFSET,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK);
//        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        //notesViewport.update(width, height, true);
        //notes.init(); //required?
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        renderer.dispose();
        lineRenderer.dispose();
    }

    @Override
    public void dispose() {

    }

    //hangling input
    @Override
    public boolean keyDown(int keycode) {
        //https://www.reddit.com/r/libgdx/comments/4223lq/how_will_i_know_when_i_need_to_implement_an/

        String note;
        if (keycode == Input.Keys.SPACE) {
            isPlaying = !isPlaying;
            notes.printFinalList();

        }
        if(keycode == Input.Keys.A)
        {
            note=KeyMapping("A");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.S)
        {
            note=KeyMapping("S");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.D)
        {
            note=KeyMapping("D");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.F)
        {
            note=KeyMapping("F");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.G)
        {
            note=KeyMapping("G");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.H)
        {
            note=KeyMapping("H");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.J)
        {
            note=KeyMapping("J");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.K)
        {
            note=KeyMapping("K");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.L)
        {
            note=KeyMapping("L");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.SEMICOLON)
        {
            note=KeyMapping(";");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.APOSTROPHE)
        {
            note=KeyMapping("'");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.W)
        {
            note=KeyMapping("W");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.E)
        {
            note=KeyMapping("E");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.F)
        {
            note=KeyMapping("F");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.T)
        {
            note=KeyMapping("T");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.W)
        {
            note=KeyMapping("W");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.G)
        {
            note=KeyMapping("G");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.Y)
        {
            note=KeyMapping("Y");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.U)
        {
            note=KeyMapping("U");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.W)
        {
            note=KeyMapping("W");
            notes.handleNote(note);
            notes.addTempNote(note);
        }

        if(keycode == Input.Keys.O)
        {
            note=KeyMapping("O");
            notes.handleNote(note);
            notes.addTempNote(note);
        }
        if(keycode == Input.Keys.P)
        {
            note=KeyMapping("P");
            notes.handleNote(note);
            notes.addTempNote(note);
        }

        if(keycode == Input.Keys.RIGHT_BRACKET)
        {
            note=KeyMapping("]");
            notes.handleNote(note);
            notes.addTempNote(note);
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        String note;
        if(keycode == Input.Keys.A)
        {
            note=KeyMapping("A");
            notes.handleNote(note);
            notes.removeTempNote(note);

        }
        if(keycode == Input.Keys.S)
        {
            note=KeyMapping("S");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.D)
        {
            note=KeyMapping("D");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.F)
        {
            note=KeyMapping("F");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.G)
        {
            note=KeyMapping("G");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.H)
        {
            note=KeyMapping("H");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.J)
        {
            note=KeyMapping("J");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.K)
        {
            note=KeyMapping("K");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.L)
        {
            note=KeyMapping("L");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.SEMICOLON)
        {
            note=KeyMapping(";");
            notes.handleNote(note);
            notes.removeTempNote(note);

        }
        if(keycode == Input.Keys.APOSTROPHE)
        {
            note=KeyMapping("'");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }

        if(keycode == Input.Keys.W)
        {
            note=KeyMapping("W");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.E)
        {
            note=KeyMapping("E");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.F)
        {
            note=KeyMapping("F");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.T)
        {
            note=KeyMapping("T");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.W)
        {
            note=KeyMapping("W");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.G)
        {
            note=KeyMapping("G");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.Y)
        {
            note=KeyMapping("Y");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.U)
        {
            note=KeyMapping("U");
            notes.handleNote(note);
            notes.removeTempNote(note);

        }
        if(keycode == Input.Keys.W)
        {
            note=KeyMapping("W");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }

        if(keycode == Input.Keys.O)
        {
            note=KeyMapping("O");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }
        if(keycode == Input.Keys.P)
        {
            note=KeyMapping("P");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }

        if(keycode == Input.Keys.RIGHT_BRACKET)
        {
            note=KeyMapping("]");
            notes.handleNote(note);
            notes.removeTempNote(note);
        }




        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        isPlaying = !isPlaying;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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


    public static Theme getTheme()
    {
        return theme;
    }

    public String KeyMapping(String inputKey)
    {
        if(inputKey.equals("A"))
            return "C4";
        else if(inputKey.equals("W"))
            return "C#4";

        else if(inputKey.equals("S"))
            return "D4";
        else if(inputKey.equals("E"))
            return "D#4";
        else if(inputKey.equals("D"))
            return "E4";
        else if(inputKey.equals("F"))
            return "F4";
        else if(inputKey.equals("T"))
            return "F#4";
        else if(inputKey.equals("G"))
            return "G4";
        else if(inputKey.equals("Y"))
            return "G#4";
        else if(inputKey.equals("H"))
            return "A4";
        else if(inputKey.equals("U"))
            return "A#4";
        else if(inputKey.equals("J"))
            return "B4";

        else if(inputKey.equals("K"))
            return "C5";
        else if(inputKey.equals("O"))
            return "C#5";
        else if(inputKey.equals("L"))
            return "D5";
        else if(inputKey.equals("P"))
            return "D#5";
        else if(inputKey.equals(";"))
            return "E5";
        else if(inputKey.equals("'"))
            return "F5";
        else if(inputKey.equals("]"))
            return "F#5";

        return  null;









    }
}
