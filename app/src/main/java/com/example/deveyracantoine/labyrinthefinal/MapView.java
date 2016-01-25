package com.hexagonal_games.labyrinthe;

/**
 * Created by vincent.dubois on 25/01/16.
 */

    import android.content.Context;
    import android.graphics.Canvas;
    import android.graphics.Matrix;
    import android.graphics.Paint;
    import android.graphics.PaintFlagsDrawFilter;
    import android.graphics.Rect;
    import android.graphics.RectF;

    import android.util.AttributeSet;
    import android.view.View;

    import com.example.deveyracantoine.labyrinthefinal.Maze;
    import com.example.deveyracantoine.labyrinthefinal.R;
    import com.example.deveyracantoine.labyrinthefinal.SpriteSheet;


/**
     * Created by dubois on 22/01/15.
     */
    public class MapView extends View {

        Maze maze;


        // Transformation permettant le centrage de la vue.
        private Matrix transform, reverse;

        // Rectangle réutilisable (pour éviter les instanciations)
        private RectF tmp;


        // Configuration du mode de dessin
        static PaintFlagsDrawFilter setfil= new PaintFlagsDrawFilter(0,
                Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);
        private Rect src;

        private SpriteSheet sprite;
        private Paint paint;
        private int w;
        private int h;


        // 3 constructeurs obligatoires pour une vue. Les 3 appellent init() pour ne pas dupliquer le code.

        public MapView(Context context) {
            super(context);
            init();
        }

        public MapView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        void init(){
            transform = new Matrix();
            reverse = new Matrix();

            sprite = SpriteSheet.get(this.getContext(), R.drawable.map,2,1);
            src = new Rect(0,0, sprite.w, sprite.h);
            tmp = new RectF();

            paint = new Paint();
            paint.setColor(0x7777ff77);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20f);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setAntiAlias(true);


            setMaze(new Maze());
        }

        public void setMaze(Maze maze){
            this.maze = maze;

            setZoom(w,h);
            invalidate();
        }

        public void onDraw(Canvas canvas){

            if (sprite == null || maze == null){ //si les sprites ne sont pas chargé, on ne fait rien.
                return;
            }

            canvas.setDrawFilter(setfil);

            // on sauvegarde la transformation en cours et on applique la transformation désirée
            canvas.save();
            canvas.concat(transform);

            //  canvas.drawColor(0xFF000000);

            //On parcours la carte
            for(int i = 0; i <maze.getWidth(); ++i){
                for(int j = 0; j < maze.getHeight(); ++j){
                    tmp.set(i,j,i+1,j+1);
                    canvas.drawBitmap(sprite.getBitmap(maze.getCode(i, j)), src,tmp,null);
                }
            }
            // On restore la transformation originale.
            canvas.restore();
        }



        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            this.w = w;
            this.h = h;
            setZoom(w, h);
        }

        /***
         * Calcul du centrage du contenu de la vue
         * @param w
         * @param h
         */
        private void setZoom(int w, int h) {
            if (w<=0 ||h <=0 || maze == null) return;

            // Dimensions dans lesquelles ont souhaite dessiner
            RectF src = new RectF(0,0,maze.getWidth(),maze.getHeight());

            // Dimensions à notre disposition
            RectF dst = new RectF(0,0,w,h);

            // Calcul de la transformation désirée (et de son inverse)
            transform.setRectToRect(src,dst, Matrix.ScaleToFit.CENTER);
            transform.invert(reverse);

            // Calcul de la taille du marqueur de position.
            float[] s = {0.5f,0.5f};
            float[] d = new float[2];
            transform.mapVectors(d,s);
            paint.setStrokeWidth(d[0]);
        }


    }



