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
    private Coordinate[]cube_vertices;//the vertices of a 3D cube
    private Coordinate[]draw_cube_vertices;//the vertices for drawing a 3D cube
    public MyView(Context context) {
        super(context, null);
        final MyView thisview=this;
        //create the paint object
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//Stroke
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(5);
        //create a 3D cube
        cube_vertices = new Coordinate[8];
        cube_vertices[0] = new Coordinate(-1, -1, -1, 1);
        cube_vertices[1] = new Coordinate(-1, -1, 1, 1);
        cube_vertices[2] = new Coordinate(-1, 1, -1, 1);
        cube_vertices[3] = new Coordinate(-1, 1, 1, 1);
        cube_vertices[4] = new Coordinate(1, -1, -1, 1);
        cube_vertices[5] = new Coordinate(1, -1, 1, 1);
        cube_vertices[6] = new Coordinate(1, 1, -1, 1);
        cube_vertices[7] = new Coordinate(1, 1, 1, 1);
        draw_cube_vertices=translate(cube_vertices,3,3,3);
        draw_cube_vertices=scale(draw_cube_vertices,40,40,40);
        //draw_cube_vertices=rotate_y(draw_cube_vertices,45); // rotate in y
        //draw_cube_vertices=rotate_x(draw_cube_vertices,45); // rotate in x

        // ---- 3D Affine Transformation - Assignment part 1 ---- //
        Coordinate centre = FindCentre(cube_vertices);
        //draw_cube_vertices=translate(cube_vertices,-centre.x,-centre.y,-centre.z);
        //draw_cube_vertices=rotate_z(draw_cube_vertices,80); // rotate in z
        //draw_cube_vertices=rotate_y(draw_cube_vertices,30); // rotate in y
        //draw_cube_vertices=translate(cube_vertices,centre.x,centre.y,centre.z);
        //draw_cube_vertices=translate(cube_vertices,5,-5,2);

        //draw_cube_vertices = rotate_x(draw_cube_vertices, 90);
        //draw_cube_vertices = rotate_y(draw_cube_vertices, 25);

        thisview.invalidate();//update the view

        // ---- Add a timer to enable animation --- /
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            float position_x = 0f;
            boolean dir = true;

            float rotation_z = 0;

             @Override
            public void run() {
//                if (position_x + 80 >= getWidth() && dir == true){
//                    dir = false;
//                } if (dir == false && position_x <= 0) {
//                    dir = true;
//                }
//                if (dir) {
//                    draw_cube_vertices = translate(draw_cube_vertices, 1f,0,0);
//                    draw_cube_vertices = rotate_x(draw_cube_vertices, -5);
//                    position_x += 1f;
//                } else {
//                    draw_cube_vertices = translate(draw_cube_vertices, -1f,0,0);
//                    //Coordinate centre = FindCentre(draw_cube_vertices);
//                    //draw_cube_vertices = translate(draw_cube_vertices,-centre.x,-centre.y,-centre.z);
//                    draw_cube_vertices = rotate_x(draw_cube_vertices, 5);
//                    //draw_cube_vertices = translate(draw_cube_vertices,centre.x,centre.y,centre.z);
//
//                    position_x -= 1f;
//                }
                 if (rotation_z >= 360) {
                     rotation_z = 0;
                 }
//                     draw_cube_vertices = translate(draw_cube_vertices, -1f,0,0);
                     Coordinate centre = FindCentre(draw_cube_vertices);
                     draw_cube_vertices = translate(draw_cube_vertices,-centre.x,-centre.y,-centre.z);
                     draw_cube_vertices = rotate_z(draw_cube_vertices, rotation_z);
                     draw_cube_vertices = translate(draw_cube_vertices,centre.x,centre.y,centre.z);

                     rotation_z += 1;
                thisview.invalidate(); // update the view
            }
        };
        timer.scheduleAtFixedRate(task, 100, 2);

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

    private void DrawCube(Canvas canvas)
    {//draw a cube on the screen
        DrawLinePairs(canvas, draw_cube_vertices, 0, 1, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 1, 3, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 3, 2, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 2, 0, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 4, 5, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 5, 7, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 7, 6, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 6, 4, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 0, 4, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 1, 5, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 2, 6, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 3, 7, redPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw objects on the screen
        super.onDraw(canvas);
        DrawCube(canvas);//draw a cube onto the screen
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
        result.x=matrix[0]*vertex.x+matrix[1]*vertex.y+matrix[2]*vertex.z+matrix[3];
        result.y=matrix[4]*vertex.x+matrix[5]*vertex.y+matrix[6]*vertex.z+matrix[7];
        result.z=matrix[8]*vertex.x+matrix[9]*vertex.y+matrix[10]*vertex.z+matrix[11];
        result.w=matrix[12]*vertex.x+matrix[13]*vertex.y+matrix[14]*vertex.z+matrix[15];
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

}