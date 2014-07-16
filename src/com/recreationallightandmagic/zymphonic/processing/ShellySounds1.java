package com.recreationallightandmagic.zymphonic.processing;

import processing.core.PApplet;
import ddf.minim.Minim;

public class ShellySounds1 extends RegionSoundMap {

	public ShellySounds1(int width, int height, int depth) {
		super(width, height, depth);
	}

	public void setup(PApplet applet) {

		Minim minim = new Minim(applet);

		loadColumn(minim, 0, 0, "Piano1_A1.mp3", "Piano2_C2.mp3",
				"Piano3_E2.mp3", "Piano4_F2.mp3", "Piano5_G2.mp3",
				"Piano6_A2.mp3", "Piano1_A1.mp3", "Piano2_C2.mp3");

		loadColumn(minim, 1, 0, "Bass1_A1.mp3", "Bass2_C2.mp3", "Bass3_E2.mp3",
				"Bass4_F2.mp3", "Bass5_G2.mp3", "Bass6_A2.mp3", "Bass1_A1.mp3",
				"Bass2_C2.mp3");

		loadColumn(minim, 0, 1, "thunder1.mp3", "thunder2.mp3", "thunder3.mp3",
				"thunder4.mp3", "thunder5.mp3", "thunder6.mp3", "thunder7.mp3",
				"thunder8.mp3");

		loadColumn(minim, 1, 1, "Fatigue.mp3", "Fear.mp3", "Hostility.mp3",
				"eerie.mp3", "Serenity.mp3", "Positivity.mp3", "Joviality.mp3",
				"End_C.mp3");

		System.out.println("Done setting up sound");
	}

	private void loadColumn(Minim minim, int x, int y, String... filenames) {
		if (filenames.length != samples[0][0].length) {
			throw new RuntimeException(
					"Must call loadColumn with the correct number of files for the depth of the soundMap");
		}
		for (int z = 0; z < filenames.length; z++) {
			// For the love of god, leave the buffer in.
			samples[x][y][z] = minim.loadSample(filenames[z], 9048);
		}
	}

}