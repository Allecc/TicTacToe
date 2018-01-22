package com.example.sledz.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int[] moves = new int[]{R.drawable.circle, R.drawable.cross };
    private int move = 0;
    private SparseIntArray board = new SparseIntArray();
    private int[] buttonIds = null;
    private boolean computer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(moves[0]);
        buttonIds = new int[]{R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};

        prepareBoard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newGame:
                prepareBoard();
                return true;
            case R.id.playComputer:
                if(item.isChecked()){
                    item.setChecked(false);
                    computer = false;
                }
                else{
                    item.setChecked(true);
                    computer = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void prepareBoard(){
        for(int id: buttonIds){
            board.put(id, 3);
            Button b = (Button) findViewById(id);
            b.setBackground(getDrawable(R.color.white));
            b.setEnabled(true);
        }


        TextView text = findViewById(R.id.textView);
        text.setText(R.string.next_player);

        ImageView img = findViewById(R.id.imageView);
        img.setVisibility(View.VISIBLE);
        img.setImageResource(moves[move]);
    }

    public void makeMove(View v){
        board.put(v.getId(), move);

        v.setEnabled(false);
        v.setBackground(getDrawable(moves[move]));

        if(move == 1){
            move = 0;
        } else {
            move = 1;
        }
        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(moves[move]);

        checkWin();
    }

    public void checkWin() {
        boolean allDisabled = true;
        for(int id : buttonIds){
            if(findViewById(id).isEnabled()){
                allDisabled = false;
                break;
            }
        }

        if(!allDisabled){
            for (int i = 0; i < 2; i++){
                if (board.get(R.id.button) == i && board.get(R.id.button2) == i && board.get(R.id.button3) == i
                        || board.get(R.id.button5) == i && board.get(R.id.button4) == i && board.get(R.id.button6) == i
                        || board.get(R.id.button7) == i && board.get(R.id.button8) == i && board.get(R.id.button9) == i
                        || board.get(R.id.button) == i && board.get(R.id.button5) == i && board.get(R.id.button8) == i
                        || board.get(R.id.button2) == i && board.get(R.id.button4) == i && board.get(R.id.button7) == i
                        || board.get(R.id.button3) == i && board.get(R.id.button6) == i && board.get(R.id.button9) == i
                        || board.get(R.id.button) == i && board.get(R.id.button4) == i && board.get(R.id.button9) == i
                        || board.get(R.id.button3) == i && board.get(R.id.button4) == i && board.get(R.id.button8) == i){
                    Log.d("Wynik", "" + i);

                    TextView text = findViewById(R.id.textView);
                    text.setText(R.string.victory);

                    ImageView img = findViewById(R.id.imageView);
                    img.setImageResource(moves[i]);

                    for(int id : buttonIds){
                        findViewById(id).setEnabled(false);
                    }
                    break;
                }
            }
        } else {
            TextView text = findViewById(R.id.textView);
            text.setText(R.string.remis);

            ImageView img = findViewById(R.id.imageView);
            img.setVisibility(View.INVISIBLE);
        }

    }
}
