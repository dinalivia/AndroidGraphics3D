package com.bennyplo.android_mooc_graphics_3d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


public class MyView extends View {
    private Paint redPaint; //paint object for drawing the lines
    private Coordinate[]cube_head;//the vertices of a 3D cube
    private Coordinate[]cube_body;//the vertices of a 3D cube
    private Coordinate[]cube_leftleg;//the vertices of a 3D cube
    private Coordinate[]cube_rightleg;//the vertices of a 3D cube
    private Coordinate[]cube_leftarm;//the vertices of a 3D cube
    private Coordinate[]cube_rightarm;//the vertices of a 3D cube

    private Coordinate[]draw_head_vertices;//the vertices for drawing a 3D cube [HEAD]
    private Coordinate[]draw_body_vertices;//the vertices for drawing a 3D cube [BODY]
    private Coordinate[]draw_leftleg_vertices;//the vertices for drawing a 3D cube [LEFT LEG]
    private Coordinate[]draw_rightleg_vertices;//the vertices for drawing a 3D cube [RIGHT LEG]
    private Coordinate[]draw_leftarm_vertices;//the vertices for drawing a 3D cube [LEFT LEG]
    private Coordinate[]draw_rightarm_vertices;//the vertices for drawing a 3D cube [RIGHT LEG]

    public MyView(Context context) {
        super(context, null);
        final MyView thisview=this;
        //create the paint object
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//Stroke
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(5);

        // ----------------- ROBOT HEAD --------------------- //

        cube_head = new Coordinate[8];
        
        cube_head[0] = new Coordinate(-1, -1, -1, 1);
        cube_head[1] = new Coordinate(-1, -1, 1, 1);
        cube_head[2] = new Coordinate(-1, 1, -1, 1);
        cube_head[3] = new Coordinate(-1, 1, 1, 1);
        cube_head[4] = new Coordinate(1, -1, -1, 1);
        cube_head[5] = new Coordinate(1, -1, 1, 1);
        cube_head[6] = new Coordinate(1, 1, -1, 1);
        cube_head[7] = new Coordinate(1, 1, 1, 1);

        draw_head_vertices=translate(cube_head,13,13,13);
        draw_head_vertices=scale(draw_head_vertices,50,50,50);

        // ----------------- ROBOT BODY --------------------- //
        //create a 3D cube
        cube_body = new Coordinate[8];

        cube_body[0] = new Coordinate(-1, -1, -1, 1);
        cube_body[1] = new Coordinate(-1, -1, 1, 1);
        cube_body[2] = new Coordinate(-1, 2, -1, 1);
        cube_body[3] = new Coordinate(-1, 2, 1, 1);
        cube_body[4] = new Coordinate(1, -1, -1, 1);
        cube_body[5] = new Coordinate(1, -1, 1, 1);
        cube_body[6] = new Coordinate(1, 2, -1, 1);
        cube_body[7] = new Coordinate(1, 2, 1, 1);

        draw_body_vertices=translate(cube_body,0,2,0);
        draw_body_vertices=translate(draw_body_vertices,13,13,13);
        draw_body_vertices=scale(draw_body_vertices,50,50,50);

        // ----------------- ROBOT LEFT LEG --------------------- //
        //create a 3D cube
        cube_leftleg = new Coordinate[8];

        cube_leftleg[0] = new Coordinate(-0.5, -1, -1, 1);
        cube_leftleg[1] = new Coordinate(-0.5, -1, 1, 1);
        cube_leftleg[2] = new Coordinate(-0.5, 2, -1, 1);
        cube_leftleg[3] = new Coordinate(-0.5, 2, 1, 1);
        cube_leftleg[4] = new Coordinate(0.5, -1, -1, 1);
        cube_leftleg[5] = new Coordinate(0.5, -1, 1, 1);
        cube_leftleg[6] = new Coordinate(0.5, 2, -1, 1);
        cube_leftleg[7] = new Coordinate(0.5, 2, 1, 1);

        draw_leftleg_vertices=translate(cube_leftleg,-0.5,5,0);
        draw_leftleg_vertices=translate(draw_leftleg_vertices,13,13,13);
        draw_leftleg_vertices=scale(draw_leftleg_vertices,50,50,50);

        // ----------------- ROBOT RIGHT LEG --------------------- //
        //create a 3D cube
        cube_rightleg = new Coordinate[8];

        cube_rightleg[0] = new Coordinate(-0.5, -1, -1, 1);
        cube_rightleg[1] = new Coordinate(-0.5, -1, 1, 1);
        cube_rightleg[2] = new Coordinate(-0.5, 2, -1, 1);
        cube_rightleg[3] = new Coordinate(-0.5, 2, 1, 1);
        cube_rightleg[4] = new Coordinate(0.5, -1, -1, 1);
        cube_rightleg[5] = new Coordinate(0.5, -1, 1, 1);
        cube_rightleg[6] = new Coordinate(0.5, 2, -1, 1);
        cube_rightleg[7] = new Coordinate(0.5, 2, 1, 1);

        draw_rightleg_vertices=translate(cube_rightleg,0.5,5,0);
        draw_rightleg_vertices=translate(draw_rightleg_vertices,13,13,13);
        draw_rightleg_vertices=scale(draw_rightleg_vertices,50,50,50);

        // ----------------- ROBOT LEFT ARM --------------------- //
        //create a 3D cube
        cube_leftarm = new Coordinate[8];

        cube_leftarm[0] = new Coordinate(-0.5, -1, -1, 1);
        cube_leftarm[1] = new Coordinate(-0.5, -1, 1, 1);
        cube_leftarm[2] = new Coordinate(-0.5, 2, -1, 1);
        cube_leftarm[3] = new Coordinate(-0.5, 2, 1, 1);
        cube_leftarm[4] = new Coordinate(0.5, -1, -1, 1);
        cube_leftarm[5] = new Coordinate(0.5, -1, 1, 1);
        cube_leftarm[6] = new Coordinate(0.5, 2, -1, 1);
        cube_leftarm[7] = new Coordinate(0.5, 2, 1, 1);

        draw_leftarm_vertices=translate(cube_leftarm,-1.5,2,0);
        draw_leftarm_vertices=translate(draw_leftarm_vertices,13,13,13);
        draw_leftarm_vertices=scale(draw_leftarm_vertices,50,50,50);

        // ----------------- ROBOT RIGHT ARM --------------------- //
        //create a 3D cube
        cube_rightarm = new Coordinate[8];

        cube_rightarm[0] = new Coordinate(-0.5, -1, -1, 1);
        cube_rightarm[1] = new Coordinate(-0.5, -1, 1, 1);
        cube_rightarm[2] = new Coordinate(-0.5, 2, -1, 1);
        cube_rightarm[3] = new Coordinate(-0.5, 2, 1, 1);
        cube_rightarm[4] = new Coordinate(0.5, -1, -1, 1);
        cube_rightarm[5] = new Coordinate(0.5, -1, 1, 1);
        cube_rightarm[6] = new Coordinate(0.5, 2, -1, 1);
        cube_rightarm[7] = new Coordinate(0.5, 2, 1, 1);

        draw_rightarm_vertices=translate(cube_rightarm,1.5,2,0);
        draw_rightarm_vertices=translate(draw_rightarm_vertices,13,13,13);
        draw_rightarm_vertices=scale(draw_rightarm_vertices,50,50,50);


        
        // ---- 3D Affine Transformation - Assignment part 1 ---- //

        Coordinate centre = FindCentre(cube_head);

//        draw_head_vertices = translate(draw_head_vertices,-centre.x,-centre.y,-centre.z);

//        draw_head_vertices = quartenion(draw_head_vertices, 1, 0.5, -0.5, 0.5);

        double y = -0.5;

//        draw_head_vertices = rotate_z(draw_head_vertices,  25);
//        draw_head_vertices = rotate_y(draw_head_vertices, 90);
//        draw_head_vertices = translate(draw_head_vertices,centre.x,centre.y,centre.z);

//        draw_head_vertices=translate(draw_head_vertices,30,30,0);
//        draw_body_vertices=translate(draw_body_vertices,30,30,0);

//        draw_head_vertices=translate(draw_head_vertices,50,300,0);

        thisview.invalidate();//update the view

        // ---- Add a timer to enable animation --- /
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            float position_x = 0f;

            double position_y = -0.5d;

            boolean dir = true;

            double rotation_z = 0, rotation_x = 0, rotation_y = 0;
            Coordinate centre_head = FindCentre(draw_head_vertices);
            Coordinate centre_body = FindCentre(draw_body_vertices);

//            Coordinate[] vertice;//the vertices of a 3D cube
//            vertice = new Coordinate[8];
//            vertice[0] = new Coordinate(-0.5, -1, -1, 1);
//            vertice[1] = new Coordinate(-0.5, -1, 1, 1);

            @Override
            public void run() {

                // ---LINEAR ANIMATION - LECTURE EXAMPLE ------- /

//                if (position_x + 80 >= getWidth() && dir == true){
//                    dir = false;
//                } if (dir == false && position_x <= 0) {
//                    dir = true;
//                }
//                if (dir) {
//                    draw_head_vertices = translate(draw_head_vertices, 1f,0,0);
//                    draw_head_vertices = rotate_x(draw_head_vertices, -5);
//                    position_x += 1f;
//                } else {
//                    draw_head_vertices = translate(draw_head_vertices, -1f,0,0);
//                    //Coordinate centre = FindCentre(draw_head_vertices);
//                    //draw_head_vertices = translate(draw_head_vertices,-centre.x,-centre.y,-centre.z);
//                    draw_head_vertices = rotate_x(draw_head_vertices, 5);
//                    //draw_head_vertices = translate(draw_head_vertices,centre.x,centre.y,centre.z);
//
//                    position_x -= 1f;
//                }

                // --------- ROTATION ANIMATION --------------- /

//                if (rotation_x >= 360) {
//                     rotation_x = rotation_x - 360;
//                }
//                if (rotation_y >= 360) {
//                    rotation_y = rotation_y - 360;
//                }
//
//                // HEAD ROTATION
//
//                draw_head_vertices = translate(draw_head_vertices,-centre_head.x,-centre_head.y,-centre_head.z);
//                draw_head_vertices = rotate_y(draw_head_vertices, rotation_y);
//                draw_head_vertices = translate(draw_head_vertices,centre_head.x,centre_head.y,centre_head.z);
//
////               draw_head_vertices = translate(draw_head_vertices,30,30,0);
////                draw_head_vertices = translate(draw_head_vertices,0,30,30);
//
//                // BODY ROTATION
//
//                draw_body_vertices = translate(draw_body_vertices,-centre_body.x,-centre_body.y,-centre_body.z);
//                draw_body_vertices = rotate_y(draw_body_vertices, rotation_y);
//                draw_body_vertices = translate(draw_body_vertices,centre_body.x,centre_body.y,centre_body.z);
//
////               draw_head_vertices = translate(draw_head_vertices,30,30,0);
////                draw_body_vertices = translate(draw_body_vertices,0,30,30);
//
//                rotation_y = 180*(Math.atan2(centre_body.z, centre_body.x))/Math.PI + 1;

                // --------- ROTATION ANIMATION QUATERNION--------------- /

                if (position_y >= 0 && dir == true){
                    dir = false;
                } if (dir == false && position_y <= -1) {
                    dir = true;
                }

                if (rotation_y >= 360) {
//                    rotation_y = rotation_y - 360;
                    rotation_y = 0;
                }

                if (dir) {
//                    draw_head_vertices = translate(draw_head_vertices, 1f,0,0);
//                    draw_head_vertices = quartenion(draw_head_vertices, 1, 0, 0.05, 0.05);
//                    draw_body_vertices = quartenion(draw_body_vertices, 1, 0, 0.05, 0.05);

                    draw_head_vertices = quartenion(draw_head_vertices, 1, 0.01, 0, 0.01);
                    draw_body_vertices = quartenion(draw_body_vertices, 1, 0.01,0, 0.01);
                    draw_leftarm_vertices = quartenion(draw_leftarm_vertices, 1, 0.01,0, 0.01);
                    draw_rightarm_vertices = quartenion(draw_rightarm_vertices, 1, 0.01,0, 0.01);
                    draw_leftleg_vertices = quartenion(draw_leftleg_vertices, 1, 0.01,0, 0.01);
                    draw_rightleg_vertices = quartenion(draw_rightleg_vertices, 1, 0.01,0, 0.01);

//                    draw_head_vertices = quartenion(draw_head_vertices, 1, 0, 0.1, 0);
//                    draw_body_vertices = quartenion(draw_body_vertices, 1, 0,0.1, 0);
//                    draw_leftarm_vertices = quartenion(draw_leftarm_vertices, 1, 0,0.1, 0);
//                    draw_rightarm_vertices = quartenion(draw_rightarm_vertices, 1, 0,0.1, 0);
//                    draw_leftleg_vertices = quartenion(draw_leftleg_vertices, 1, 0,0.1, 0);
//                    draw_rightleg_vertices = quartenion(draw_rightleg_vertices, 1, 0,0.1, 0);

//                    draw_head_vertices = rotate_x(draw_head_vertices, -5);

                    Coordinate[] leftarm = new Coordinate[2];
                    leftarm[0] = draw_leftarm_vertices[0]; leftarm[1] = draw_leftarm_vertices[1];
                    Coordinate centre_leftarm = FindCentre(leftarm);
                    draw_leftarm_vertices = translate(draw_leftarm_vertices,-centre_leftarm.x,-centre_leftarm.y,-centre_leftarm.z);
                    draw_leftarm_vertices = rotate_z(draw_leftarm_vertices, -5);
                    draw_leftarm_vertices = translate(draw_leftarm_vertices,centre_leftarm.x,centre_leftarm.y,centre_leftarm.z);

                    Coordinate[] rightarm = new Coordinate[2];
                    rightarm[0] = draw_rightarm_vertices[0]; rightarm[1] = draw_rightarm_vertices[1];
                    Coordinate centre_rightarm = FindCentre(rightarm);
                    draw_rightarm_vertices = translate(draw_rightarm_vertices,-centre_rightarm.x,-centre_rightarm.y,-centre_rightarm.z);
                    draw_rightarm_vertices = rotate_z(draw_rightarm_vertices, -5);
                    draw_rightarm_vertices = translate(draw_rightarm_vertices,centre_rightarm.x,centre_rightarm.y,centre_rightarm.z);

                    position_y += 0.05d;

                    rotation_y = 180*(Math.atan2(centre_leftarm.z, centre_leftarm.x))/Math.PI - 5;

                } else {
//                    draw_head_vertices = translate(draw_head_vertices, -1f,0,0);

                    draw_head_vertices = quartenion(draw_head_vertices, 1, -0.01, 0, -0.01);
                    draw_body_vertices = quartenion(draw_body_vertices, 1, -0.01,0, -0.01);
                    draw_leftarm_vertices = quartenion(draw_leftarm_vertices, 1, -0.01,0, -0.01);
                    draw_rightarm_vertices = quartenion(draw_rightarm_vertices, 1, -0.01,0, -0.01);
                    draw_leftleg_vertices = quartenion(draw_leftleg_vertices, 1, -0.01,0, -0.01);
                    draw_rightleg_vertices = quartenion(draw_rightleg_vertices, 1, -0.01,0, -0.01);

//                    draw_head_vertices = quartenion(draw_head_vertices, 1, 0, -0.1, 0);
//                    draw_body_vertices = quartenion(draw_body_vertices, 1, 0,-0.1, 0);
//                    draw_leftarm_vertices = quartenion(draw_leftarm_vertices, 1, 0,-0.1, 0);
//                    draw_rightarm_vertices = quartenion(draw_rightarm_vertices, 1, 0,-0.1, 0);
//                    draw_leftleg_vertices = quartenion(draw_leftleg_vertices, 1, 0,-0.1, 0);
//                    draw_rightleg_vertices = quartenion(draw_rightleg_vertices, 1, 0,-0.1, 0);

                    Coordinate[] leftarm = new Coordinate[2];
                    leftarm[0] = draw_leftarm_vertices[0]; leftarm[1] = draw_leftarm_vertices[1];
                    Coordinate centre_leftarm = FindCentre(leftarm);
                    draw_leftarm_vertices = translate(draw_leftarm_vertices,-centre_leftarm.x,-centre_leftarm.y,-centre_leftarm.z);
                    draw_leftarm_vertices = rotate_z(draw_leftarm_vertices, 5);
                    draw_leftarm_vertices = translate(draw_leftarm_vertices,centre_leftarm.x,centre_leftarm.y,centre_leftarm.z);

                    Coordinate[] rightarm = new Coordinate[2];
                    rightarm[0] = draw_rightarm_vertices[0]; rightarm[1] = draw_rightarm_vertices[1];
                    Coordinate centre_rightarm = FindCentre(rightarm);
                    draw_rightarm_vertices = translate(draw_rightarm_vertices,-centre_rightarm.x,-centre_rightarm.y,-centre_rightarm.z);
                    draw_rightarm_vertices = rotate_z(draw_rightarm_vertices, 5);
                    draw_rightarm_vertices = translate(draw_rightarm_vertices,centre_rightarm.x,centre_rightarm.y,centre_rightarm.z);

                    position_y -= 0.05d;

                    rotation_y = 180*(Math.atan2(centre_leftarm.z, centre_leftarm.x))/Math.PI + 5;

                };

                thisview.invalidate(); // update the view
            }
        };
        timer.scheduleAtFixedRate(task, 10, 90);

    }


    private  void DrawLinePairs(Canvas canvas, Coordinate[] vertices, int start, int end, Paint paint)
    {//draw a line connecting 2 points
        //canvas - canvas of the view
        //points - array of points
        //start - index of the starting point
        //end - index of the ending point
        //paint - the paint of the line
        canvas.drawLine((int)vertices[start].x,(int)vertices[start].y,(int)vertices[end].x,(int)vertices[end].y,paint);
    }

    private void DrawCube(Canvas canvas, Coordinate[] cube)
    {//draw a cube on the screen
        DrawLinePairs(canvas, cube, 0, 1, redPaint);
        DrawLinePairs(canvas, cube, 1, 3, redPaint);
        DrawLinePairs(canvas, cube, 3, 2, redPaint);
        DrawLinePairs(canvas, cube, 2, 0, redPaint);
        DrawLinePairs(canvas, cube, 4, 5, redPaint);
        DrawLinePairs(canvas, cube, 5, 7, redPaint);
        DrawLinePairs(canvas, cube, 7, 6, redPaint);
        DrawLinePairs(canvas, cube, 6, 4, redPaint);
        DrawLinePairs(canvas, cube, 0, 4, redPaint);
        DrawLinePairs(canvas, cube, 1, 5, redPaint);
        DrawLinePairs(canvas, cube, 2, 6, redPaint);
        DrawLinePairs(canvas, cube, 3, 7, redPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw objects on the screen
        super.onDraw(canvas);
        DrawCube(canvas, draw_head_vertices);//draw a cube onto the screen
        DrawCube(canvas, draw_body_vertices);//draw a cube onto the screen
        DrawCube(canvas, draw_leftleg_vertices);
        DrawCube(canvas, draw_rightleg_vertices);
        DrawCube(canvas, draw_leftarm_vertices);
        DrawCube(canvas, draw_rightarm_vertices);

    }
    //*********************************
    //matrix and transformation functions
    public double []GetIdentityMatrix() {//return an 4x4 identity matrix
        double []matrix=new double[16];
        matrix[0]=1;matrix[1]=0;matrix[2]=0;matrix[3]=0;
        matrix[4]=0;matrix[5]=1;matrix[6]=0;matrix[7]=0;
        matrix[8]=0;matrix[9]=0;matrix[10]=1;matrix[11]=0;
        matrix[12]=0;matrix[13]=0;matrix[14]=0;matrix[15]=1;
        return matrix;
    }
    public Coordinate Transformation(Coordinate vertex,double []matrix) {//affine transformation with homogeneous coordinates
        //i.e. a vector (vertex) multiply with the transformation matrix
        // vertex - vector in 3D
        // matrix - transformation matrix
        Coordinate result=new Coordinate();
        result.x = matrix[0]*vertex.x + matrix[1]*vertex.y + matrix[2]*vertex.z + matrix[3];
        result.y = matrix[4]*vertex.x + matrix[5]*vertex.y + matrix[6]*vertex.z + matrix[7];
        result.z = matrix[8]*vertex.x + matrix[9]*vertex.y + matrix[10]*vertex.z + matrix[11];
        result.w = matrix[12]*vertex.x + matrix[13]*vertex.y + matrix[14]*vertex.z + matrix[15];
        return result;
    }
    public Coordinate[]Transformation(Coordinate []vertices,double []matrix) {   //Affine transform a 3D object with vertices
        // vertices - vertices of the 3D object.
        // matrix - transformation matrix
        Coordinate[] result=new Coordinate[vertices.length];
        for (int i=0;i<vertices.length;i++)
        {
           result[i]=Transformation(vertices[i],matrix);
           result[i].Normalise();
        }
        return result;
    }
    //***********************************************************
    //Affine transformation
    public Coordinate[] translate (Coordinate[] vertices, double tx, double ty, double tz) {
        double[] matrix = GetIdentityMatrix();
        matrix[3] = tx;
        matrix[7] = ty;
        matrix[11] = tz;
        return Transformation(vertices,matrix);
    }
    private Coordinate[] scale (Coordinate[] vertices, double sx, double sy, double sz) {
        double[] matrix = GetIdentityMatrix();
        matrix[0] = sx;
        matrix[5] = sy;
        matrix[10] = sz;
        return Transformation(vertices,matrix);
    }

    private Coordinate FindCentre(Coordinate[] coordinates) {
        Coordinate centre = new Coordinate (0, 0, 0,1);
        for (int i = 0; i < coordinates.length; i ++) {
            centre.x += coordinates[i].x;
            centre.y += coordinates[i].y;
            centre.z += coordinates[i].z;
        }
        centre.x = centre.x / (coordinates.length);
        centre.y = centre.y / (coordinates.length);
        return centre;
    }

    private Coordinate[] rotate_x (Coordinate[] vertices, double theta) {
        theta = theta*Math.PI/180;
        double[] matrix = GetIdentityMatrix();
        matrix[5] = Math.cos(theta);
        matrix[6] = -Math.sin(theta);
        matrix[9] = Math.sin(theta);
        matrix[10] = Math.cos(theta);
        return Transformation(vertices, matrix);
    }
    private Coordinate[] rotate_y (Coordinate[] vertices, double theta) {
        theta = theta*Math.PI/180;
        double[] matrix = GetIdentityMatrix();
        matrix[0] = Math.cos(theta);
        matrix[2] = Math.sin(theta);
        matrix[8] = -Math.sin(theta);
        matrix[10] = Math.cos(theta);
        return Transformation(vertices, matrix);
    }
    private Coordinate[] rotate_z (Coordinate[] vertices, double theta) {
        theta = theta * Math.PI / 180;
        double[] matrix = GetIdentityMatrix();
        matrix[0] = Math.cos(theta);
        matrix[1] = -Math.sin(theta);
        matrix[4] = Math.sin(theta);
        matrix[5] = Math.cos(theta);
        return Transformation(vertices, matrix);
    }

    private Coordinate[] quartenion (Coordinate[] vertices, double w, double x, double y, double z) {
        double[] matrix = new double[16]; //= GetIdentityMatrix();
        matrix[0] = ((w*w) + (x*x) - (y*y) - (z*z));
        matrix[1] = (2*(x*y) - 2*(w*z));
        matrix[2] = (2*(x*z) + 2*(w*z));
        matrix[3] = 0;

        matrix[4] = (2*(x*y) + 2*(w*z));
        matrix[5] = ((w*w) + (y*y) - (x*x) - (z*z));
        matrix[6] = (2*(y*z) - 2*(w*x));
        matrix[7] = 0;

        matrix[8] = (2*(x*z) - 2*(w*y));
        matrix[9] = (2*(y*z) + 2*(w*x));
        matrix[10] = ((w*w) + (z*z) - (x*x) - (y*y));
        matrix[11] = 0;

        matrix[12] = 0;
        matrix[13] = 0;
        matrix[14] = 0;
        matrix[15] = 1;

        return Transformation(vertices, matrix);
    }

}