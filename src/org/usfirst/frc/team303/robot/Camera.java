package org.usfirst.frc.team303.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Camera {
	
	int session;
	Image frame;
	AxisCamera camera;
    NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
	
	public Camera() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_HSL, 0); //was rgb imagetype

        // open the camera at the IP address assigned. This is the IP address that the camera
        // can be accessed through the web interface.
        camera = new AxisCamera("10.3.3.26");
	}
	
    public void operatorControl() {

        /**
         * grab an image from the camera, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        camera.getImage(frame);
        NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
        CameraServer.getInstance().setImage(frame);
       
    }
	
}
