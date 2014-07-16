package com.recreationallightandmagic.zymphonic.processing;

import processing.core.PApplet;
import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

/**
 * Stuff for debugging (showing on a monitor) what the kinect is seeing.
 */
public class KinectDebugger {

	private static PVector com = new PVector();
	private static PVector com2d = new PVector();

	public static void debug(PApplet applet, SimpleOpenNI kinect) {
		// draw depthImageMap
		// applet.image(kinect.depthImage(), 0, 0);
		applet.image(kinect.userImage(), 0, 0);

		// draw the skeleton if it's available
		int[] userList = kinect.getUsers();
		for (int i = 0; i < userList.length; i++) {
			if (kinect.isTrackingSkeleton(userList[i])) {
				applet.stroke(Constants.basicColors[(userList[i] - 1)
						% Constants.basicColors.length]);
				drawSkeleton(userList[i], kinect);
			}

			// draw the center of mass
			if (kinect.getCoM(userList[i], com)) {
//				System.out.println(i + " " + com.z);
				kinect.convertRealWorldToProjective(com, com2d);
				applet.stroke(100, 255, 0);
				applet.strokeWeight(1);
				applet.beginShape(PApplet.LINES);
				applet.vertex(com2d.x, com2d.y - 5);
				applet.vertex(com2d.x, com2d.y + 5);

				applet.vertex(com2d.x - 5, com2d.y);
				applet.vertex(com2d.x + 5, com2d.y);
				applet.endShape();

				applet.fill(0, 255, 100);
				applet.text(Integer.toString(userList[i]), com2d.x, com2d.y);
			}
		}
	}

	// draw the skeleton with the selected joints
	private static void drawSkeleton(int userId, SimpleOpenNI kinect) {
		// to get the 3d joint data
		/*
		 * PVector jointPos = new PVector();
		 * context.getJointPositionSkeleton(userId
		 * ,SimpleOpenNI.SKEL_NECK,jointPos); println(jointPos);
		 */

		kinect.drawLimb(userId, SimpleOpenNI.SKEL_HEAD, SimpleOpenNI.SKEL_NECK);

		kinect.drawLimb(userId, SimpleOpenNI.SKEL_NECK,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_ELBOW,
				SimpleOpenNI.SKEL_LEFT_HAND);

		kinect.drawLimb(userId, SimpleOpenNI.SKEL_NECK,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW,
				SimpleOpenNI.SKEL_RIGHT_HAND);

		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER,
				SimpleOpenNI.SKEL_TORSO);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
				SimpleOpenNI.SKEL_TORSO);

		kinect.drawLimb(userId, SimpleOpenNI.SKEL_TORSO,
				SimpleOpenNI.SKEL_LEFT_HIP);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_HIP,
				SimpleOpenNI.SKEL_LEFT_KNEE);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_KNEE,
				SimpleOpenNI.SKEL_LEFT_FOOT);

		kinect.drawLimb(userId, SimpleOpenNI.SKEL_TORSO,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_HIP,
				SimpleOpenNI.SKEL_RIGHT_KNEE);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_KNEE,
				SimpleOpenNI.SKEL_RIGHT_FOOT);
	}

}