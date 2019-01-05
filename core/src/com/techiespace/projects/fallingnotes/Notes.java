package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.techiespace.projects.fallingnotes.pianoHelpers.MidiParser;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

import java.util.Arrays;

public class Notes {


    Array<Note> tempList;

    //Final List
    Array<Note> noteArray;
    Note[] noteArrayPool;
    int poolIndex;
    float initialTime;
    float initialSystemTime;
    Sound sound;

    public Notes(){
        init();
        poolIndex = 0;
    }

    public void init(){
        noteArray = new Array<Note>(88);
        tempList = new Array<Note>(88);

//        MidiParser midiParser = new MidiParser();
        //Girls_Like_You_Maroon_5, broken_dreams
//        noteArrayPool = midiParser.parse("midi/perfect.mid");
//        Arrays.sort(noteArrayPool);
        initialTime = 0;
    }

    public void update(float delta){
        initialTime += delta * Constants.SPEED;
//        Gdx.app.log("Init time: ", ""+initialTime+" Delta time: "+ noteArrayPool[poolIndex].startTime);
//        for (int i = poolIndex; i < noteArrayPool.length ; i++) {
//        while (poolIndex < noteArrayPool.length && noteArrayPool[poolIndex].startTime <= initialTime * 1000) {
//                noteArray.add(noteArrayPool[poolIndex]);
//                poolIndex++;
//        }
////        }
//
//        for (Note note : noteArray) {
//            Sound sound;
//            long soundId = 0;
//            note.update(delta);
//            PianoKey key = Piano.findKey(note.noteName);
//            sound = note.sound;
//
//            if (note.position.y < (Constants.WHITE_PIANO_KEY_HEIGHT + Constants.OFFSET)) {
//
//
//                // Handling the corner case  of a really long note
//                //updating the y position and simultaneously reducing the height
//                if(note.position.y<Constants.OFFSET) {
//                    note.position.y = note.position.y + Constants.WHITE_PIANO_KEY_HEIGHT;
//                   note.noteLength = note.noteLength - Constants.WHITE_PIANO_KEY_HEIGHT;
//                }
//
//                if (!note.soundOnce) {
//                    soundId = sound.play(note.pressVelocity / 100f);   //https://stackoverflow.com/questions/31990997/libgdx-not-playing-sound-android  (takes a while to load the sound)
////                    Gdx.app.log("Volume: ", "" + note.pressVelocity);
//                    note.soundOnce = true;
//                }
//
//
////                Gdx.app.log("TestOut", "" + noteArray.size);
////                Gdx.app.log("Condition",(Constants.WHITE_PIANO_KEY_HEIGHT + (float)Constants.OFFSET) + " "+note.position.y + " " + note.noteLength);
//                if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT + (float) Constants.OFFSET) {
//                    sound.stop(soundId);
//                    noteArray.removeValue(note, false);
////                    Gdx.app.log("Test",""+noteArray.size);
//                    }
//            }
//
//
//
//            //for handling the key
//            if (note.position.y < Constants.WHITE_PIANO_KEY_HEIGHT + Constants.OFFSET && !key.getIsPressed())
//            {
//                key.updateTextureDown();
//            }
//
//            if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT + Constants.OFFSET && key.getIsPressed())
//            {
//                key.updateTextureUp();
//            }
//        }
    }

    public void render(RoundRectShapeRenderer renderer) {
        // TODO: Render each note
        for (Note note : noteArray) {
            note.render(renderer);
    }
    }

    void addNote(String noteName,int startTime,int endTime)
    {
        Gdx.app.log("Note added",noteName+" "+startTime+" "+endTime);
        noteArray.add(new Note(noteName,startTime,endTime));
    }


    void addTempNote(String noteName)
    {
        tempList.add(new Note(noteName,(int)initialTime,0));
    }

    void removeTempNote(String noteName)
    {
        Note note = null;
        for(int i=0;i<tempList.size;i++)
        {
            if(tempList.get(i).noteName.equals(noteName))
            {
                note = tempList.get(i);
                break;
            }
        }
        if(note!=null) {
            tempList.removeValue(note, false);

            addNote(note.noteName, note.startTime, (int) initialTime);
        }
    }

    public void handleNote(String Note)
    {
      PianoKey key;
       // Gdx.app.log("handle note ",Note);
      if(Note!=null)
      {
          key = Piano.findKey(Note);
         // Gdx.app.log("handle note find key ",key.getName());
          if(key.getIsPressed())
              key.updateTextureUp();
          else
              key.updateTextureDown();
      }
    }

    public void printFinalList()
    {

        for(Note note : noteArray)
        Gdx.app.log("Notes In List ",note.noteName+" "+note.startTime+" "+note.endTime);
    }

}
